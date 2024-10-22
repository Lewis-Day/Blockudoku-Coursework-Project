package stats;

public class StringSelectors {
    // implement convenience methods for selecting strings
    public Selector<String> longestString() {
        // todo: implement this method
        return new Selector<String>((a, b) -> Integer.compare(a.length(), b.length()));
    }

    public Selector<String> shortestString() {
        // todo: implement this method
        return new Selector<String>((a, b) -> Integer.compare(b.length(), a.length()));
    }
}


