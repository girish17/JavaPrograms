import java.lang.*;
import java.sql.SQLException;
import java.util.*;

class MainClass{
   public static void main(String[] args)
   {
     try {
      Book book = new Book(args[0], args[1], Integer.parseInt(args[2]), args[3]);
     }
     catch(Exception ex)
     {
       System.out.println("Exception: " + ex.toString());
     } 
   }

    public void connectToDb() throws SQLException {
       throw new SQLException();
    }
}
