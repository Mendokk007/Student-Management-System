import java.util.Scanner;

/**
 * Project02_SimpleStudentList.java
 * Allows user to input student records and displays them.
 * Uses a fixed-size array and Scanner for input.
 * 
 * Compile: javac Project02_SimpleStudentList.java
 * Run:     java Project02_SimpleStudentList
 */
public class Project02_SimpleStudentList 
{
    public static void main(String[] args) 
	{
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   PROJECT 2: SIMPLE STUDENT LIST        ");
        System.out.println("=========================================\n");

        // Ask user how many students
        System.out.print("How many students do you want to enter? ");
        int count = Integer.parseInt(scanner.nextLine());

        // Arrays to store data
        int[] ids = new int[count];
        String[] names = new String[count];
        double[] grades = new double[count];

        // Input loop
        System.out.println("\n--- Enter Student Information ---");
        for (int i = 0; i < count; i++) {
            System.out.println("\nStudent #" + (i + 1) + ":");
            System.out.print("  Enter ID: ");
            ids[i] = Integer.parseInt(scanner.nextLine());
            System.out.print("  Enter Name: ");
            names[i] = scanner.nextLine();
            System.out.print("  Enter Grade: ");
            grades[i] = Double.parseDouble(scanner.nextLine());
        }

        // Display all entered students
        System.out.println("\n=========================================");
        System.out.println("       ENTERED STUDENT RECORDS           ");
        System.out.println("=========================================");

        double totalGrade = 0;
        for (int i = 0; i < count; i++) 
		{
            System.out.printf("ID: %-5d | Name: %-20s | Grade: %.2f%n", 
                              ids[i], names[i], grades[i]);
            totalGrade += grades[i];
        }

        // Summary
        System.out.println("-----------------------------------------");
        System.out.println("Total students: " + count);
        System.out.printf("Average grade: %.2f%n", (totalGrade / count));

        // Find highest and lowest grade
        double highest = grades[0];
        double lowest = grades[0];
        String highestName = names[0];
        String lowestName = names[0];

        for (int i = 1; i < count; i++) 
		{
            if (grades[i] > highest) 
			{
                highest = grades[i];
                highestName = names[i];
            }
            if (grades[i] < lowest) 
			{
                lowest = grades[i];
                lowestName = names[i];
            }
        }

        System.out.println("Highest grade: " + highestName + " (" + highest + ")");
        System.out.println("Lowest grade:  " + lowestName + " (" + lowest + ")");

        System.out.println("\n=========================================");
        System.out.println("   PROJECT 2 COMPLETE                    ");
        System.out.println("=========================================");

        scanner.close();
    }
}