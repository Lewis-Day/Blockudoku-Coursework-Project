package reflection.uml;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunUMLDiagram {

    //Adds classes to an arraylist then gets diagram data, gets layout then gets diagram
    public static void main(String[] args) {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(MyShape.class);
        classes.add(MyCircle.class);
        classes.add(MyEllipse.class);
        classes.add(Connector.class);
        System.out.println(classes);
        System.out.println();
        ReflectionData.DiagramData data = new ProcessClasses().process(classes);
        UMLLayout umlLayout = new UMLLayout();
        Map<String, UMLLayout.ClassLayout> layout = umlLayout.calculateLayout(data);
        DisplayUML displayUML = new DisplayUML(layout);

        RunUMLDiagram diagram = new RunUMLDiagram();
        diagram.createJFrame(displayUML);


    }

    //Method to display the output in a JFrame
    public void createJFrame(DisplayUML displayUML){
        JFrame frame = new JFrame("UML Class Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(displayUML);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
