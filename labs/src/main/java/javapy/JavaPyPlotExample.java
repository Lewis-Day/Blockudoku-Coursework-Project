package javapy;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jep.Jep;
import jep.JepConfig;
import jep.JepException;
import jep.SubInterpreter;
import lists.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import java.util.Base64;
import java.util.function.Supplier;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

record GraphDataResultSet(
        List<Integer> xValues,
        Map<String, List<Double>> results
) {}


public class JavaPyPlotExample {

    // Generate random data series
    public static Map<String, List<Double>> generateRandomData(int seriesCount, int dataCount) {
        Random random = new Random();
        Map<String, List<Double>> dataSeries = new HashMap<>();
        for (int i = 0; i < seriesCount; i++) {
            List<Double> data = new ArrayList<>();
            for (int j = 0; j < dataCount; j++) {
                data.add(random.nextDouble() * 100);
            }
             dataSeries.put("Series " + (i + 1), data);
        }
        return dataSeries;
    }

    static long time_n_appends(IntList list, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.append(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static GraphDataResultSet GenerateResults(int numRuns){
        List<Supplier<IntList>> listMakers = new ArrayList<>();
//        listMakers.add(IntArrayList::new);
//        listMakers.add(IntLinkedList::new);
        listMakers.add(EfficientIntArrayList::new);
        listMakers.add(EfficientIntLinkedList::new);
//        listMakers.add(() -> new GenIntListWrapper(new GenericArrayList<Integer>()));
//        listMakers.add(() -> new GenIntListWrapper(new GenericLinkedList<Integer>()));
//        listMakers.add(() -> new GenIntListWrapper(new GenericLinkedListRecord<Integer>()));

        int initial_n = 1000;
        int n_step = 1000;
        int n_max = 100000;
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
                double totalTime = 0;
                System.out.println(n);
                for(int k= 0; k < numRuns; k++){
                    long t = time_n_appends(listMaker.get(), n);
                    totalTime = totalTime + t;
                }
                double avgTime = totalTime / numRuns;
                series.add((double) avgTime);
            }
            results.put(seriesName, series);
        }
        return new GraphDataResultSet(xValues, results);
    }


    // Display the plot image
    public static void displayImage(BufferedImage img) {
        JFrame frame = new JFrame("Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Generate random data
//            Map<String, List<Double>> dataSeries = generateRandomData(3, 100);
            GraphDataResultSet results = GenerateResults(100);

            // Convert the data to JSON using GSON
            Gson gson = new GsonBuilder().create();
            String jsonData = gson.toJson(results);
            System.out.println("Sending data to Python: " + jsonData);

            JepConfig config = new JepConfig();
            config.addIncludePaths(".", "/opt/miniconda3/bin/python3");
            System.out.println(config);
            try (Jep jep = new SubInterpreter(config)) {
                // Load the Python script to process the data and generate the plot
                jep.eval("import sys");
                jep.eval("sys.stdout = sys.__stdout__");

                jep.runScript("./labs/pysrc/javapy/plot_series.py");

                // Send JSON data to Python
                jep.set("json_data", jsonData);

                // Generate plot and return the image as a Base64-encoded string
//                String base64Image = "test";
                String base64Image = (String) jep.getValue("plot_and_return_image(json_data)");
//                System.out.println("Base64 image: " + base64Image);

                // Decode the Base64-encoded image
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                InputStream is = new ByteArrayInputStream(imageBytes);
                BufferedImage img = ImageIO.read(is);

                System.out.println("Image: " + img);
                // Display the image in a JFrame
                displayImage(img);
            }
        } catch (JepException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
