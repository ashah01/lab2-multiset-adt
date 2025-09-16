
// Node is only used inside the LinkedList class, so we define it in the same file;
// there can only be one public class in a file, but there can also be non-public classes.
class Node {
    int item;
    Node next;
    Node(int item) {
        this.item = item;
    }
}


public class LinkedListMultiSet extends MultiSet {

    // a linked list initially is empty
    private Node front;
    private int size;


    public void add(int item) {
        Node newNode =  new Node(item);
        newNode.next = front;
        front = newNode;
        size += 1;
    }

    public void remove(int item) {
        if (front == null) return;

        // if the head matches, remove it
        if (front.item == item) {
            front = front.next;
            size--;
        }

        // otherwise find the node to unlink
        Node prev = front;
        Node curr = front.next;
        while (curr != null) {
            if (curr.item == item) {
                prev.next = curr.next;
                size--;
            }
            prev = curr;
            curr = curr.next;
        }

    }

    public boolean contains(int item) {
        Node curr = front;
        while (curr != null) {
            if (curr.item == item) return true;
            curr = curr.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0; // or: return front == null;
    }


    public int count(int item) {
        int c = 0;
        Node curr = front;
        while (curr != null) {
            if (curr.item == item) c++;
            curr = curr.next;
        }
        return c;
    }

    public int size() {
        return size;
    }
}
