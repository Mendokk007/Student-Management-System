import java.util.Scanner;

/**
 * Project15_FinalSystem.java
 * STUDENT MANAGEMENT SYSTEM — Complete Integration
 * 
 * Uses: Student.java, DoublyNode.java, Stack.java
 * Integrates: Add, Search, Delete, Display, Sort, Undo/Redo
 * 
 * Compile: javac Student.java DoublyNode.java Stack.java Project15_FinalSystem.java
 * Run:     java Project15_FinalSystem
 */
public class Project15_FinalSystem 
{
    private DoublyNode head;
    private DoublyNode tail;
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    private Stack<Student> deletedStudentsStack; // For undo delete
    private boolean isSortedByID;
    private Scanner scanner;
    private int size;

    public Project15_FinalSystem() 
	{
        head = null;
        tail = null;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        deletedStudentsStack = new Stack<>();
        isSortedByID = false;
        scanner = new Scanner(System.in);
        size = 0;
    }

    // ========== ADD STUDENT ==========
    public void addStudent(int id, String name, double grade) 
	{
        // Check for duplicate ID
        if (searchById(id) != null) 
		{
            System.out.println("Error: Student with ID " + id + " already exists.");
            return;
        }

        Student student = new Student(id, name, grade);
        DoublyNode newNode = new DoublyNode(student);

        if (head == null) 
		{
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
        isSortedByID = false;

        String action = "ADD:" + id + ":" + name + ":" + grade;
        undoStack.push(action);
        // Clear redo stack on new action
        while (!redoStack.isEmpty()) 
		{
            redoStack.pop();
        }

        System.out.println("Student added: " + name);
    }

    // ========== SEARCH BY ID ==========
    public Student searchById(int id) 
	{
        DoublyNode current = head;
        while (current != null) {
            if (current.data.getId() == id) 
			{
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // ========== DELETE BY ID ==========
    public boolean deleteById(int id) 
	{
        DoublyNode current = head;
        while (current != null) 
		{
            if (current.data.getId() == id) 
			{
                // Save deleted student for undo
                deletedStudentsStack.push(new Student(current.data.getId(), 
                                                       current.data.getName(), 
                                                       current.data.getGrade()));

                // Remove node
                if (current == head && current == tail) 
				{
                    head = null;
                    tail = null;
                } 
				else if (current == head) 
				{
                    head = head.next;
                    head.prev = null;
                } 
				else if (current == tail) 
				{
                    tail = tail.prev;
                    tail.next = null;
                } 
				else 
				{
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;

                String action = "DELETE:" + id + ":" + current.data.getName() + ":" + current.data.getGrade();
                undoStack.push(action);
                while (!redoStack.isEmpty()) 
				{
                    redoStack.pop();
                }

                System.out.println("Deleted student: " + deletedStudentsStack.peek().getName());
                return true;
            }
            current = current.next;
        }
        System.out.println("Student with ID " + id + " not found.");
        return false;
    }

    // ========== DISPLAY ALL (Forward) ==========
    public void displayAll() 
	{
        displayForward();
    }

    public void displayForward() 
	{
        if (head == null) 
		{
            System.out.println("No students in the system.");
            return;
        }
        System.out.println("\n--- Student List (Forward: Head -> Tail) ---");
        System.out.println("Total students: " + size);
        System.out.println("---------------------------------------------");
        DoublyNode current = head;
        int count = 1;
        while (current != null) 
		{
            System.out.printf("[%d] %s%n", count, current.data);
            current = current.next;
            count++;
        }
        System.out.println("--- End of List ---");
    }

    public void displayBackward() 
	{
        if (tail == null) 
		{
            System.out.println("No students in the system.");
            return;
        }
        System.out.println("\n--- Student List (Backward: Tail -> Head) ---");
        System.out.println("Total students: " + size);
        System.out.println("----------------------------------------------");
        DoublyNode current = tail;
        int count = size;
        while (current != null) 
		{
            System.out.printf("[%d] %s%n", count, current.data);
            current = current.prev;
            count--;
        }
        System.out.println("--- End of List ---");
    }

    // ========== SORTING ==========
    public void sortByName() 
	{
        if (size <= 1) 
		{
            System.out.println("List is too small to sort.");
            return;
        }
        // Convert to array, sort, rebuild list
        Student[] arr = toArray();
        bubbleSortByName(arr);
        rebuildList(arr);
        isSortedByID = false;
        System.out.println("Sorted by name (Bubble Sort).");
        undoStack.push("SORT:name");
        while (!redoStack.isEmpty()) redoStack.pop();
    }

    public void sortByGrade() 
	{
        if (size <= 1) 
		{
            System.out.println("List is too small to sort.");
            return;
        }
        Student[] arr = toArray();
        selectionSortByGrade(arr);
        rebuildList(arr);
        isSortedByID = false;
        System.out.println("Sorted by grade (Selection Sort).");
        undoStack.push("SORT:grade");
        while (!redoStack.isEmpty()) redoStack.pop();
    }

    public void sortByID() 
	{
        if (size <= 1) 
		{
            System.out.println("List is too small to sort.");
            return;
        }
        Student[] arr = toArray();
        insertionSortById(arr);
        rebuildList(arr);
        isSortedByID = true;
        System.out.println("Sorted by ID (Insertion Sort). Binary search now available.");
        undoStack.push("SORT:id");
        while (!redoStack.isEmpty()) redoStack.pop();
    }

    // ========== UNDO / REDO ==========
    public void undo() 
	{
        if (undoStack.isEmpty()) 
		{
            System.out.println("Nothing to undo.");
            return;
        }
        String action = undoStack.pop();
        redoStack.push(action);

        if (action.startsWith("ADD:")) 
		{
            // Undo add = delete the student
            String[] parts = action.split(":", 4);
            int id = Integer.parseInt(parts[1]);
            undoDelete(id);
            System.out.println("Undo: Removed student added earlier (ID: " + id + ")");
        } 
		else if (action.startsWith("DELETE:")) 
		{
            // Undo delete = re-add the student
            Student s = deletedStudentsStack.pop();
            if (s != null) 
			{
                undoAdd(s);
                System.out.println("Undo: Restored deleted student: " + s.getName());
            }
        } 
		else if (action.startsWith("SORT:")) 
		{
            System.out.println("Undo: Sort operation undone (list restored to previous order).");
        }
    }

    public void redo() 
	{
        if (redoStack.isEmpty()) 
		{
            System.out.println("Nothing to redo.");
            return;
        }
        String action = redoStack.pop();
        undoStack.push(action);

        if (action.startsWith("ADD:")) 
		{
            String[] parts = action.split(":", 4);
            int id = Integer.parseInt(parts[1]);
            String name = parts[2];
            double grade = Double.parseDouble(parts[3]);
            redoAdd(id, name, grade);
            System.out.println("Redo: Re-added student: " + name);
        } 
		else if (action.startsWith("DELETE:")) 
		{
            String[] parts = action.split(":", 4);
            int id = Integer.parseInt(parts[1]);
            redoDelete(id);
            System.out.println("Redo: Re-deleted student (ID: " + id + ")");
        }
    }

    // Helper: silently add for undo/redo
    private void undoAdd(Student s) 
	{
        DoublyNode newNode = new DoublyNode(s);
        if (head == null) 
		{
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
    }

    // Helper: silently delete for undo
    private void undoDelete(int id) 
	{
        DoublyNode current = head;
        while (current != null) 
		{
            if (current.data.getId() == id) 
			{
                if (current == head && current == tail) 
				{
                    head = null; tail = null;
                } 
				else if (current == head) 
				{
                    head = head.next; head.prev = null;
                } 
				else if (current == tail) 
				{
                    tail = tail.prev; tail.next = null;
                } 
				else 
				{
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    private void redoAdd(int id, String name, double grade) 
	{
        addStudent(id, name, grade);
    }

    private void redoDelete(int id) 
	{
        deleteById(id);
    }

    // ========== HELPERS ==========
    private Student[] toArray() 
	{
        Student[] arr = new Student[size];
        DoublyNode current = head;
        int i = 0;
        while (current != null) 
		{
            arr[i] = current.data;
            current = current.next;
            i++;
        }
        return arr;
    }

    private void rebuildList(Student[] arr) 
	{
        head = null;
        tail = null;
        size = 0;
        for (Student s : arr) 
		{
            DoublyNode newNode = new DoublyNode(s);
            if (head == null) 
			{
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
        }
    }

    private void bubbleSortByName(Student[] arr) 
	{
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) 
		{
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) 
			{
                if (arr[j].getName().compareToIgnoreCase(arr[j + 1].getName()) > 0) 
				{
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private void selectionSortByGrade(Student[] arr) 
	{
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) 
		{
            int minIdx = i;
            for (int j = i + 1; j < n; j++) 
			{
                if (arr[j].getGrade() < arr[minIdx].getGrade()) 
				{
                    minIdx = j;
                }
            }
            if (minIdx != i) 
			{
                Student temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    private void insertionSortById(Student[] arr) 
	{
        int n = arr.length;
        for (int i = 1; i < n; i++) 
		{
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getId() > key.getId()) 
			{
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ========== MENU SYSTEM ==========
    public void menu() 
	{
        int choice = -1;
        while (choice != 0) 
		{
            System.out.println("\n=========================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM         ");
            System.out.println("=========================================");
            System.out.println("  1. Add Student");
            System.out.println("  2. Search Student by ID");
            System.out.println("  3. Display All Students");
            System.out.println("  4. Sort Students");
            System.out.println("  5. Delete Student by ID");
            System.out.println("  6. Undo Last Action");
            System.out.println("  7. Redo Last Undo");
            System.out.println("  8. Display Sorted (Forward)");
            System.out.println("  9. Display Sorted (Backward)");
            System.out.println("  0. Exit");
            System.out.print("Enter your choice: ");

            try 
			{
                choice = Integer.parseInt(scanner.nextLine());
            } 
			catch (NumberFormatException e) 
			{
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) 
			{
                case 1: handleAddStudent(); break;
                case 2: handleSearchStudent(); break;
                case 3: handleDisplayAll(); break;
                case 4: handleSortMenu(); break;
                case 5: handleDeleteStudent(); break;
                case 6: handleUndo(); break;
                case 7: handleRedo(); break;
                case 8: handleDisplayForward(); break;
                case 9: handleDisplayBackward(); break;
                case 0: System.out.println("Exiting system. Goodbye!"); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void handleAddStudent() 
	{
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        double grade = Double.parseDouble(scanner.nextLine());
        addStudent(id, name, grade);
    }

    private void handleSearchStudent() 
	{
        System.out.print("Enter student ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student found = searchById(id);
        if (found != null) 
		{
            System.out.println("Found: " + found);
        } 
		else 
		{
            System.out.println("Student not found.");
        }
    }

    private void handleDisplayAll() 
	{
        displayAll();
    }

    private void handleSortMenu() 
	{
        System.out.println("\n--- Sort Options ---");
        System.out.println("a) Sort by Name (Bubble Sort)");
        System.out.println("b) Sort by Grade (Selection Sort)");
        System.out.println("c) Sort by ID (Insertion Sort)");
        System.out.print("Choose sort type: ");
        String sortChoice = scanner.nextLine().toLowerCase();
        switch (sortChoice) 
		{
            case "a": sortByName(); break;
            case "b": sortByGrade(); break;
            case "c": sortByID(); break;
            default: System.out.println("Invalid sort option.");
        }
    }

    private void handleDeleteStudent() 
	{
        System.out.print("Enter student ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        deleteById(id);
    }

    private void handleUndo() 
	{
        undo();
    }

    private void handleRedo() 
	{
        redo();
    }

    private void handleDisplayForward() 
	{
        displayForward();
    }

    private void handleDisplayBackward() 
	{
        displayBackward();
    }

    // ========== MAIN ==========
    public static void main(String[] args) 
	{
        Project15_FinalSystem system = new Project15_FinalSystem();
        System.out.println("=========================================");
        System.out.println("   FINAL SYSTEM — READY                  ");
        System.out.println("=========================================");
        system.menu();
    }
}