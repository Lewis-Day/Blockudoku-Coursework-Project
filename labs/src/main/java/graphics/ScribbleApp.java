package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;


class Line{
    ArrayList<Point2D> points = new ArrayList<>();
}

class DrawComoponent extends JComponent{
    final ArrayList<Line> lines = new ArrayList<>();
    int pointSpan = 10;
    Line drawingLine = new Line();


    class MouseDrawListener implements MouseListener, MouseMotionListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            drawingLine.points.add(new Point2D.Double(e.getX(), e.getY()));
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            lines.add(drawingLine);
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {

            drawingLine.points.add(new Point2D.Double(e.getX(), e.getY()));
            repaint();

        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    public DrawComoponent(){
        MouseDrawListener mouseListener = new MouseDrawListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        setBackground(Color.WHITE);
    }

    public void reset(){
        this.lines.clear();
        drawingLine = new Line();
        repaint();
    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());


        for(Line line : lines) {
            for(int i = 1; i<line.points.size(); i++){

                Point2D point1 = line.points.get(i);
                Point2D point2 = line.points.get(Math.max(0, i-pointSpan));
                g.drawLine((int) point1.getX(), (int) point1.getY(), (int) point2.getX(), (int) point2.getY());
            }
        }

    }

    public Dimension getPreferredSize(){ return new Dimension(500, 300); }

}

class ScribblePanel extends JPanel{
    private final DrawComoponent drawComoponent = new DrawComoponent();
    JButton resetButton = new JButton("Reset");
    JPanel buttonPanel = new JPanel();

    public ScribblePanel(){

        setSize(drawComoponent.getPreferredSize());
        setLayout(new BorderLayout());
        this.add(drawComoponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawComoponent.reset();
            }
        });
    }
}

public class ScribbleApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scribble App - Mouse Event Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ScribblePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
