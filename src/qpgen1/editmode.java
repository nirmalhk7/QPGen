package qpgen1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class editmode {
	public static Connection connect()
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
	public static void qadd(String qsubject,String qtopic)
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger l= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		Scanner input = new Scanner(System.in);
		Statement printall;
		Connection con = connect();
		try {
			printall=con.createStatement();
			ResultSet r = printall.executeQuery("SELECT * FROM QBANK WHERE qsubject= "+qsubject+" AND qtopic= "+qtopic+";");
			while ( r.next() ) {
		     	   int no = r.getInt("qnos");
		     	   String  desc = r.getString("qdesc");
		     	   String op1 = r.getString("op1");
		     	   String op2 = r.getString("op2");
		     	   String op3 = r.getString("op3");
		     	   String op4 = r.getString("op4");
		     	   String correctop = r.getString("opA");
		     	   int qhardness=r.getInt("qhardness");
		     	   qsubject=r.getString("qsubject");
		     	   qtopic=r.getString("qtopic");
		            System.out.println( "QNOS = " +no );
		            System.out.println( "QUESTION = " + desc );
		            System.out.println( "OPTION 1 = " + op1 );
		            System.out.println( "OPTION 2 = " + op2 );
		            System.out.println( "OPTION 3 = " + op3 );
		            System.out.println( "OPTION 4 = " + op4 );
		            System.out.println( "CORRECT ANSWER = " + correctop);
		            System.out.println( "HARDNESS = "+ qhardness);
		            System.out.println( "SUBJECT = "+ qsubject);
		            System.out.println( "TOPIC = "+ qtopic);
		            System.out.println();
		       }
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
		Connection connector = connect();
		try
		{
			l.log(Level.INFO,"Checking if Table Exists or Not");
			Statement stmt = (Statement) connector.createStatement();
	        String tablecreate = "CREATE TABLE IF NOT EXISTS QBANK " +
	                       "(qnos INTEGER NOT NULL," +
	                       " qdesc TEXT NOT NULL,\n " + 
	                       " op1 TEXT NOT NULL,\n " + 
	                       " op2 TEXT NOT NULL,\n " +
	                       " op3 TEXT NOT NULL,\n " +
	                       " op4 TEXT NOT NULL,\n " +
	                       " opA TEXT NOT NULL,\n " +
	                       " qhardness INTEGER NOT NULL, \n" + 
	                       " qsubject TEXT NOT NULL ,\n " + 
	                       " qtopic TEXT NOT NULL )";
	                       
	        ((java.sql.Statement) stmt).executeUpdate(tablecreate);
	        l.log(Level.INFO,"Table Checked.");
			
	        stmt.close();
	        connector.close();
		}
		catch(Exception e)
        {
            l.log(Level.INFO,"Was not able to Create the Table!!");
			System.out.println("Sorry!!- Something went wrong " + e.getMessage());
            System.exit(0);
        }
	    String sql = "INSERT INTO QBANK(qnos, qdesc , op1, op2, op3,op4,opA, qhardness, qsubject,qtopic) VALUES(?,?,?,?,?,?,?,?,?,?)"; 
        l.log(Level.INFO,"Getting into Insert Try! ");
	    try 
        {
	    	Scanner i= new Scanner(System.in);
	    	Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            System.out.println("Enter the Question Number"); 
            int qn = i.nextInt();
             pstmt.setInt(1, qn);
            System.out.println("Enter the Question"); 
             String qdesc = input.nextLine();
              pstmt.setString(2, qdesc);
            
              System.out.println("Enter Option 1 ");
               String op1 = input.nextLine();
              pstmt.setString(3, op1);
              
              System.out.println("Enter Option 2 ");
               String op2 = input.nextLine();
              pstmt.setString(4, op2);
              
              System.out.println("Enter Option 3 ");
              String op3 = input.nextLine();
              pstmt.setString(5, op3);
              
              System.out.println("Enter Option 4 ");
               String op4 = input.nextLine();
              pstmt.setString(6, op4);
              
              System.out.println("Enter  the  Correct Option ");
               String correctop = input.nextLine();
               pstmt.setString(7, correctop);
              
               System.out.println("Enter the  Difficulty Rating - Please give the difficulty rating from 1 - 5, with 1 being easy and 5 being very hard");
               int qhardness = input.nextInt();
               pstmt.setInt(8, qhardness);
               
               pstmt.setString(9, qsubject);
               pstmt.setString(10, qtopic);
               pstmt.executeUpdate();
               System.out.println("The following questions now exist under subject "+qsubject+" and topic "+qtopic+" -");
               Statement printdel;
               printdel=conn.createStatement();
               ResultSet rs = printdel.executeQuery("SELECT * FROM QBANK WHERE qsubject= "+qsubject+" AND qtopic= "+qtopic+" ;");
               while ( rs.next() ) {
            	   int no = rs.getInt("qnos");
            	   String  desc = rs.getString("qdesc");
            	   op1 = rs.getString("op1");
            	   op2 = rs.getString("op2");
            	   op3 = rs.getString("op3");
            	   op4 = rs.getString("op4");
            	   correctop = rs.getString("opA");
            	   qhardness=rs.getInt("qhardness");
            	   qsubject=rs.getString("qsubject");
            	   qtopic=rs.getString("qtopic");
                   System.out.println( "QNOS = " +no );
                   System.out.println( "QUESTION = " + qdesc );
                   System.out.println( "OPTION 1 = " + op1 );
                   System.out.println( "OPTION 2 = " + op2 );
                   System.out.println( "OPTION 3 = " + op3 );
                   System.out.println( "OPTION 4 = " + op4 );
                   System.out.println( "CORRECT ANSWER = " + correctop);
                   System.out.println( "HARDNESS = "+ qhardness);
                   System.out.println( "SUBJECT = "+ qsubject);
                   System.out.println( "TOPIC = "+ qtopic);
                   System.out.println();
              }
              
        }
        catch(Exception e)
        {
            System.out.println("Sorry!!- Something went wrong really bad " + e.getMessage());
            System.exit(0);
        }
	}
	public static void qedit(String Subj,String topic)
	{
		
	}
	public static void qdelete(String Subj, String topic)
	{
	      Scanner inp=new Scanner(System.in);
	      LogManager logmgr = LogManager.getLogManager();
		  Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	      try {
	    	  Connection conn = connect();
	          conn.setAutoCommit(false);
	    	  log.log(Level.FINER,"Opened database successfully");      
	    	  PreparedStatement stmt = (PreparedStatement) conn.createStatement();
	         //Show list of Questions that come under that category
	          ResultSet rs = ((java.sql.Statement) stmt).executeQuery( "SELECT * FROM QBANK WHERE qtopic="+topic+" AND qsubj="+Subj+";");
	          while ( rs.next() )
	          {
		         int qnos = rs.getInt("qnos");
		         String  qdesc = rs.getString("qdesc");
		         String op1=rs.getString("op1");
		         String op2=rs.getString("op2");
		         String op3=rs.getString("op3");
		         String op4=rs.getString("op4");
		         String opA=rs.getString("opA");
		         int  qhardness = rs.getInt("qhardness");
		         String qsubject = rs.getString("qsubject");
		         String qtopic = rs.getString("qtopic");
		         
		         System.out.println( "QNOS = " + qnos );
		         System.out.println( "QDESC = " + qdesc );
		         System.out.println("OPTION 1= "+op1);
		         System.out.println("OPTION 2= "+op2);
		         System.out.println("OPTION 3= "+op3);
		         System.out.println("OPTION 4= "+op4);
		         System.out.println("CORRECT OPTION= "+opA);
		         System.out.println( "QHARDNESS="+ qhardness );
		         System.out.println( "QSUBJECT = " + qsubject );
		         System.out.println( "QTOPIC = " + qtopic );
	         }
	         System.out.println("Enter the Question Number which you wish to delete:");
	         int qdel=inp.nextInt();
	         String sql = "DELETE from QNLIST where qnos="+qdel+";";
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


