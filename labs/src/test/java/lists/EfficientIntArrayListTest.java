package lists;

public class EfficientIntArrayListTest extends AbstractIntListTest {

    @Override
    protected IntList createList() {
        return new EfficientIntArrayList();
    }
}