/**
 * Project04_DynamicStudentList.java
 * Tests the dynamic array by adding students beyond initial capacity.
 * 
 * Compile: javac Student.java StudentList.java Project04_DynamicStudentList.java
 * Run:     java Project04_DynamicStudentList
 */
public class Project04_DynamicStudentList 
{
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 4: DYNAMIC STUDENT LIST       ");
        System.out.println("=========================================\n");

        StudentList list = new StudentList();
        System.out.println("Initial capacity: " + list.capacity());

        // Add 6 students (initial capacity is 2, so it will resize multiple times)
        System.out.println("\n--- Adding Students ---");
        list.add(new Student(201, "Mia Santos", 88.5));
        list.add(new Student(202, "Leo Cruz", 91.0));
        list.add(new Student(203, "Zoe Reyes", 76.5));  // Triggers first resize (2->4)
        list.add(new Student(204, "Max Garcia", 84.0));
        list.add(new Student(205, "Ava Mendoza", 93.5)); // Triggers second resize (4->8)
        list.add(new Student(206, "Kai Lopez", 79.0));

        // Display all
        System.out.println("\n--- Displaying All Students ---");
        list.displayAll();

        // Show final stats
        System.out.println("\n--- List Statistics ---");
        System.out.println("Students stored: " + list.size());
        System.out.println("Current capacity: " + list.capacity());
        System.out.println("Wasted space: " + (list.capacity() - list.size()) + " slots");

        // Test get method
        System.out.println("\n--- Testing get() ---");
        System.out.println("Student at index 2: " + list.get(2));
        System.out.println("Student at index 10 (out of bounds): " + list.get(10));

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 4 COMPLETE                    ");
        System.out.println("=========================================");
    }
}