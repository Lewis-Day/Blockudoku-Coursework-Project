package shapes;

import geometry.Vec2d;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    // todo: implement JUnit tests for Tile

    @Test
    public void testContainsPoint() {
        ArrayList<Vec2d> vertices = new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        ));
        Tile tile = new Tile(new Vec2d(1, 1), vertices, Color.RED);


        assertTrue(tile.contains(new Vec2d(1, 1)));
        assertFalse(tile.contains(new Vec2d(3, 3)));
    }

    @Test
    public void testIntersects() {
        Tile tile1 = new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.BLUE);

        Tile tile2 = new Tile(new Vec2d(1, 1), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.GREEN);

        Tile tile3 = new Tile(new Vec2d(5, 5), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.YELLOW);

        assertTrue(tile1.intersects(tile2));
        assertFalse(tile1.intersects(tile3));
    }

    @Test
    public void testContainsTile() {
        Tile tile1 = new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 5),
                new Vec2d(5, 5),
                new Vec2d(5, 0)
        )), Color.RED);

        Tile tile2 = new Tile(new Vec2d(2, 2), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.BLUE);

        Tile tile3 = new Tile(new Vec2d(6, 6), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.GREEN);


        assertTrue(tile1.contains(tile2));
        assertFalse(tile1.contains(tile3));


    }
}
