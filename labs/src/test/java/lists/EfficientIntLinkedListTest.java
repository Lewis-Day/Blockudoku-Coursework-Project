package lists;

public class EfficientIntLinkedListTest extends AbstractIntListTest {

    @Override
    protected IntList createList() {
        return new EfficientIntLinkedList();
    }
}
