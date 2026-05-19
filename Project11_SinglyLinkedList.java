/**
 * Project11_SinglyLinkedList.java
 * Demonstrates singly linked list operations:
 * - Insert at beginning
 * - Insert at end
 * - Display all nodes
 * - Count nodes
 * 
 * Compile: javac Student.java Node.java Project11_SinglyLinkedList.java
 * Run:     java Project11_SinglyLinkedList
 */
public class Project11_SinglyLinkedList 
{
    private Node head;

    public Project11_SinglyLinkedList() 
	{
        head = null;
    }

    /**
     * Insert a new student at the beginning of the list.
     * O(1) operation — no traversal needed.
     */
    public void insertAtBeginning(Student student) 
	{
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        System.out.println("Inserted at beginning: " + student.getName());
    }

    /**
     * Insert a new student at the end of the list.
     * O(n) operation — must traverse to the last node.
     */
    public void insertAtEnd(Student student) 
	{
        Node newNode = new Node(student);

        if (head == null) 
		{
            head = newNode;
        } 
		else 
		{
            Node current = head;
            while (current.next != null) 
			{
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Inserted at end: " + student.getName());
    }

    /**
     * Display all students in the list from head to end.
     */
    public void display() 
	{
        if (head == null) 
		{
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("\n--- Student List (Singly Linked) ---");
        Node current = head;
        int position = 1;
        while (current != null) 
		{
            System.out.println("[" + position + "] " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("--- End of List ---");
    }

    /**
     * Count the number of nodes in the list.
     */
    public int size() 
	{
        int count = 0;
        Node current = head;
        while (current != null) 
		{
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Check if the list is empty.
     */
    public boolean isEmpty() 
	{
        return head == null;
    }

    // ========== MAIN METHOD ==========
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 11: SINGLY LINKED LIST        ");
        System.out.println("=========================================\n");

        Project11_SinglyLinkedList list = new Project11_SinglyLinkedList();

        // Test 1: Check if empty
        System.out.println("--- Test 1: Empty List Check ---");
        System.out.println("Is list empty? " + list.isEmpty());
        System.out.println("Size: " + list.size());
        list.display();

        // Test 2: Insert at beginning
        System.out.println("\n--- Test 2: Insert at Beginning ---");
        list.insertAtBeginning(new Student(301, "Diana Prince", 88.0));
        list.insertAtBeginning(new Student(302, "Bruce Wayne", 91.5));
        list.insertAtBeginning(new Student(303, "Clark Kent", 95.0));
        list.display();
        System.out.println("Size: " + list.size());

        // Test 3: Insert at end
        System.out.println("\n--- Test 3: Insert at End ---");
        list.insertAtEnd(new Student(304, "Barry Allen", 79.0));
        list.insertAtEnd(new Student(305, "Hal Jordan", 84.5));
        list.display();
        System.out.println("Size: " + list.size());

        // Test 4: Show traversal order (important for understanding linked lists)
        System.out.println("\n--- Test 4: Understanding Traversal ---");
        System.out.println("Notice: insertAtBeginning reverses the order.");
        System.out.println("The last student inserted at beginning appears first.");
        System.out.println("Insert at end maintains insertion order at the tail.");

        // Test 5: Empty check after adding
        System.out.println("\n--- Test 5: Final State ---");
        System.out.println("Is list empty? " + list.isEmpty());
        System.out.println("Final size: " + list.size());
        list.display();

        System.out.println("\n=========================================");
        System.out.println("   SINGLY LINKED LIST — COMPLETE         ");
        System.out.println("=========================================");
    }
}