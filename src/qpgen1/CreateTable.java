
import java.util.Scanner;
import java.sql.*;

class Create
{
    
     public void connect()
     {
         
      Connection c =null; 
      Scanner input = new Scanner(System.in);
      
      try {
         Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:QuestionBank.db"); //Connecting to the Question Bank database, or creating it if not exists 
         System.out.println("Opened database successfully");
         
        }
         
      catch(Exception e)
      {
          System.out.println("Couldn't connect to database :-("+ e.getMessage());
         System.exit(0);
       }
        
      
     
         try{
        Statement stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS QUESTION " +
                        "(SERIALNO         INTEGER PRIMARY KEY NOT NULL," +
                        " QDesc           TEXT     NOT NULL,\n " + 
                        " Op1             TEXT     NOT NULL,\n " + 
                        " Op2             TEXT     NOT NULL,\n " +
                        " Op3             TEXT     NOT NULL,\n " +
                        " Op4             TEXT     NOT NULL,\n " +
                        " Correctans      TEXT     NOT NULL,\n " +
                        " Subj            TEXT     NOT NULL, \n" + 
                        " Topic            TEXT     NOT NULL ,\n " + 
                        " Diff           INTEGER     NOT NULL )";
                        
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      }  catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
    }   

 }
                   
public class CreateTable {
   
     
   public static void main( String args[] ) {   
       //String a ,b,c,d,e,f,g,h,i;
       char k;
       Scanner input = new Scanner(System.in);
       System.out.println("Hello !! This is the program to add questions into question bank !!");
       Create object1 = new Create();  
       object1.connect();
       //object1.CreateTable();
    /*   try
       {
           while( (k= (char)System.in.read()) != '\n')
       {
           System.out.println("Enter the question ");
           String a = input.nextLine();
           System.out.println("Enter Option 1 ");
           String b = input.nextLine();
           System.out.println("Enter Option 2 ");
              String c = input.nextLine();
               System.out.println("Enter Option 3 ");
              String d = input.nextLine();
               System.out.println("Enter Option 4 ");
              String e = input.nextLine();
               System.out.println("Enter  the  Correct Option ");
              String f = input.nextLine();
               System.out.println("Enter the  Difficulty Rating ");
             String g = input.nextLine();
               System.out.println("Enter the Subject");
              String h = input.nextLine();
               System.out.println("Enter the specific Topic on which the Question is based ");
           String i = input.nextLine();
           object1.InsertQuestion(a,b,c,d,e,f,g,h,i);
       System.out.println("If you do not want to enter any more questions, press the 'Enter' button ");
    }
    
}
    catch (Exception e)
    {
        System.out.println("There was an error in input - please try again" + e.getMessage());
    }  */      
    
    // Write program to show list of questions with SELECT statement
      
      
      
      
   }
}