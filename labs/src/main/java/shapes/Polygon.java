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
        return 0;
    }

    public double perimeter() {
        // todo: implement by summing the lengths of the edges
        return 0;
    }
}
