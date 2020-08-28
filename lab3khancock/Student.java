
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student
{
    // instance variables - replace the example below with your own
    private int smithUBox;
    private String name;
    private String address;
    private String number;
    private String line;
    private String email;

    /**
     * Constructor for objects of class Student
     */
    public Student(String line)
    {
        // initialise instance variables
        this.line = line;
        getAttributes(line);
    }
    
    public void getAttributes(String line) {
        String[] information = line.split("\\|");
        name = information[0];
        String phoneNumber = information[4];
        this.smithUBox = Integer.parseInt(phoneNumber.trim());
        this.number = information[2];
        this.address = information[1];
        this.email = information[3];
    }
    
    public String toString() {
        return name + "|" + address + "|" + number + "|" + email + "|" + smithUBox;
    }
    
    public String getNumber() {
        return number;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public int getSmithUBox() {
        return smithUBox;
    }
}



    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    //public void getAttributes(String line) {
        
    //}
