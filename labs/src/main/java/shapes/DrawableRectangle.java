package shapes;

import geometry.Vec2d;

import java.awt.*;

public class DrawableRectangle extends Rectangle implements Drawable {
    final private Color color;

    public DrawableRectangle(Vec2d p, double width, double height, Color color) {
        super(p, width, height);
        this.color = color;
    }

    public void draw(Graphics2D g) {
        // todo: implement
        // draw a filled rectangle with width and height centred at position
        Vec2d position = getPosition();
        g.setColor(color);
        g.fillRect((int)position.x(), (int)position.y(), (int)super.width, (int)super.height);
    }
}
