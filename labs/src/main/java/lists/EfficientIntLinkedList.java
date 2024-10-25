package lists;

public class EfficientIntLinkedList implements IntList {
    IntNode head;
    int len;
    IntNode nextAvailableNode;

    public EfficientIntLinkedList() {
        head = null;
        len = 0;
        nextAvailableNode = null;
    }

    public boolean contains(int value) {
        // todo: implement this properly

        IntNode ptr = this.head;

        while(ptr != null){
            if(ptr.value == value){
                return true;
            }
            ptr = ptr.next;
        }
        return false;

    }

    public void append(int value) {

        if(this.head == null){
            this.head = new IntNode(value);
            nextAvailableNode = this.head;
        }
        else{
            nextAvailableNode.next = new IntNode(value);
            nextAvailableNode = nextAvailableNode.next;
        }
        this.len++;
    }

    public int length() {
        return this.len;
    }

    public static void main(String[] args) {
        EfficientIntLinkedList list = new EfficientIntLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        System.out.println(list.contains(2));
        System.out.println(list.contains(4));
        System.out.println(list.length());

    }
}


