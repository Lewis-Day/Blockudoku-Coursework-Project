package stats;

import java.util.List;

public class RunningSummary implements StatSummary {
    // todo: might some fields be useful here?  :)

    //Using Welford's algorithm to calculate variance and hence standard deviation
    private double sum = 0;
    private int numValues = 0;
    private double mean = 0;
    //M2 is the sum of the squares from the mean
    private double M2 = 0;

    @Override
    public double mean() {
        if (n() == 0) {
            throw new NotEnoughDataException("Need at least one value for mean, we have zero");
        }
        return mean;
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
        return Math.sqrt(M2 / (n()-1));
    }

    @Override
    public StatSummary add(double value) {
        sum = sum + value;
        numValues++;
        double delta = value - mean;
        mean = mean +  delta / numValues;
        double delta2 = value - mean;
        M2 = M2 + delta*delta2;

        return this;
    }

    @Override
    public StatSummary add(Number value) {
        // todo: implement this
        sum = sum + value.doubleValue();
        numValues++;
        double delta = value.doubleValue() - mean;
        mean = mean + delta / numValues;
        double delta2 = value.doubleValue() - mean;
        M2 = M2 + delta*delta2;

        return this;
    }

    @Override
    public StatSummary add(List<? extends Number> values) {
        for (Number value : values) {
            sum += value.doubleValue();
            numValues++;
            double delta = value.doubleValue() - mean;
            mean = mean + delta / numValues;
            double delta2 = value.doubleValue() - mean;
            M2 = M2 + delta*delta2;

        }
        return this;
    }
}
