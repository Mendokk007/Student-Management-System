/**
 * Project12_LinkedListOperations.java
 * Extends singly linked list with:
 * - Search by ID (linear search on linked list)
 * - Delete by ID (find node, relink pointers)
 * 
 * Compile: javac Student.java Node.java Project12_LinkedListOperations.java
 * Run:     java Project12_LinkedListOperations
 */
public class Project12_LinkedListOperations 
{
    private Node head;

    public Project12_LinkedListOperations() 
	{
        head = null;
    }

    /**
     * Insert at end (helper to populate the list).
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
    }

    /**
     * Linear search by student ID.
     * O(n) — must traverse potentially entire list.
     * @return Student if found, null otherwise
     */
    public Student searchById(int id) 
	{
        Node current = head;
        int nodesChecked = 0;

        while (current != null) 
		{
            nodesChecked++;
            if (current.data.getId() == id) 
			{
                System.out.println("  -> Found after checking " + nodesChecked + " node(s)");
                return current.data;
            }
            current = current.next;
        }
        System.out.println("  -> Checked all " + nodesChecked + " node(s). Not found.");
        return null;
    }

    /**
     * Delete a student by ID.
     * Three cases: empty list, delete head, delete middle/tail.
     * @return true if deleted, false if not found
     */
    public boolean deleteById(int id) 
	{
        // Case 1: Empty list
        if (head == null) 
		{
            System.out.println("  -> List is empty. Nothing to delete.");
            return false;
        }

        // Case 2: Delete head node
        if (head.data.getId() == id) 
		{
            System.out.println("  -> Deleting head node: " + head.data.getName());
            head = head.next;  // Head now points to the second node
            return true;
        }

        // Case 3: Delete middle or tail node
        Node current = head;
        while (current.next != null) 
		{
            if (current.next.data.getId() == id) 
			{
                System.out.println("  -> Deleting node: " + current.next.data.getName());
                // Bypass the node to be deleted
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        System.out.println("  -> Student with ID " + id + " not found.");
        return false;
    }

    /**
     * Display all students.
     */
    public void display() 
	{
        if (head == null) 
		{
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("\n--- Current Student List ---");
        Node current = head;
        int position = 1;
        while (current != null) 
		{
            System.out.println("[" + position + "] " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("--- End of List (" + (position - 1) + " students) ---");
    }

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

    // ========== MAIN METHOD ==========
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("  PROJECT 12: LINKED LIST OPERATIONS     ");
        System.out.println("=========================================\n");

        Project12_LinkedListOperations list = new Project12_LinkedListOperations();

        // Populate the list
        System.out.println("--- Populating List ---");
        list.insertAtEnd(new Student(401, "Peter Parker", 87.5));
        list.insertAtEnd(new Student(402, "Tony Stark", 95.0));
        list.insertAtEnd(new Student(403, "Steve Rogers", 82.0));
        list.insertAtEnd(new Student(404, "Natasha Romanoff", 91.0));
        list.insertAtEnd(new Student(405, "Thor Odinson", 78.5));
        list.display();

        // Test 1: Search for existing student
        System.out.println("\n--- Test 1: Search for Existing ID (403) ---");
        Student found = list.searchById(403);
        if (found != null) 
		{
            System.out.println("  Result: " + found);
        }

        // Test 2: Search for non-existent student
        System.out.println("\n--- Test 2: Search for Non-Existent ID (999) ---");
        found = list.searchById(999);
        if (found != null) 
		{
            System.out.println("  Result: " + found);
        }

        // Test 3: Delete middle node
        System.out.println("\n--- Test 3: Delete Middle Node (ID 403) ---");
        boolean deleted = list.deleteById(403);
        System.out.println("  Deletion successful: " + deleted);
        list.display();

        // Test 4: Delete head node
        System.out.println("\n--- Test 4: Delete Head Node (ID 401) ---");
        deleted = list.deleteById(401);
        System.out.println("  Deletion successful: " + deleted);
        list.display();

        // Test 5: Delete tail node
        System.out.println("\n--- Test 5: Delete Tail Node (ID 405) ---");
        deleted = list.deleteById(405);
        System.out.println("  Deletion successful: " + deleted);
        list.display();

        // Test 6: Try to delete from empty list (after removing all)
        System.out.println("\n--- Test 6: Delete Non-Existent ID (999) ---");
        deleted = list.deleteById(999);
        System.out.println("  Deletion successful: " + deleted);

        // Test 7: Show remaining students
        System.out.println("\n--- Test 7: Final List ---");
        list.display();

        // Test 8: Search after deletions
        System.out.println("\n--- Test 8: Search for Deleted Student (403) ---");
        found = list.searchById(403);
        if (found != null) 
		{
            System.out.println("  Result: " + found);
        }

        System.out.println("\n=========================================");
        System.out.println("  LINKED LIST OPERATIONS — COMPLETE      ");
        System.out.println("=========================================");
    }
}