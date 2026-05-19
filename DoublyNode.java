/**
 * DoublyNode.java
 * A node for a doubly linked list — stores a Student object
 * and references to both next and previous nodes.
 * Created for Project 13, used in Project 15.
 */
public class DoublyNode {
    public Student data;
    public DoublyNode next;
    public DoublyNode prev;

    // Constructor
    public DoublyNode(Student data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}