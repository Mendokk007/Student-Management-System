/**
 * Project03_StudentADT.java
 * Tests the Student class by creating objects, using getters/setters,
 * and displaying student information.
 * 
 * Compile: javac Student.java Project03_StudentADT.java
 * Run:     java Project03_StudentADT
 */
public class Project03_StudentADT 
{
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 3: STUDENT ADT TESTING        ");
        System.out.println("=========================================\n");

        // Test 1: Create Student objects using the constructor
        System.out.println("--- Test 1: Creating Student Objects ---");
        Student s1 = new Student(101, "Alice Johnson", 92.5);
        Student s2 = new Student(102, "Bob Smith", 85.0);
        Student s3 = new Student(103, "Charlie Brown", 78.3);

        // Test 2: Display students using toString()
        System.out.println("\n--- Test 2: Displaying Students (toString) ---");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        // Test 3: Test individual getters
        System.out.println("\n--- Test 3: Testing Getters ---");
        System.out.println("Student 1 ID:    " + s1.getId());
        System.out.println("Student 1 Name:  " + s1.getName());
        System.out.println("Student 1 Grade: " + s1.getGrade());

        System.out.println("Student 2 ID:    " + s2.getId());
        System.out.println("Student 2 Name:  " + s2.getName());
        System.out.println("Student 2 Grade: " + s2.getGrade());

        // Test 4: Test setters (important for undo/redo later)
        System.out.println("\n--- Test 4: Testing Setters ---");
        System.out.println("Before update: " + s3);
        s3.setName("Charlie Updated");
        s3.setGrade(88.8);
        System.out.println("After update:  " + s3);

        // Test 5: Store students in an array (preview of Project 4)
        System.out.println("\n--- Test 5: Student Array (Preview of Project 4) ---");
        Student[] students = new Student[3];
        students[0] = s1;
        students[1] = s2;
        students[2] = s3;

        System.out.println("All students in array:");
        for (int i = 0; i < students.length; i++) 
		{
            System.out.println("  [" + i + "] " + students[i]);
        }

        // Test 6: Search by ID in array (preview of Project 5)
        System.out.println("\n--- Test 6: Linear Search by ID (Preview of Project 5) ---");
        int searchId = 102;
        Student found = null;
        for (int i = 0; i < students.length; i++) 
		{
            if (students[i].getId() == searchId) 
			{
                found = students[i];
                break;
            }
        }
        if (found != null) 
		{
            System.out.println("Found student with ID " + searchId + ": " + found);
        } 
		else 
		{
            System.out.println("Student with ID " + searchId + " not found.");
        }

        // Test search for non-existent ID
        searchId = 999;
        found = null;
        for (int i = 0; i < students.length; i++) 
		{
            if (students[i].getId() == searchId) 
			{
                found = students[i];
                break;
            }
        }
        if (found != null) 
		{
            System.out.println("Found student with ID " + searchId + ": " + found);
        } 
		else 
		{
            System.out.println("Student with ID " + searchId + " not found.");
        }

        System.out.println("\n=========================================");
        System.out.println("   ALL TESTS COMPLETED SUCCESSFULLY      ");
        System.out.println("=========================================");
    }
}