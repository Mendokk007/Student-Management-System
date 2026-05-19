/**
 * Project09_InsertionSort.java
 * Sorts the StudentList by ID using Insertion Sort.
 * Builds the sorted list one element at a time by inserting
 * each new element into its correct position.
 * 
 * Compile: javac Student.java StudentList.java Project09_InsertionSort.java
 * Run:     java Project09_InsertionSort
 */
public class Project09_InsertionSort 
{

    /**
     * Insertion sort by ID (lowest to highest).
     */
    public static void insertionSortById(StudentList list) 
	{
        int n = list.size();
        int shifts = 0;

        // Copy to array
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) 
		{
            arr[i] = list.get(i);
        }

        for (int i = 1; i < n; i++) 
		{
            Student key = arr[i];
            int j = i - 1;

            // Shift elements greater than key to the right
            while (j >= 0 && arr[j].getId() > key.getId()) 
			{
                arr[j + 1] = arr[j];
                j--;
                shifts++;
            }
            arr[j + 1] = key;
        }

        System.out.println("  Shifts: " + shifts);
        displaySortedArray(arr);
    }

    public static void displaySortedArray(Student[] arr) 
	{
        System.out.println("\n--- Sorted by ID (Insertion Sort) ---");
        for (int i = 0; i < arr.length; i++) 
		{
            System.out.println("[" + (i + 1) + "] " + arr[i]);
        }
        System.out.println("--- End ---");
    }

    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 9: INSERTION SORT (BY ID)     ");
        System.out.println("=========================================\n");

        // Unsorted IDs
        StudentList list = new StudentList();
        list.add(new Student(605, "Zara", 85.0));
        list.add(new Student(602, "Alex", 90.0));
        list.add(new Student(608, "Maya", 78.0));
        list.add(new Student(601, "Ethan", 92.0));
        list.add(new Student(604, "Liam", 88.0));

        System.out.println("Original list (unsorted IDs):");
        list.displayAll();

        System.out.println("\n--- Sorting by ID ---");
        insertionSortById(list);

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 9 COMPLETE                    ");
        System.out.println("=========================================");
    }
}