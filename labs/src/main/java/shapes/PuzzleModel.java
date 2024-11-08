package shapes;


import geometry.Vec2d;

import java.util.ArrayList;
import java.util.List;

public class PuzzleModel {
    Tile box;
    final private ArrayList<Tile> tiles;

    public PuzzleModel(Tile box, ArrayList<Tile> tiles) {
        this.box = box;
        this.tiles = tiles;
        System.out.println("PuzzleModel created with " + tiles.size() + " tiles.");
    }

    public boolean isSolved() {
        // check containment and overlaps
        return countContains() == tiles.size() && countOverlaps() == 0;
    }

    public String getStatusText() {
        return "n Overlaps " + countOverlaps() + " Contains? " +
                countContains() + " Solved? " + isSolved();
    }

    public Tile getTileAt(Vec2d point) {
        // todo: implement - return the 'top' tile that contains the point
        // or null if no tile contains the point
        return null;
    }

    public boolean checkOverlaps(Tile currentShape) {
        // todo: implement - return true if the currentShape overlaps any other shape
        return false;
    }

    public int countOverlaps() {
        // todo: implement - return the number of tiles that overlap at least one other tile
        int total = 0;
        return total;
    }

    public int countContains() {
        // todo: implement - return the number of tiles that are completely contained in the box
        int total = 0;
        return total;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}


