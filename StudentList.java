/**
 * StudentList.java
 * A dynamic array-based list that stores Student objects.
 * Automatically resizes (doubles capacity) when full.
 * Created for Project 4, reused in Projects 5-10.
 */
public class StudentList 
{
    private Student[] students;
    private int size;           // Number of students currently stored
    private static final int INITIAL_CAPACITY = 2;  // Start small to test resizing

    public StudentList() 
	{
        students = new Student[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Add a student to the list. Resizes array if full.
     */
    public void add(Student student) 
	{
        if (size == students.length) 
		{
            resize();
        }
        students[size] = student;
        size++;
    }

    /**
     * Double the array capacity.
     */
    private void resize() 
	{
        int newCapacity = students.length * 2;
        Student[] newArray = new Student[newCapacity];
        for (int i = 0; i < size; i++) 
		{
            newArray[i] = students[i];
        }
        students = newArray;
        System.out.println("  [Array resized to capacity: " + newCapacity + "]");
    }

    /**
     * Get student at a specific index.
     */
    public Student get(int index) 
	{
        if (index < 0 || index >= size) 
		{
            return null;
        }
        return students[index];
    }

    /**
     * Get the current number of students.
     */
    public int size() 
	{
        return size;
    }

    /**
     * Get the current array capacity.
     */
    public int capacity() 
	{
        return students.length;
    }

    /**
     * Display all students.
     */
    public void displayAll() 
	{
        if (size == 0) 
		{
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("\n--- Student List (" + size + " students, capacity: " + students.length + ") ---");
        for (int i = 0; i < size; i++)
		{
            System.out.println("[" + (i + 1) + "] " + students[i]);
        }
        System.out.println("--- End of List ---");
    }
}