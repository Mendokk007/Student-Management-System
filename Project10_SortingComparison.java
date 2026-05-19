/**
 * Project10_SortingComparison.java
 * Compares the performance of all three sorting algorithms
 * by timing how long each takes to sort the same data.
 * 
 * Compile: javac Student.java StudentList.java Project10_SortingComparison.java
 * Run:     java Project10_SortingComparison
 */
public class Project10_SortingComparison 
{

    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 10: SORTING COMPARISON        ");
        System.out.println("=========================================\n");

        // Create a list with students in random order
        StudentList original = new StudentList();
        original.add(new Student(705, "Zara", 85.0));
        original.add(new Student(702, "Alex", 92.0));
        original.add(new Student(708, "Maya", 78.5));
        original.add(new Student(701, "Ethan", 91.0));
        original.add(new Student(704, "Liam", 88.0));
        original.add(new Student(709, "Nora", 81.0));
        original.add(new Student(703, "Owen", 95.0));
        original.add(new Student(706, "Paula", 76.0));

        System.out.println("Original list (8 students):");
        original.displayAll();

        int n = original.size();

        // --- Bubble Sort by Name ---
        System.out.println("\n=========================================");
        System.out.println("  1. BUBBLE SORT (by Name)               ");
        System.out.println("=========================================");
        Student[] arrBubble = copyToArray(original);
        long start = System.nanoTime();
        bubbleSortByName(arrBubble);
        long end = System.nanoTime();
        double timeBubble = (end - start) / 1_000_000.0;
        System.out.println("  Time: " + timeBubble + " ms");
        System.out.println("  Time Complexity: O(n²) — " + (n * n) + " max comparisons");
        displayArray(arrBubble, "Bubble Sort Result");

        // --- Selection Sort by Grade ---
        System.out.println("\n=========================================");
        System.out.println("  2. SELECTION SORT (by Grade)           ");
        System.out.println("=========================================");
        Student[] arrSelection = copyToArray(original);
        start = System.nanoTime();
        selectionSortByGrade(arrSelection);
        end = System.nanoTime();
        double timeSelection = (end - start) / 1_000_000.0;
        System.out.println("  Time: " + timeSelection + " ms");
        System.out.println("  Time Complexity: O(n²) — " + (n * n) + " max comparisons");
        displayArray(arrSelection, "Selection Sort Result");

        // --- Insertion Sort by ID ---
        System.out.println("\n=========================================");
        System.out.println("  3. INSERTION SORT (by ID)              ");
        System.out.println("=========================================");
        Student[] arrInsertion = copyToArray(original);
        start = System.nanoTime();
        insertionSortById(arrInsertion);
        end = System.nanoTime();
        double timeInsertion = (end - start) / 1_000_000.0;
        System.out.println("  Time: " + timeInsertion + " ms");
        System.out.println("  Time Complexity: O(n²) worst, O(n) best");
        displayArray(arrInsertion, "Insertion Sort Result");

        // --- Summary ---
        System.out.println("\n=========================================");
        System.out.println("   PERFORMANCE SUMMARY                   ");
        System.out.println("=========================================");
        System.out.printf("  Bubble Sort:    %.4f ms%n", timeBubble);
        System.out.printf("  Selection Sort: %.4f ms%n", timeSelection);
        System.out.printf("  Insertion Sort: %.4f ms%n", timeInsertion);

        String fastest;
        if (timeBubble <= timeSelection && timeBubble <= timeInsertion) {
            fastest = "Bubble Sort";
        } else if (timeSelection <= timeBubble && timeSelection <= timeInsertion) {
            fastest = "Selection Sort";
        } else {
            fastest = "Insertion Sort";
        }
        System.out.println("\n  Fastest: " + fastest);
        System.out.println("\n  Note: All are O(n²) in worst case.");
        System.out.println("  Insertion sort is fastest for nearly-sorted data.");
        System.out.println("  Selection sort always does O(n²) comparisons.");
        System.out.println("  Bubble sort can terminate early if sorted.");

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 10 COMPLETE                   ");
        System.out.println("=========================================");
    }

    // Helper to copy StudentList to array
    public static Student[] copyToArray(StudentList list) 
	{
        Student[] arr = new Student[list.size()];
        for (int i = 0; i < list.size(); i++) 
		{
            arr[i] = list.get(i);
        }
        return arr;
    }

    // Bubble Sort by Name
    public static void bubbleSortByName(Student[] arr) 
	{
        int n = arr.length;
        int swaps = 0;
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
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        System.out.println("  Swaps: " + swaps);
    }

    // Selection Sort by Grade
    public static void selectionSortByGrade(Student[] arr) 
	{
        int n = arr.length;
        int swaps = 0;
        int comparisons = 0;
        for (int i = 0; i < n - 1; i++) 
		{
            int minIndex = i;
            for (int j = i + 1; j < n; j++) 
			{
                comparisons++;
                if (arr[j].getGrade() < arr[minIndex].getGrade()) 
				{
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Student temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
        }
        System.out.println("  Comparisons: " + comparisons + ", Swaps: " + swaps);
    }

    // Insertion Sort by ID
    public static void insertionSortById(Student[] arr) 
	{
        int n = arr.length;
        int shifts = 0;
        for (int i = 1; i < n; i++) 
		{
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getId() > key.getId()) 
			{
                arr[j + 1] = arr[j];
                j--;
                shifts++;
            }
            arr[j + 1] = key;
        }
        System.out.println("  Shifts: " + shifts);
    }

    // Display helper
    public static void displayArray(Student[] arr, String title) 
	{
        System.out.println("\n  " + title + ":");
        for (int i = 0; i < arr.length; i++) 
		{
            System.out.println("    [" + (i + 1) + "] " + arr[i]);
        }
    }
}