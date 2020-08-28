
/**
 * Write a description of class Recursion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Recursion {    
    public static int countCannonballs(int height) {
        if(height == 1) {
            return 1;
        } else {
            return countCannonballs(height - 1) + (height * height);
        }
    }
    
    public static int digitSum(int n) {
        int remainder = n % 10;
        if (n == 0) {
            return 0;
        } else {
            return remainder + (digitSum(n / 10));
        }
    }
    
    public static int bitsToDecimal(String bits) {
        int size = bits.length();
        if(size == 1) {
            return 1;
        }
        else {
            if (bits.charAt(size - 1) == '1') {
                return (bitsToDecimal(bits.substring(0, size - 1)) * 2) + 1;
            } else {
                return bitsToDecimal(bits.substring(0, size - 1)) * 2;
            }
        }            
    }
    
    public static boolean isPalindrome(String str) {
        if (str.length() == 0 || str.length() == 1) {
            return true;
        } else {
            if (str.charAt(0) == ' ') {
                return isPalindrome(str.substring(1));
            }
            
            if (str.charAt(str.length() - 1) == ' ') {
                return isPalindrome(str.substring(0, str.length() - 1));
            }
            
            if (str.charAt(0) == str.charAt(str.length() - 1)) {
                return isPalindrome(str.substring(1, str.length() - 1));
            } else {
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Number of cannonballs in stack of height 4: " + countCannonballs(4));
        System.out.println("Sum of digits in 5398: " + digitSum(5398));
        System.out.println("Decimals value of 100001: " + bitsToDecimal("100001"));
        System.out.println(isPalindrome("r a   ceca r "));
        System.out.println(isPalindrome("not a palindrome"));
    }
}
