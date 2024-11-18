package shapes;

import geometry.Vec2d;

import java.awt.*;

public class DrawableCircle extends Circle implements Drawable {
    private Color color;

    public DrawableCircle(Vec2d p, double radius, Color color) {
        super(p, radius);
        this.color = color;
    }

    public void draw(Graphics2D g) {
        // todo: implement
        // draw a filled circle centre at position with radius
        Vec2d position = getPosition();
        g.setColor(color);
        g.fillOval((int)position.x(), (int)position.y(), (int)(2*radius), (int)(2*radius));
    }
}
