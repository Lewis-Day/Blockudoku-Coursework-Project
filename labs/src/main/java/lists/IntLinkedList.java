package lists;

public class IntLinkedList implements IntList {
    IntNode head;
    int len;

    public IntLinkedList() {
        head = null;
        len = 0;
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

        if(len == 0){
            this.head = new IntNode(value);
        }

        else{
            IntNode ptr = this.head;

            while(ptr.next != null){
                ptr = ptr.next;
            }

            ptr.next = new IntNode(value);
        }

        this.len = this.len + 1;

    }

    public int length() {
        return this.len;
    }

    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        System.out.println(list.contains(2));
        System.out.println(list.contains(4));
        System.out.println(list.length());
    }
}

// a IntNode for each element in LinkedList
class IntNode {
    int value;
    IntNode next;

    public IntNode(int value) {
        this.value = value;
        this.next = null;
    }
}
