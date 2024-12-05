package reflection.uml;

import java.util.ArrayList;
import java.util.List;
import blocks.*;
import reflection.uml.ReflectionData.*;

public class MermaidWriter {
    final static String superclass = " --|> ";
    final static String dependency = " ..> ";
    String writeMermaid(DiagramData diagramData) {
        // write out the diagram data in Mermaid format
        // first process each class
        StringBuilder sb = new StringBuilder();
        sb.append("classDiagram\n");
        for (ClassData cd : diagramData.classes()) {
            sb.append("class ").append(cd.className());
            sb.append(" {\n");
            for (FieldData fd : cd.fields()) {
                sb.append(fd.name()).append(" : ").append(fd.type()).append("\n");
            }
            for (MethodData md : cd.methods()) {
                sb.append(md.name()).append("() : ").append(md.returnType()).append("\n");
            }
            sb.append("}\n");
        }

        // then to each link
        for (Link l : diagramData.links()) {
            sb.append(l.from());
            switch (l.type()) {
                case SUPERCLASS -> sb.append(superclass);
                case DEPENDENCY -> sb.append(dependency);
            }
            sb.append(l.to()).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<Class<?>> classes = new ArrayList<>();
        // add in all the classes we wish to generate UML for
        classes.add(blocks.BlockShapes.class);
        classes.add(blocks.Controller.class);
        classes.add(blocks.GameView.class);
        classes.add(blocks.Model2dArray.class);
        classes.add(blocks.ModelSet.class);
        classes.add(blocks.Palette.class);
        classes.add(blocks.RegionHelper.class);
        classes.add(State2dArray.class);
        classes.add(StateSet.class);
        classes.add(ModelInterface.class);
        classes.add(BlockShapes.Sprite.class);
        classes.add(BlockShapes.Shape.class);
        classes.add(BlockShapes.Cell.class);
        classes.add(BlockShapes.Piece.class);
        classes.add(BlockShapes.SpriteState.class);
        classes.add(BlockShapes.PixelLoc.class);


//        System.out.println(classes);
        System.out.println();
        DiagramData dd = new ProcessClasses().process(classes);
        System.out.println();
        System.out.println(new MermaidWriter().writeMermaid(dd));
    }
}

