import java.lang.*;
import java.math.*;

class Statically
{
   private static int num;
   private static int i;   
   public static void main(String[] args)
   {
     for(; i<Integer.parseInt(args[0]); i++)
     {
       num++;
       System.out.println(num);
     }
   }
}

