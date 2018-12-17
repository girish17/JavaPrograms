import java.lang.* ;
import java.util.*;

/*This is a Java program to find out whether an integer is a palindrome or not*/
class IntegerPalindrome {
   public static void main(String[] args){
      try
      {
        int num = Integer.parseInt(args[0]);
        int palNum = num;
        int numOfDigits = numberOfDigits(num);
        int pow = 1;
        boolean isPalindrome = true;
        for(int i=1; i<=numOfDigits; i++)
        {
          pow = power(10, numOfDigits-i);
          if(num/pow != palNum%10)
          {
            System.out.println("Not a palindrome");
            isPalindrome = false;
            break;
          }
          num = num%pow;
          palNum = palNum/10;
        }
        if(isPalindrome)
         System.out.println("Palindrome");
      }
      catch(Exception ex)
      {
        System.out.println("\nException: "+ex.toString());
      }
   }

   static int numberOfDigits(int num)
   {
     int numDigits = 1;
     while(num/10 != 0)
     {
       num = num/10;
       numDigits++;
     }
     return numDigits;
   }

   static int power(int num, int exp)
   {
     if(exp == 0)
       return 1;

     for(int i=1; i<exp; i++)
       num = num*10;

     return num;
   }
}
