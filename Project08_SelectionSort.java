/**
 * Project08_SelectionSort.java
 * Sorts the StudentList by grade (low to high) using Selection Sort.
 * Finds the minimum element in the unsorted portion and places it at the beginning.
 * 
 * Compile: javac Student.java StudentList.java Project08_SelectionSort.java
 * Run:     java Project08_SelectionSort
 */
public class Project08_SelectionSort 
{

    /**
     * Selection sort by grade (lowest to highest).
     */
    public static void selectionSortByGrade(StudentList list) 
	{
        int n = list.size();
        int swaps = 0;
        int comparisons = 0;

        // Copy to array
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) 
		{
            arr[i] = list.get(i);
        }

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
            // Swap only if a new minimum was found
            if (minIndex != i) 
			{
                Student temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
        }

        System.out.println("  Comparisons: " + comparisons + ", Swaps: " + swaps);
        displaySortedArray(arr);
    }

    public static void displaySortedArray(Student[] arr) 
	{
        System.out.println("\n--- Sorted by Grade (Selection Sort, Low->High) ---");
        for (int i = 0; i < arr.length; i++) 
		{
            System.out.println("[" + (i + 1) + "] " + arr[i]);
        }
        System.out.println("--- End ---");
    }

    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 8: SELECTION SORT (BY GRADE)  ");
        System.out.println("=========================================\n");

        StudentList list = new StudentList();
        list.add(new Student(501, "Zara", 85.0));
        list.add(new Student(502, "Alex", 92.0));
        list.add(new Student(503, "Maya", 78.5));
        list.add(new Student(504, "Ethan", 91.0));
        list.add(new Student(505, "Liam", 88.0));

        System.out.println("Original list:");
        list.displayAll();

        System.out.println("\n--- Sorting by Grade ---");
        selectionSortByGrade(list);

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 8 COMPLETE                    ");
        System.out.println("=========================================");
    }
}