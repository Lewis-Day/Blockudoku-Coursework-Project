package shapes;

import geometry.Vec2d;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleModelTest {
    // todo: implement JUnit tests for PuzzleModel and its methods

    //Tests generated using ChatGPT

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
        assertNotNull(model.getTileAt(new Vec2d(1, 1))); // Inside first tile
        assertNotNull(model.getTileAt(new Vec2d(4, 4))); // Inside second tile
        assertNull(model.getTileAt(new Vec2d(6, 6)));    // Outside all tiles
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

        // Check containment
        assertEquals(1, model.countContains()); // Only one tile is in the box
    }

    @Test
    public void testIsSolved() {
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

        tiles.add(new Tile(new Vec2d(2, 2), new ArrayList<>(List.of(
                new Vec2d(0, 0),
                new Vec2d(0, 1),
                new Vec2d(1, 1),
                new Vec2d(1, 0)
        )), Color.BLUE));

        PuzzleModel model = new PuzzleModel(box, tiles);

        // Check solution status
        assertFalse(model.isSolved()); // Not solved initially

        // Adjust to fit all tiles without overlaps
        tiles.get(1).moveTo(new Vec2d(4, 4));
        assertTrue(model.isSolved()); // Solved now
    }

}
