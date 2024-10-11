package lists;

public class GenericLinkedListRecordTest extends AbstractIntListTest {

    @Override
    protected IntList createList() {

        // return an GenIntListWrapper object that wraps around a GenericArrayList object
        return new GenIntListWrapper(new GenericLinkedListRecord<Integer>());
    }
}

