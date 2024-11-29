package blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import blocks.BlockShapes.Shape;
import blocks.BlockShapes.ShapeSet;
import blocks.BlockShapes.SpriteState;
import blocks.BlockShapes.Sprite;
import blocks.BlockShapes.PixelLoc;

public class Palette {
    ArrayList<Shape> shapes = new ArrayList<>();
    List<Sprite> sprites;
    int nShapes = 3;

    public Palette() {
        shapes.addAll(new ShapeSet().getShapes());
        sprites = new ArrayList<>();
        replenish();
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public ArrayList<Shape> getShapesToPlace() {
        // todo: implement

        // return a list of shapes that are in the palette - could use streams to filter this

        ArrayList<Shape> shapesInPalette = new ArrayList<>();

        for (Sprite sprite : sprites) {
            if (sprite.state == SpriteState.IN_PALETTE || sprite.state == SpriteState.IN_PLAY) {
                shapesInPalette.add(sprite.shape);
            }
        }
        return shapesInPalette;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    // if we have a sprite that contains the point (px, py), return it
    // and the size of the cells - the sprite location is already in pixel coordinates
    public Sprite getSprite(PixelLoc mousePoint, int cellSize) {
        // todo: implement
        for (Sprite sprite : sprites) {
            if(sprite.contains(mousePoint, cellSize)){
                return sprite;
            }
        }
        return null;
    }

    private int nReadyPieces() {
        int count = 0;
        for (Sprite sprite : sprites) {
            if (sprite.state == SpriteState.IN_PALETTE || sprite.state == SpriteState.IN_PLAY) {
                count++;
            }
        }
        System.out.println("nReadyPieces: " + count);
        return count;
    }

    public void doLayout(int x0, int y0, int cellSize) {
        // todo: implement

        // layout the sprites in the palette
        int margin = x0;
        int xCoord = x0;

        for(Sprite sprite:sprites){
            sprite.px = xCoord;
            sprite.py = y0;

            xCoord = xCoord + cellSize + margin;
        }
    }

    public void replenish() {
        // todo: implement
        if (nReadyPieces() > 0) {
            return;
        }
        // clear the sprites and add new randomly selected shapes
        sprites.clear();

        ArrayList<Shape> shapes = getShapes();
        Random randomGen = new Random();

        for(int i = 0; i<nShapes; i++){
            int shapeLoc = randomGen.nextInt(shapes.size());
            Shape shape = shapes.get(shapeLoc);
            Sprite sprite = new Sprite(shape, 0, 0);
            sprite.state = SpriteState.IN_PALETTE;
            sprites.add(sprite);
        }

        System.out.println("Replenished: " + sprites);
    }

    public static void main(String[] args) {
        Palette palette = new Palette();
        System.out.println(palette.shapes);
        System.out.println(palette.sprites);
        palette.doLayout(0, 0, 20);
        System.out.println(palette.sprites);
    }
}
