package reflection.uml;

/**
 *
 * This class will lay out a class diagram based on its inheritance relationships.
 * It will be based on the UML diagram layout from the
 * Map<String, ClassLayout> calculateLayout(DiagramData diagram) method.
 *
 * Hence, all the work we do here is purely in rendering the class diagram,
 * in fact to begin with we will just render the class boxes, not even the lines connecting them.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import reflection.uml.UMLLayout.ClassLayout;

public class DisplayUML extends JPanel {

    private final Map<String, ClassLayout> layout; // This holds the layout of the class boxes

    // Constructor that takes the layout map
    public DisplayUML(Map<String, ClassLayout> layout) {
        this.layout = layout;
        setPreferredSize(new Dimension(800, 600)); // Set the preferred size for the component
    }

    // Override the paintComponent method to draw the class boxes
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Map<String, Integer> xTop = new HashMap<>();
        Map<String, Integer> yTop = new HashMap<>();

        Map<String, Integer> xBottom = new HashMap<>();
        Map<String, Integer> yBottom = new HashMap<>();


        // Set a font for the class names
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        // Loop through the layout map and draw each class box
        for (Map.Entry<String, ClassLayout> entry : layout.entrySet()) {
            String className = entry.getKey();
            ClassLayout cl = entry.getValue();


            // Get the position and size of the class box
            double x = cl.centerX() - cl.width() / 2;
            double y = cl.centerY() - cl.height() / 2 + 10;
            double width = cl.width();
            double height = cl.height();


            // Draw the class name centered in the box
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(className);
            int largestTextWidth = textWidth;
            int textX = (int) (x + (width - textWidth) / 2);
            int textY = (int) (y + fm.getAscent());


            //Get position and size of the fields box
            double fieldsY = textY + fm.getHeight() + 10;
            double fieldsHeight = cl.fieldsH();
            int fieldsTextY = (int)(fieldsY + fm.getAscent()+10);


            for(ReflectionData.FieldData fields : cl.fields()){
                String text = fields.name() + " : " + fields.type();
                if(fm.stringWidth(text) > largestTextWidth ){
                    largestTextWidth = fm.stringWidth(text);
                }

                g2d.drawString(text, (int)x+5, fieldsTextY);
                fieldsTextY = fieldsTextY + fm.getHeight();

            }

            double methodsHeight = cl.methodsH();
            double methodsY = fieldsTextY;

            int methodsTextY = (int)(methodsY + fm.getAscent()+10);
            for(ReflectionData.MethodData methods : cl.methods()){
                String text = methods.name() + "() :: " + methods.returnType();
                if(fm.stringWidth(text) > largestTextWidth ){
                    largestTextWidth = fm.stringWidth(text);
                }
                g2d.drawString(text, (int)x+5, methodsTextY);
                methodsTextY = methodsTextY + fm.getHeight();
            }

            int boxWidth = largestTextWidth + 10;
            double classBoxHeight = methodsTextY - y;
            double textPosition = (boxWidth - textWidth) / 2;
            int halfTextWidth = textWidth / 2;


            g2d.drawRect((int) x, (int) y, boxWidth, (int) classBoxHeight);

            if(cl.type().equals(ReflectionData.ClassType.CLASS)){
                g2d.setColor(new Color(0, 0, 255, 50));
                g2d.fillRect((int) x, (int) y, boxWidth, (int) classBoxHeight);
                g2d.setColor(Color.BLACK);
            }

            else if(cl.type().equals(ReflectionData.ClassType.INTERFACE)){
                g2d.setColor(new Color(0, 255, 0, 50));
                g2d.fillRect((int) x, (int) y, boxWidth, (int) classBoxHeight);
                g2d.setColor(Color.BLACK);
            }

            g2d.drawRect((int)x, (int)fieldsY, boxWidth, (int)(fieldsTextY - fieldsY));
            g2d.drawRect((int)x, (int)methodsY, boxWidth, (int)(methodsTextY - methodsY));
            textX = (int) (x + textPosition);
            g2d.drawString(className, textX, textY);

            xTop.put(className, (textX + halfTextWidth));
            yTop.put(className, (int) (y));

            xBottom.put(className, (textX + halfTextWidth));
            yBottom.put(className, (int)(y + classBoxHeight));
        }

        for (Map.Entry<String, ClassLayout> entry : layout.entrySet()) {
            String className = entry.getKey();
            ClassLayout cl = entry.getValue();

            for (ReflectionData.Link classLink : cl.classLinks()) {
                if(className.equals(classLink.from())){

                    Integer sX = xTop.get(className);
                    Integer sY = yTop.get(className);

                    Integer eX = xBottom.get(classLink.to());
                    Integer eY = yBottom.get(classLink.to());

                    if (sX != null && sY != null && eX != null && eY != null) {
                        if(classLink.type() == ReflectionData.LinkType.DEPENDENCY){
                            float[] dashPattern = {5, 5};
                            Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0);
                            g2d.setStroke(dashedStroke);
                        }
                        g2d.drawLine(sX, sY, eX, eY);
                        g2d.setStroke(new BasicStroke());
                    }

                }
            }


        }
    }

    // Main method to set up a simple frame and display the panel
    public static void main(String[] args) {
        // Mock-up example layout to display
//        Map<String, ClassLayout> exampleLayout = Map.of(
//                "MyShape", new ClassLayout(200, 100, 150, 60, 30, 20,),
//                "MyCircle", new ClassLayout(200, 200, 150, 120, 30, 20),
//                "MyEllipse", new ClassLayout(400, 200, 150, 90, 30, 20),
//                "Connector", new ClassLayout(300, 300, 150, 60, 30, 20)
//
//        );
        List<Class<?>> classes = new ArrayList<>();
        classes.add(MyShape.class);
        classes.add(MyCircle.class);
        classes.add(MyCircle.InnerStatic.class);
        classes.add(MyEllipse.class);
        classes.add(Connector.class);
        System.out.println(classes);
        System.out.println();
        ReflectionData.DiagramData dd = new ProcessClasses().process(classes);

        // Create the panel to display the UML diagram
        Map<String, ClassLayout> layout = new UMLLayout().calculateLayout(dd);
        DisplayUML display = new DisplayUML(layout);

        // Create a frame to hold the panel
        JFrame frame = new JFrame("UML Class Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
