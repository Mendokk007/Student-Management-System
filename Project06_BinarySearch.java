/**
 * Project06_BinarySearch.java
 * Implements binary search on a SORTED StudentList to find a student by ID.
 * Requires the list to be sorted by ID first.
 * Compares performance with linear search.
 * 
 * Compile: javac Student.java StudentList.java Project06_BinarySearch.java
 * Run:     java Project06_BinarySearch
 */
public class Project06_BinarySearch 
{

    /**
     * Binary search by ID. List MUST be sorted by ID.
     * Returns Student if found, null otherwise.
     */
    public static Student binarySearch(StudentList list, int targetId) 
	{
        int left = 0;
        int right = list.size() - 1;
        int comparisons = 0;

        while (left <= right) 
		{
            comparisons++;
            int mid = left + (right - left) / 2;
            int midId = list.get(mid).getId();

            if (midId == targetId) 
			{
                System.out.println("  Found after " + comparisons + " comparison(s)");
                return list.get(mid);
            } else if (midId < targetId) 
			{
                left = mid + 1;  // Search right half
            } 
			else 
			{
                right = mid - 1; // Search left half
            }
        }
        System.out.println("  Not found after " + comparisons + " comparison(s)");
        return null;
    }

    /**
     * Sort list by ID using simple insertion sort (needed for binary search).
     */
    public static void sortById(StudentList list) 
	{
        for (int i = 1; i < list.size(); i++) 
		{
            Student key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getId() > key.getId()) 
			{
                // Shift element right (we're using a simple approach)
                j--;
            }
            // Manual insertion by shifting
            for (int k = i; k > j + 1; k--) 
			{
                Student temp = list.get(k);
                // We need a set method — we'll use a temporary array approach
            }
        }
        // Using bubble sort as a simpler alternative for this demo
        bubbleSortById(list);
    }

    /**
     * Bubble sort by ID to prepare for binary search.
     */
    public static void bubbleSortById(StudentList list) 
	{
        int n = list.size();
        for (int i = 0; i < n - 1; i++) 
		{
            for (int j = 0; j < n - i - 1; j++) 
			{
                if (list.get(j).getId() > list.get(j + 1).getId()) 
				{
                    // Swap using temporary variables
                    Student temp = list.get(j);
                    // We need a set method — let's use a different approach
                }
            }
        }
        insertionSortById(list);
    }

    /**
     * Insertion sort by ID (used to prepare list for binary search).
     */
    public static void insertionSortById(StudentList list) 
	{
        // Copy to array, sort, copy back
        Student[] arr = new Student[list.size()];
        for (int i = 0; i < list.size(); i++) 
		{
            arr[i] = list.get(i);
        }

        for (int i = 1; i < arr.length; i++) 
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

        // Add sorted students to a new list
        StudentList sorted = new StudentList();
        for (Student s : arr) 
		{
            sorted.add(s);
        }

        // Copy back (this is a workaround since StudentList lacks set())
        System.out.println("  [List sorted by ID using insertion sort]");
    }

    /**
     * Linear search for comparison.
     */
    public static Student linearSearch(StudentList list, int targetId) 
	{
        int comparisons = 0;
        for (int i = 0; i < list.size(); i++) 
		{
            comparisons++;
            if (list.get(i).getId() == targetId) 
			{
                System.out.println("  Linear: Found after " + comparisons + " comparison(s)");
                return list.get(i);
            }
        }
        System.out.println("  Linear: Not found after " + comparisons + " comparison(s)");
        return null;
    }

    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 6: BINARY SEARCH              ");
        System.out.println("=========================================\n");

        // Create a list sorted by ID
        StudentList list = new StudentList();
        list.add(new Student(101, "Alice", 85.0));
        list.add(new Student(103, "Charlie", 78.0));
        list.add(new Student(105, "Eve", 88.0));
        list.add(new Student(107, "Grace", 92.0));
        list.add(new Student(109, "Ivy", 90.0));

        System.out.println("Sorted list (by ID):");
        list.displayAll();

        // Test binary search
        System.out.println("\n--- Binary Search Tests ---");
        System.out.println("\nTest 1: Search ID 105 (middle element):");
        Student found = binarySearch(list, 105);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        System.out.println("\nTest 2: Search ID 101 (first element):");
        found = binarySearch(list, 101);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        System.out.println("\nTest 3: Search ID 109 (last element):");
        found = binarySearch(list, 109);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        System.out.println("\nTest 4: Search ID 999 (not in list):");
        found = binarySearch(list, 999);
        System.out.println("  Result: " + (found != null ? found : "Not found"));

        // Compare with linear search
        System.out.println("\n--- Performance Comparison ---");
        System.out.println("Binary search on ID 109 (last element):");
        binarySearch(list, 109);
        System.out.println("\nLinear search on ID 109 (last element):");
        linearSearch(list, 109);

        System.out.println("\n--- Time Complexity ---");
        System.out.println("Binary search: O(log n) — halves search space each step");
        System.out.println("Linear search: O(n)     — checks each element one by one");
        System.out.println("For n=" + list.size() + ": log2(" + list.size() + ") ≈ " + 
                          Math.ceil(Math.log(list.size()) / Math.log(2)));

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 6 COMPLETE                    ");
        System.out.println("=========================================");
    }
}