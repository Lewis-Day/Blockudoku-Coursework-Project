package lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

record GraphDataResultSet(
        List<Integer> xValues,
        Map<String, List<Double>> results
) {}

public class EvalIntListSpeed {
    static long time_n_appends(IntList list, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.append(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }


    public static void main(String[] args) {
        IntList[] lists = {
                new IntArrayList(),
                new IntLinkedList(),
                new EfficientIntArrayList(),
                new EfficientIntLinkedList(),
                new GenIntListWrapper(new GenericArrayList<Integer>()),
                new GenIntListWrapper(new GenericLinkedList<Integer>()),
                new GenIntListWrapper(new GenericLinkedListRecord<Integer>()),

        };

        List<Supplier<IntList>> listMakers = new ArrayList<>();
        listMakers.add(IntArrayList::new);
        listMakers.add(IntLinkedList::new);
        listMakers.add(EfficientIntArrayList::new);
        listMakers.add(EfficientIntLinkedList::new);
        listMakers.add(() -> new GenIntListWrapper(new GenericArrayList<Integer>()));
        listMakers.add(() -> new GenIntListWrapper(new GenericLinkedList<Integer>()));
        listMakers.add(() -> new GenIntListWrapper(new GenericLinkedListRecord<Integer>()));

        int initial_n = 1000;
        int n_step = 1000;
        int n_max = 10000;
        List<Integer> xValues = new ArrayList<>();
        for (int n = initial_n; n <= n_max; n += n_step) {
            xValues.add(n);
        }

        Map<String, List<Double>> results = new HashMap<>();
        for (Supplier<IntList> listMaker : listMakers) {
            String seriesName = listMaker.get().getClass().getSimpleName();

            if(seriesName.equals("GenIntListWrapper")){
                seriesName = listMaker.get().toString();
            }

            List<Double> series = new ArrayList<>();
            System.out.println("List class: " + seriesName);
            for (int n = initial_n; n <= n_max; n += n_step) {
                System.out.println(n);
                long t = time_n_appends(listMaker.get(), n);
                series.add((double) t);
            }
            results.put(seriesName, series);
        }
        System.out.println(new GraphDataResultSet(xValues, results));
    }
}
