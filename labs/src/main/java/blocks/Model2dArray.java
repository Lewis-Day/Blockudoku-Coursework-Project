package blocks;

/**
 * Logical model for the Blocks Puzzle
 * This handles the game logic, such as the grid, the pieces, and the rules for
 * placing pieces and removing lines and subgrids.
 * <p>
 * Note this has no dependencies on the UI or the game view, and no
 * concept of pixel-space or screen coordinates.
 * <p>
 * The standard block puzzle is on a 9x9 grid, so all placeable shapes will have
 * cells in that range.
 */

import blocks.BlockShapes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model2dArray extends State2dArray implements ModelInterface {
    List<Shape> regions = new RegionHelper().allRegions();

    public Model2dArray() {
        grid = new boolean[width][height];
        // initially all cells are empty (false) - they would be by default anyway
        // but this makes it explicit
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = false;
            }
        }
    }

    public int getScore() {
        return score;
    }


    // interestingly, for canPlace we could also use sets to store the occupied cells
    // and then check if the shape's cells intersect with the occupied cells

    public boolean canPlace(Piece piece) {
        // todo: implement
        // check if the shape can be placed at this loc
        List<Cell> cells = piece.cells();

        for (Cell cell : cells) {
            if (cell.x() < 0 || cell.x() >= width || cell.y() < 0 || cell.y() >= height) {
                return false;
            }
        }

        for(Cell cell : cells){
            if(grid[cell.x()][cell.y()]){
                return false;
            }
        }
        return true;
    }

    @Override
    public void place(Piece piece) {
        // todo: implement
        if(canPlace(piece)){
            for(Cell cell: piece.cells()){
                grid[cell.x()][cell.y()] = true;
            }
        }
    }

    @Override
    public void remove(Shape region) {
        // todo: implement
        for(Cell cells: region){
            grid[cells.x()][cells.y()] = false;
        }
    }

    public boolean isComplete(Shape region) {
        // todo: implement
        // check if the shape is complete, i.e. all cells are occupied
        for(Cell cells: region){

            if(grid[cells.x()][cells.y()]){
                return true;
            }
        }
        return false;
    }

    private boolean wouldBeComplete(Shape region, List<Cell> toAdd) {
        // todo: implement
        // check if the shape is complete, i.e. all cells are occupied
        return true;
    }

    @Override
    public boolean isGameOver(List<Shape> palettePieces) {
        // todo: implement
        // if any shape in the palette can be placed, the game is not over
        return true;
    }

    public boolean canPlaceAnywhere(Shape shape) {
        // todo: implement
        // check if the shape can be placed anywhere on the grid
        // by checking if it can be placed at any loc
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                Piece piece = new Piece(shape, new Cell(i, j));

                if(canPlace(piece)){
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public List<Shape> getPoppableRegions(Piece piece) {
        // todo: implement
        // iterate over the regions
        List<Shape> poppable = new ArrayList<>();
        return poppable;
    }

    @Override
    public Set<Cell> getOccupiedCells() {
        // todo: implement
        Set<Cell> occupiedCells = new HashSet<>();
        return occupiedCells;
    }
}

