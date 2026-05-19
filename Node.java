/**
 * Node.java
 * A simple node for a singly linked list — stores a Student object
 * and a reference to the next node.
 * Created for Project 11, reused in Project 12 and the Stack class.
 */
public class Node 
{
    public Student data;
    public Node next;

    // Constructor
    public Node(Student data) 
	{
        this.data = data;
        this.next = null;
    }
}