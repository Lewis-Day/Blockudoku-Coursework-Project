package stats;

import lists.GenericArrayList;

import java.util.ArrayList;
import java.util.List;

public class RunningSummary implements StatSummary {
    // todo: might some fields be useful here?  :)
    final private List<Number> values = new ArrayList<>();
    private double sum = 0;
    private double sumofsquaresfrommean = 0;
    private int numValues = 0;

    @Override
    public double mean() {
        if (n() == 0) {
            throw new NotEnoughDataException("Need at least one value for mean, we have zero");
        }
        else if(n() == 1 ){return sum();}
        return sum() / n();
    }

    @Override
    public int n() {return numValues;}

    @Override
    public double sum() {return sum;}

    @Override
    public double standardDeviation() {
        if (n() < 2) {
            throw new NotEnoughDataException("Need at least two values for standard deviation, we have " + n());
        }
        double variance = sumofsquaresfrommean/n();
        return Math.sqrt(variance);
    }

    @Override
    public StatSummary add(double value) {
        sum = sum + value;
        values.add(value);
        sumofsquaresfrommean = sumofsquaresfrommean + Math.pow((value - mean()), 2);
        numValues++;
        return this;
    }

    @Override
    public StatSummary add(Number value) {
        // todo: implement this
        sum = sum + value.doubleValue();
        sumofsquaresfrommean = sumofsquaresfrommean + Math.pow((value.doubleValue() - mean()), 2);
        numValues++;
        return this.add(value.doubleValue());
    }

    @Override
    public StatSummary add(List<? extends Number> values) {
        for (Number value : values) {
            sum = sum + value.doubleValue();
            sumofsquaresfrommean = sumofsquaresfrommean + Math.pow((value.doubleValue() - mean()), 2);
            numValues++;
        }
        return this;
    }
}
