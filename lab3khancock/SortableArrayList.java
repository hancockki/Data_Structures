import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class SortableArrayList<T> extends SimpleArrayList<T> {
    private List<T> sortList = new ArrayList<T>();
    // create new class for compare method (write implements Comparator)
            // for example, test a comparison of integers (IntComparator)
    public static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;  // returns -, 0, or + if a is <, =, or > b
        }
    }
    
    public static class SUComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            int aSUBox = a.getSmithUBox();
            int bSUBox = b.getSmithUBox();
            
            return aSUBox - bSUBox;
        }
    }
    
    public static class VowelComparator implements Comparator<Student> {
            @Override
            public int compare(Student a, Student b) {
                String aLower = a.getName().toLowerCase();
                String bLower = b.getName().toLowerCase();
                int vowelA = 0;
                int vowelB = 0;
                
                for(int i = 0; i < aLower.length(); i++) {
                    char ch = aLower.charAt(i);
                    if(ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                    vowelA++;
                }
            }
                
                for(int i = 0; i < bLower.length(); i++) {
                    char ch = bLower.charAt(i);
                    if(ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                    vowelB++;
                }
            }
            return vowelA - vowelB;
        }
    }
    
    public static class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            String[] splitAName = a.getName().split(" ");
            String aLastName = splitAName[1];
            aLastName = aLastName.toLowerCase();
            String[] splitBName = b.getName().split(" ");
            String bLastName = splitBName[1];
            bLastName = bLastName.toLowerCase();
            
            return aLastName.compareTo(bLastName);
        }
    }
    
    public void printSUBox() {
        
    }
    // create two constructors, one for each in the parent class
        // initialize instance variables defined in this class AND parent class (SimpleArrayList)
        // ie. call both constructors defined in parent (super keyword)
        // then init. any other instance variables found in this class (none in this case)
    public SortableArrayList(int startingCapacity) {
        super(startingCapacity);    // constructor with capacity given as a parameter
    }
    
    public SortableArrayList() {
        super();        // constructor with default capacity
    }
    
    protected void addStudent(T newStudent) {
        sortList.add(newStudent);
    }
    
    public List<T> getStudentList() {
        return sortList;
    }
    // create a sort method
        // reorders the list in ascending order
        // use the following declaration:
        // public void sort(Comparator<? super T> c)
        // void because changes the existing list, does not return sorted copy 
        //  comparator as parameter
        
        // pseudocode for SelectionSort:
        // for each unsorted list index, starting from the end:
            // find largest value in the unsorted part of the list\
            // swap this value with the rightmost unsorted list element
  
    private void swap(List<T> data, int i, int j) {
        T current = (T)data.get(i);
        T newNum = (T)data.get(j);
        data.set(i, newNum);
        data.set(j, current);
    }
    
    public void sort(Comparator<? super T> c) {
        for (int unsorted = sortList.size(); unsorted > 0; unsorted--) {
            int largest = 0;
            for (int i = 1; i<unsorted; i++) {
                if (c.compare((T)sortList.get(largest), (T)sortList.get(i)) >= 1) {
                    largest = i;
                }
            }
            swap(sortList, largest, unsorted - 1);
        }
    }
    
    
        
    // create main method to test sort method behavior
        // make short list of ints and sort it using the IntComparator given
        //      create a comparator
        //      hand that comparator to sort method --> myIntegerList.sort(new TestComparator());
        // print it before and after the sort
    public static void main(String[] args) {
        List<Integer> sortList = new ArrayList<Integer>();
        sortList.add(2); sortList.add(3); sortList.add(6);
        sortList.add(4); sortList.add(1); sortList.add(9); sortList.add(5);
        Comparator toTest = new IntComparator();
        System.out.println(sortList);
        sortList.sort(toTest);
        System.out.println(sortList);
        
    } 
} 
