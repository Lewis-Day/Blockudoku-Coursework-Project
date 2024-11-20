package shapes;


import geometry.Vec2d;

import java.util.ArrayList;

public class Polygon extends MovableShape {
    ArrayList<Vec2d> vertices;

    public Polygon(Vec2d p, ArrayList<Vec2d> vertices) {
        super(p);
        this.vertices = vertices;
    }

    public double area() {
        // todo: implement using the shoelace formula
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < vertices.size()-1; i++) {
            sum1 += vertices.get(i).x() * vertices.get(i+1).y();
            sum2 += vertices.get(i).y() * vertices.get(i+1).x();
        }

        sum1 += vertices.get(vertices.size()-1).x() * vertices.get(0).y();
        sum2 += vertices.get(vertices.size()-1).y() * vertices.get(0).x();

        return 0.5 * Math.abs(sum1 - sum2);
    }

    public double perimeter() {
        // todo: implement by summing the lengths of the edges
        double sum = 0;

        for (int i=0; i<vertices.size(); i++) {

            sum = sum + vertices.get(i).distance(vertices.get((i+1) % vertices.size()));

        }

        return sum;
    }
}
