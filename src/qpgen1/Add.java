import java.lang.*;
import java.util.Scanner;
import java.sql.*;

 class Add1
{
    
     public Connection connect()
     {
          Connection c = null;
      
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
        return c;
    }
    
    public  void InsertQuestion()
      {
        String sql = "INSERT INTO QUESTION(QDesc , Op1 , Op2, Op3 , Op4 , Correctans , Subj, Topic,Diff ) VALUES(?,?,?,?,?,?,?,?,?)";
            Scanner input = new Scanner(System.in);
        try 
        {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql); 
             System.out.println("Enter the Question"); 
             String a = input.nextLine();
              pstmt.setString(1, a);
               System.out.println("Enter Option 1 ");
               String b = input.nextLine();
              pstmt.setString(2, b);
               System.out.println("Enter Option 2 ");
               String c = input.nextLine();
              pstmt.setString(3, c);
              System.out.println("Enter Option 3 ");
              String d = input.nextLine();
              pstmt.setString(4, d);
               System.out.println("Enter Option 4 ");
               String e = input.nextLine();
              pstmt.setString(5, e);
               System.out.println("Enter  the  Correct Option ");
               String f = input.nextLine();
              pstmt.setString(6, f);
              System.out.println("Enter the Subject");
             String h = input.nextLine();
              pstmt.setString(7, h);
             System.out.println("Enter the specific Topic on which the Question is based ");
             String i = input.nextLine();
                pstmt.setString(8, i);
                System.out.println("Enter the  Difficulty Rating - Please give the difficulty rating from 1 - 5, with 1 being easy and 5 being very hard   ");
             int g = input.nextInt();
             if(g== 1 || g== 2 || g== 3 || g== 4 || g== 5)
              pstmt.setInt(9, g);
              else
              System.out.println("Error in input ");
              pstmt.executeUpdate();
        }
        
        catch(Exception e)
        {
            System.out.println("Sorry!!- Something went wrong " + e.getMessage());
            System.exit(0);
        }
    }
}
                   
public class Add {
   
     
   public static void main( String args[] ) {   
       int k;
       Scanner input = new Scanner(System.in);
       System.out.println("Hello !! This is the program to add questions into question bank !!");
       Add1 object1 = new Add1();  
       object1.connect();
       System.out.println("Enter the number of questions you want to enter into question bank");
       k = input.nextInt();
       try
       {
           for(int i=1;i<=k;i++)
       {
          
           if(i==k)
           {
               System.out.println("This is the last question you can enter in this session.");
               
               
            }
            object1.InsertQuestion();
            
               
     
    }
}

    catch (Exception e)
    {
        System.out.println("There was an error in input - please try again" + e.getMessage());
    }        
    
    // Write program to show list of questions with SELECT statement - Think about it later
//    System.out.println("Do you want to see the list of questions? Press 1 for yes or any other key to exit");
  //  int l = input.nextInt();
  //    if(l==1)
      {
          
      
      
        }
   }
}