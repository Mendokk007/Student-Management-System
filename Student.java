/**
 * Student.java
 * Represents a single student record.
 * Created for Project 3, reused throughout all subsequent projects.
 */
public class Student 
{
    private int id;
    private String name;
    private double grade;

    // Constructor
    public Student(int id, String name, double grade) 
	{
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public int getId() 
	{
        return id;
    }

    public String getName() 
	{
        return name;
    }

    public double getGrade() 
	{
        return grade;
    }

    // Setters (needed for potential updates, undo/redo)
    public void setId(int id) 
	{
        this.id = id;
    }

    public void setName(String name) 
	{
        this.name = name;
    }

    public void setGrade(double grade) 
	{
        this.grade = grade;
    }

    // String representation for easy printing
    @Override
    public String toString() 
	{
        return String.format("ID: %-5d | Name: %-20s | Grade: %.2f", id, name, grade);
    }
}