package shapes;

import geometry.Vec2d;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleModelTest {
    // todo: implement JUnit tests for PuzzleModel and its methods

    @Test
    public void testGetTileAt() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.RED));

        tiles.add(new Tile(new Vec2d(3, 3), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.BLUE));

        PuzzleModel model = new PuzzleModel(null, tiles);

        // Check if the correct tile is returned
        assertNotNull(model.getTileAt(new Vec2d(1, 1)));
        assertNotNull(model.getTileAt(new Vec2d(4, 4)));
        assertNull(model.getTileAt(new Vec2d(6, 6)));
    }

    @Test
    public void testCountContains() {
        Tile box = new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 10),
                new Vec2d(10, 10),
                new Vec2d(10, 0)
        )), Color.BLACK);

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(new Vec2d(1, 1), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.RED));

        tiles.add(new Tile(new Vec2d(15, 15), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.BLUE));

        PuzzleModel model = new PuzzleModel(box, tiles);

        assertEquals(1, model.countContains());
        assertNotEquals(2, model.countContains());
    }

    @Test
    public void testCheckOverlaps(){
        Tile box = new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 10),
                new Vec2d(10, 10),
                new Vec2d(10, 0)
        )), Color.BLACK);

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(new Vec2d(1, 1), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.RED));

        tiles.add(new Tile(new Vec2d(15, 15), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.BLUE));

        Tile overlappingTile = new Tile(new Vec2d(16, 16), new ArrayList<>(List.of(
                new Vec2d(-1, -1),
                new Vec2d(-1, 1),
                new Vec2d(1, 1),
                new Vec2d(1, -1)
        )), Color.CYAN);
        PuzzleModel model = new PuzzleModel(box, tiles);

        Tile nonOverlappingTile = new Tile(new Vec2d(20, 20), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.GREEN);

        assertTrue(model.checkOverlaps(overlappingTile));
        assertFalse(model.checkOverlaps(nonOverlappingTile));
    }

    @Test
    public void testCountOverlaps(){
        Tile box = new Tile(new Vec2d(0, 0), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 10),
                new Vec2d(10, 10),
                new Vec2d(10, 0)
        )), Color.BLACK);

        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(new Vec2d(1, 1), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.RED));

        tiles.add(new Tile(new Vec2d(15, 15), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.BLUE));

        tiles.add(new Tile(new Vec2d(16, 16), new ArrayList<>(List.of(
                new Vec2d(-1, -1),
                new Vec2d(-1, 1),
                new Vec2d(1, 1),
                new Vec2d(1, -1)
        )), Color.CYAN));
        PuzzleModel model = new PuzzleModel(box, tiles);

        tiles.add(new Tile(new Vec2d(20, 20), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 2),
                new Vec2d(2, 2),
                new Vec2d(2, 0)
        )), Color.GREEN));

        assertEquals(2, model.countOverlaps());
        assertNotEquals(1, model.countOverlaps());
    }




}
