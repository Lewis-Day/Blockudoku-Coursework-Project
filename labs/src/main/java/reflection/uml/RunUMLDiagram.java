package reflection.uml;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunUMLDiagram {

    public static void main(String[] args) {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(MyShape.class);
        classes.add(MyCircle.class);
        classes.add(MyEllipse.class);
        classes.add(Connector.class);
        System.out.println(classes);
        System.out.println();
        ReflectionData.DiagramData dd = new ProcessClasses().process(classes);
        UMLLayout umlLayout = new UMLLayout();
        Map<String, UMLLayout.ClassLayout> layout = umlLayout.calculateLayout(dd);
        DisplayUML displayUML = new DisplayUML(layout);

        RunUMLDiagram diagram = new RunUMLDiagram();
        diagram.createJFrame(displayUML);


    }

    public void createJFrame(DisplayUML displayUML){
        JFrame frame = new JFrame("UML Class Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(displayUML);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
