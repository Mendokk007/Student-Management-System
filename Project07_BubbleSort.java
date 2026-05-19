/**
 * Project07_BubbleSort.java
 * Sorts the StudentList by name using Bubble Sort.
 * Repeatedly steps through the list, compares adjacent elements,
 * and swaps them if they're in the wrong order.
 * 
 * Compile: javac Student.java StudentList.java Project07_BubbleSort.java
 * Run:     java Project07_BubbleSort
 */
public class Project07_BubbleSort 
{

    /**
     * Bubble sort by name (A to Z).
     * Uses a temporary array approach since StudentList lacks set().
     */
    public static void bubbleSortByName(StudentList list) 
	{
        int n = list.size();
        int swaps = 0;
        int passes = 0;

        // Copy to array for easy swapping
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) 
		{
            arr[i] = list.get(i);
        }

        for (int i = 0; i < n - 1; i++) 
		{
            passes++;
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) 
			{
                // Compare adjacent names (case-insensitive)
                if (arr[j].getName().compareToIgnoreCase(arr[j + 1].getName()) > 0) {
                    // Swap
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            // If no swaps occurred, list is already sorted
            if (!swapped) 
			{
                System.out.println("  Early termination at pass " + passes + " (no swaps)");
                break;
            }
        }

        // Copy sorted array back to a new list and display
        System.out.println("  Passes: " + passes + ", Swaps: " + swaps);
        displaySortedArray(arr);
    }

    public static void displaySortedArray(Student[] arr) 
	{
        System.out.println("\n--- Sorted by Name (Bubble Sort) ---");
        for (int i = 0; i < arr.length; i++) 
		{
            System.out.println("[" + (i + 1) + "] " + arr[i]);
        }
        System.out.println("--- End ---");
    }

    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 7: BUBBLE SORT (BY NAME)      ");
        System.out.println("=========================================\n");

        StudentList list = new StudentList();
        list.add(new Student(401, "Zara", 85.0));
        list.add(new Student(402, "Alex", 90.0));
        list.add(new Student(403, "Maya", 78.0));
        list.add(new Student(404, "Ethan", 92.0));
        list.add(new Student(405, "Liam", 88.0));

        System.out.println("Original list:");
        list.displayAll();

        System.out.println("\n--- Sorting by Name ---");
        bubbleSortByName(list);

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 7 COMPLETE                    ");
        System.out.println("=========================================");
    }
}