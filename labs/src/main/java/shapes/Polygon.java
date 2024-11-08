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
        return 0.0;
    }

    public double perimeter() {
        // todo: implement by summing the lengths of the edges
        double sum = 0;

        for (Vec2d vertex : vertices) {
            for(int i = 1; i<vertices.size(); i++){
                sum = sum + vertex.distance(vertices.get(i));
            }
        }

        return sum;
    }
}
