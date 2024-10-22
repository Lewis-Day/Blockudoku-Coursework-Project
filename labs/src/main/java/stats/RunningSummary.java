package stats;

import lists.GenericArrayList;

import java.util.ArrayList;
import java.util.List;

public class RunningSummary implements StatSummary {
    // todo: might some fields be useful here?  :)

    final private GenericArrayList<Number> values = new GenericArrayList<Number>();

    @Override
    public double mean() {
        if (n() == 0) {
            throw new NotEnoughDataException("Need at least one value for mean, we have zero");
        }
        return sum() / n();
    }

    @Override
    public int n() {
        // todo: implement this
        return values.length();
    }

    @Override
    public double sum() {
        return 0;
    }

    @Override
    public double standardDeviation() {
        // todo: implement this
        return 0;
    }

    @Override
    public StatSummary add(double value) {
        values.append(value);
        return this;
    }

    @Override
    public StatSummary add(Number value) {
        // todo: implement this
        return this.add(value.doubleValue());
    }

    @Override
    public StatSummary add(List<? extends Number> values) {
        // todo: implement this
        return null;
    }
}
