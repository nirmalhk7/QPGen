package qpgen1;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class editmode {

	public Connection connect()
    {
	     Connection c = null;
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
	public void qadd(String subj,String topic)
	{
		Scanner input = new Scanner(System.in);
    	String sql = "INSERT INTO QUESTION(QDesc , Op1 , Op2, Op3 , Op4 , Correctop , Subj, Topic,Qhardness ) VALUES(?,?,?,?,?,?,?,?,?)"; 
        try 
        {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql); 
             System.out.println("Enter the Question"); 
             String qdesc = input.nextLine();
              pstmt.setString(1, qdesc);
               System.out.println("Enter Option 1 ");
               String op1 = input.nextLine();
              pstmt.setString(2, op1);
               System.out.println("Enter Option 2 ");
               String op2 = input.nextLine();
              pstmt.setString(3, op2);
              System.out.println("Enter Option 3 ");
              String op3 = input.nextLine();
              pstmt.setString(4, op3);
               System.out.println("Enter Option 4 ");
               String op4 = input.nextLine();
              pstmt.setString(5, op4);
               System.out.println("Enter  the  Correct Option ");
               String correctop = input.nextLine();
              pstmt.setString(6, correctop);
              pstmt.setString(7, subj);
              pstmt.setString(8, topic);
              System.out.println("Enter the  Difficulty Rating - Please give the difficulty rating from 1 - 5, with 1 being easy and 5 being very hard");
              int qhardness = input.nextInt();
              pstmt.setInt(9, qhardness);
              pstmt.executeUpdate();
        }
        
        catch(Exception e)
        {
            System.out.println("Sorry!!- Something went wrong " + e.getMessage());
            System.exit(0);
        }
	}
	public void qedit(String Subj,String topic)
	{
		
	}
	public void qdelete(String Subj, String topic)
	{
	      Scanner inp=new Scanner(System.in);
	      LogManager logmgr = LogManager.getLogManager();
			Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	      try {
	    	  Connection conn = this.connect();
	          conn.setAutoCommit(false);
	    	  log.log(Level.FINER,"Opened database successfully");
	         
	    	  Statement stmt = (Statement) conn.createStatement();
	         //Show list of Questions that come under that category
	         ResultSet rs = ((java.sql.Statement) stmt).executeQuery( "SELECT * FROM QNLIST WHERE qtopic='"+topic+"';");
	         while ( rs.next() )
	         {
		         int qnos = rs.getInt("qnos");
		         String  qdesc = rs.getString("qdesc");
		         String op1=rs.getString("op1");
		         String op2=rs.getString("op2");
		         String op3=rs.getString("op3");
		         String op4=rs.getString("op4");
		         String opA=rs.getString("opA");
		         String  qhardness = rs.getString("qhardness");
		         String qtopic = rs.getString("qtopic");
		         
		         System.out.println( "QNOS = " + qnos );
		         System.out.println( "QDESC = " + qdesc );
		         System.out.println("OPTION 1= "+op1);
		         System.out.println("OPTION 2= "+op2);
		         System.out.println("OPTION 3= "+op3);
		         System.out.println("OPTION 4= "+op4);
		         System.out.println("CORRECT OPTION= "+opA);
		         System.out.println( "QHARDNESS="+ qhardness );
		         System.out.println( "QTOPIC = " + qtopic );
	         }
	         System.out.println("Enter the Question Number which you wish to delete:");
	         int qdel=inp.nextInt();
	         String sql = "DELETE from QNLIST where TOPIC='"+topic+";";
	         ((java.sql.Statement) stmt).executeUpdate(sql);
	         conn.commit(); 
	         rs.close();
	         ((Connection) stmt).close();
	         conn.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	}
}


