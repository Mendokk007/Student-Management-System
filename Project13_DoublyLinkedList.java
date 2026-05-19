/**
 * Project13_DoublyLinkedList.java
 * Demonstrates doubly linked list operations:
 * - Insert at beginning (O(1))
 * - Insert at end (O(1) with tail pointer)
 * - Delete from beginning (O(1))
 * - Delete from end (O(1))
 * - Forward traversal (head -> tail)
 * - Backward traversal (tail -> head)
 * 
 * Compile: javac Student.java DoublyNode.java Project13_DoublyLinkedList.java
 * Run:     java Project13_DoublyLinkedList
 */
public class Project13_DoublyLinkedList 
{
    private DoublyNode head;
    private DoublyNode tail;
    private int size;

    public Project13_DoublyLinkedList() 
	{
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Insert at the beginning of the list.
     * O(1) — update head and handle prev pointers.
     */
    public void insertAtBeginning(Student student) 
	{
        DoublyNode newNode = new DoublyNode(student);

        if (head == null) 
		{
            // List is empty — new node is both head and tail
            head = newNode;
            tail = newNode;
        } 
		else 
		{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        System.out.println("Inserted at beginning: " + student.getName());
    }

    /**
     * Insert at the end of the list.
     * O(1) — use tail pointer, no traversal needed!
     */
    public void insertAtEnd(Student student) 
	{
        DoublyNode newNode = new DoublyNode(student);

        if (tail == null) 
		{
            // List is empty
            head = newNode;
            tail = newNode;
        } 
		else 
		{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        System.out.println("Inserted at end: " + student.getName());
    }

    /**
     * Delete the first node (head).
     * O(1) — just move head forward.
     */
    public boolean deleteFromBeginning() 
	{
        if (head == null) 
		{
            System.out.println("List is empty. Cannot delete.");
            return false;
        }

        System.out.println("Deleted from beginning: " + head.data.getName());

        if (head == tail) 
		{
            // Only one node in the list
            head = null;
            tail = null;
        } 
		else 
		{
            head = head.next;
            head.prev = null;
        }
        size--;
        return true;
    }

    /**
     * Delete the last node (tail).
     * O(1) — use tail.prev, no traversal needed!
     * This is the key advantage over singly linked list.
     */
    public boolean deleteFromEnd() 
	{
        if (tail == null) 
		{
            System.out.println("List is empty. Cannot delete.");
            return false;
        }

        System.out.println("Deleted from end: " + tail.data.getName());

        if (head == tail) 
		{
            // Only one node
            head = null;
            tail = null;
        } 
		else 
		{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return true;
    }

    /**
     * Display list from head to tail (forward).
     */
    public void displayForward() 
	{
        if (head == null) 
		{
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("\n--- Forward Traversal (Head -> Tail) ---");
        DoublyNode current = head;
        int position = 1;
        while (current != null) 
		{
            System.out.println("[" + position + "] " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("--- End Forward (" + size + " students) ---");
    }

    /**
     * Display list from tail to head (backward).
     * This is ONLY possible with a doubly linked list.
     */
    public void displayBackward() 
	{
        if (tail == null) 
		{
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("\n--- Backward Traversal (Tail -> Head) ---");
        DoublyNode current = tail;
        int position = size;
        while (current != null) 
		{
            System.out.println("[" + position + "] " + current.data);
            current = current.prev;
            position--;
        }
        System.out.println("--- End Backward (" + size + " students) ---");
    }

    public int getSize() 
	{
        return size;
    }

    public boolean isEmpty() 
	{
        return size == 0;
    }

    // ========== MAIN METHOD ==========
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("  PROJECT 13: DOUBLY LINKED LIST         ");
        System.out.println("=========================================\n");

        Project13_DoublyLinkedList list = new Project13_DoublyLinkedList();

        // Test 1: Insert at beginning
        System.out.println("--- Test 1: Insert at Beginning ---");
        list.insertAtBeginning(new Student(501, "Wanda Maximoff", 89.0));
        list.insertAtBeginning(new Student(502, "Vision Stone", 92.0));
        list.insertAtBeginning(new Student(503, "Sam Wilson", 85.5));
        list.displayForward();
        list.displayBackward();

        // Test 2: Insert at end (O(1) with tail pointer!)
        System.out.println("\n--- Test 2: Insert at End (O(1)) ---");
        list.insertAtEnd(new Student(504, "Bucky Barnes", 78.0));
        list.insertAtEnd(new Student(505, "Carol Danvers", 96.0));
        list.displayForward();
        list.displayBackward();

        // Test 3: Delete from beginning (O(1))
        System.out.println("\n--- Test 3: Delete from Beginning ---");
        list.deleteFromBeginning();
        list.deleteFromBeginning();
        list.displayForward();

        // Test 4: Delete from end (O(1)) — KEY ADVANTAGE
        System.out.println("\n--- Test 4: Delete from End (O(1)) ---");
        System.out.println("  (Singly linked list would need O(n) traversal!)");
        list.deleteFromEnd();
        list.displayForward();
        list.displayBackward();

        // Test 5: Show both traversals
        System.out.println("\n--- Test 5: Final State ---");
        System.out.println("Size: " + list.getSize());
        list.displayForward();
        list.displayBackward();

        // Test 6: Edge case — delete until empty
        System.out.println("\n--- Test 6: Delete All Remaining ---");
        while (!list.isEmpty()) 
		{
            list.deleteFromBeginning();
        }
        System.out.println("Is list empty? " + list.isEmpty());
        list.displayForward();

        // Test 7: Re-insert after empty
        System.out.println("\n--- Test 7: Re-insert After Empty ---");
        list.insertAtEnd(new Student(506, "Nick Fury", 99.0));
        list.displayForward();
        list.displayBackward();

        System.out.println("\n=========================================");
        System.out.println("  DOUBLY LINKED LIST — COMPLETE          ");
        System.out.println("=========================================");
    }
}