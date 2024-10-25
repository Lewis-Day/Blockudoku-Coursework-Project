package stats;

import lists.GenericArrayList;

import java.util.ArrayList;
import java.util.List;

public class RunningSummary implements StatSummary {
    // todo: might some fields be useful here?  :)
    private double sum = 0;
    private double sumofsquaresfrommean = 0;
    private int numValues = 0;
    private double sumofsquares = 0;
    private double mean = 0;
    private double mean2 = 0;

    @Override
    public double mean() {
        if (n() == 0) {
            throw new NotEnoughDataException("Need at least one value for mean, we have zero");
        }
//        else if(n() == 1){
//            return sum();
//        }
//        return sum() / n();
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
        //used Simon's standard deviation formula from lectures
//        double variance = (sumofsquares/n() - mean() * mean());
//        return Math.sqrt(variance);
        return Math.sqrt(mean2 / (n()-1));
    }

    @Override
    public StatSummary add(double value) {
        sum = sum + value;
////        sumofsquaresfrommean = sumofsquaresfrommean + Math.pow((value - mean()), 2);
//        sumofsquares += value * value;
//        numValues++;
        numValues++;
        double delta = value - mean;
        mean += delta / numValues;
        double delta2 = value - mean;
        mean2 += delta*delta2;

        return this;
    }

    @Override
    public StatSummary add(Number value) {
        // todo: implement this
        sum = sum + value.doubleValue();
////        sumofsquaresfrommean = sumofsquaresfrommean + Math.pow((value.doubleValue() - mean()), 2);
//        sumofsquares += value.doubleValue() * value.doubleValue();
//        numValues++;
        numValues++;
        double delta = value.doubleValue() - mean;
        mean += delta / numValues;
        double delta2 = value.doubleValue() - mean;
        mean2 += delta*delta2;

        return this;
    }

    @Override
    public StatSummary add(List<? extends Number> values) {
        for (Number value : values) {
            sum += value.doubleValue();
////            sumofsquaresfrommean = sumofsquaresfrommean + Math.pow((value.doubleValue() - mean()), 2);
//            sumofsquares += value.doubleValue() * value.doubleValue();
//            numValues++;
            numValues++;
            double delta = value.doubleValue() - mean;
            mean += delta / numValues;
            double delta2 = value.doubleValue() - mean;
            mean2 += delta*delta2;

        }
        return this;
    }
}
