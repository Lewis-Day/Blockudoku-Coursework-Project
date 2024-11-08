package geometry;


public record Vec2d(double x, double y) {
    public double distance(Vec2d other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public double magnitude() {
        // todo: implement
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vec2d add(Vec2d other) {
        return new Vec2d(x + other.x, y + other.y);
    }

    public Vec2d subtract(Vec2d other) {
        // todo: implement
        return new Vec2d(x - other.x, y - other.y);
    }

    public Vec2d multiply(double scalar) {
        // todo: implement
        return new Vec2d(x * scalar, y * scalar);
    }

    public double dot(Vec2d other) {
        // todo: implement
        return ((this.x * other.x)+(this.y * other.y));
    }

    public Vec2d perpendicular() {
        return new Vec2d(-y, x);
    }

    public double angleRadians() {
        return Math.atan2(y, x);
    }
}
