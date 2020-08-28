import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

/**
 * Write a description of class DictionarySort here.
 * 
 * All the Comparator classes are here
 * Main method actually answers the questions
 * Build the array of Student objects here, and then compare students
 * Create new Sortable Array List object, which takes the list of 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DictionarySort
{
    // instance variables - replace the example below with your own
    private static List<Student> studentList = new ArrayList<Student>();

    /**
     * Constructor for objects of class DictionarySort
     */
    public DictionarySort()
    {
        // initialise instance variables
        this.studentList = studentList;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SortableArrayList sortStudents = new SortableArrayList();
            try {
                scan = new Scanner(new File ("directory.txt"));
            } catch (Exception e) {
                System.exit(0);
            }
        
            while (scan.hasNext()) { // while there’s more of the file to read
                 String line = scan.nextLine(); // read the next line
                 // create new student object with line
                 Student nextStudent = new Student(line);
                 sortStudents.addStudent(nextStudent);
                 //System.out.println(line);
                 
                 //studentList.add(nextStudent);
            }
        scan.close(); // done reading the file, close the Scanner
        
        Comparator sortSUBox = new SortableArrayList.SUComparator();
        sortStudents.sort(sortSUBox);  
        
        List<Student> sortList = sortStudents.getStudentList();
        int largest = sortList.size() - 1;
        
        System.out.println("The Students with the largest SUBox numbers are \n" + 
        sortList.get(0) + "\n" + sortList.get(1) + "\n");
        System.out.println("The students with the smallest SU Box numbers are \n" + 
        sortList.get(largest) + "\n" + sortList.get(largest - 1) + "\n");
        
        Comparator sortName = new SortableArrayList.NameComparator();
        sortStudents.sort(sortName);
        
        System.out.println("The Student with last name last in alphabetical order is\n" + sortList.get(0) + "\n");
        System.out.println("The student with last name first in alphabetical order is\n" + sortList.get(largest) + "\n");
        
        Comparator sortVowels = new SortableArrayList.VowelComparator();
        sortStudents.sort(sortVowels);
        System.out.println("The students with the largest number of vowels in their names are\n" +
        sortList.get(0) + "\n" + sortList.get(1) + "\n" + sortList.get(2));
    }
}
