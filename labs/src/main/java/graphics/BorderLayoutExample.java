package graphics;
import javax.swing.*;
import java.awt.*;

class ColorComponent extends JComponent{

    Color colour;
    Dimension dimension;

    public ColorComponent(Color borderColour, Dimension borderDimension) {
        this.colour = borderColour;
        this.dimension = borderDimension;
        setPreferredSize(dimension);

    }
    protected void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        g.setColor(this.colour);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

public class BorderLayoutExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("BorderLayout Example");
        frame.setLayout(new BorderLayout());
        frame.add(new ColorComponent(Color.RED, new Dimension(400, 50)), BorderLayout.NORTH);
        frame.add(new ColorComponent(Color.BLUE, new Dimension(400, 50)), BorderLayout.SOUTH);
        frame.add(new ColorComponent(Color.GREEN, new Dimension(50, 100)), BorderLayout.EAST);
        frame.add(new ColorComponent(Color.YELLOW, new Dimension(50, 200)), BorderLayout.WEST);
        frame.add(new ColorComponent(Color.ORANGE, null), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);


    }

}
