/**
 * Project01_StudentInfoPrinter.java
 * Displays pre-stored student records using a fixed array.
 * No user input — just prints what's already in the array.
 * 
 * Compile: javac Project01_StudentInfoPrinter.java
 * Run:     java Project01_StudentInfoPrinter
 */
public class Project01_StudentInfoPrinter 
{
    public static void main(String[] args) 
	{
        System.out.println("=========================================");
        System.out.println("   PROJECT 1: STUDENT INFO PRINTER       ");
        System.out.println("=========================================\n");

        // Hardcoded student data in parallel arrays
        int[] ids = {1001, 1002, 1003, 1004, 1005};
        String[] names = {"Maria Santos", "Juan Dela Cruz", "Ana Reyes", 
                          "Carlos Garcia", "Elena Mendoza"};
        double[] grades = {88.5, 92.0, 79.5, 85.0, 95.5};

        System.out.println("Total students stored: " + ids.length);
        System.out.println("\n--- Student Records ---");

        // Print each student using a for loop
        for (int i = 0; i < ids.length; i++) 
		{
            System.out.println("Student #" + (i + 1));
            System.out.println("  ID:    " + ids[i]);
            System.out.println("  Name:  " + names[i]);
            System.out.println("  Grade: " + grades[i]);
            System.out.println();
        }

        System.out.println("--- End of Records ---");
        System.out.println("\n=========================================");
        System.out.println("   PROJECT 1 COMPLETE                    ");
        System.out.println("=========================================");
    }
}