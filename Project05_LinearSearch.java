/**
 * Project05_LinearSearch.java
 * Implements linear search on the StudentList to find a student by ID.
 * Searches from index 0 to size-1, checking each element.
 * 
 * Compile: javac Student.java StudentList.java Project05_LinearSearch.java
 * Run:     java Project05_LinearSearch
 */
public class Project05_LinearSearch 
{

    /**
     * Linear search by ID. Returns Student if found, null otherwise.
     * Also counts how many comparisons were made.
     */
    public static Student linearSearch(StudentList list, int targetId) 
	{
        int comparisons = 0;
        for (int i = 0; i < list.size(); i++) 
		{
            comparisons++;
            if (list.get(i).getId() == targetId) 
			{
                System.out.println("  Found after " + comparisons + " comparison(s)");
                return list.get(i);
            }
        }
        System.out.println("  Not found after " + comparisons + " comparison(s)");
        return null;
    }

    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 5: LINEAR SEARCH              ");
        System.out.println("=========================================\n");

        // Populate the list
        StudentList list = new StudentList();
        list.add(new Student(301, "Alice", 85.0));
        list.add(new Student(302, "Bob", 90.0));
        list.add(new Student(303, "Charlie", 78.0));
        list.add(new Student(304, "Diana", 92.0));
        list.add(new Student(305, "Eve", 88.0));

        System.out.println("List contents:");
        list.displayAll();

        // Test 1: Search existing student (first element)
        System.out.println("\n--- Test 1: Search ID 301 (first element) ---");
        Student found = linearSearch(list, 301);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        // Test 2: Search existing student (last element - worst case)
        System.out.println("\n--- Test 2: Search ID 305 (last element, worst case) ---");
        found = linearSearch(list, 305);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        // Test 3: Search non-existent student
        System.out.println("\n--- Test 3: Search ID 999 (not in list) ---");
        found = linearSearch(list, 999);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        // Time complexity analysis
        System.out.println("\n--- Time Complexity ---");
        System.out.println("Best case:  O(1) — found at first position");
        System.out.println("Worst case: O(n) — found at last position or not found");
        System.out.println("n = " + list.size() + " students");

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 5 COMPLETE                    ");
        System.out.println("=========================================");
    }
}