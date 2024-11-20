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

    //Generated using ChatGPT

    @Test
    public void testContainsPoint() {
        ArrayList<Vec2d> vertices = new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        ));
        Tile tile = new Tile(new Vec2d(1, 1), vertices, Color.RED);

        // Test points inside the tile
        assertTrue(tile.contains(new Vec2d(1, 1)));
        assertTrue(tile.contains(new Vec2d(1.5, 1.5)));

        // Test points outside the tile
        assertFalse(tile.contains(new Vec2d(0, 0))); // Outside the world position
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

        // Test intersection
        assertTrue(tile1.intersects(tile2));

        // Test non-intersection
        Tile tile3 = new Tile(new Vec2d(5, 5), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.YELLOW);

        assertFalse(tile1.intersects(tile3));
    }

    @Test
    public void testContainsTile() {
        Tile largerTile = new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 5),
                new Vec2d(5, 5),
                new Vec2d(5, 0)
        )), Color.RED);

        Tile smallerTile = new Tile(new Vec2d(2, 2), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.BLUE);

        // Test containment
        assertTrue(largerTile.contains(smallerTile));

        // Test non-containment
        Tile outsideTile = new Tile(new Vec2d(6, 6), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.GREEN);

        assertFalse(largerTile.contains(outsideTile));


    }
}
