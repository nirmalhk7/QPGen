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
	public static void printtable(String sql,String qsubject,String qtopic)
	{
		
		try {
			Connection con = connect();
			Statement printall=con.createStatement();
			printall=con.createStatement();
			ResultSet r = printall.executeQuery(sql);
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
	}
	public static Connection connect()
    {
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		 
		Connection c = null;
	     try {
	        Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection("jdbc:sqlite:QuestionBank.db"); //Connecting to the Question Bank database, or creating it if not exists 
	        log.log(Level.FINE,"Opened database successfully at p1");
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
		String printall="SELECT * FROM QNBANK WHERE qsubject= '"+qsubject+"' AND qtopic= '"+qtopic+"';";
		printtable(printall,qsubject,qtopic);
		Connection connector = connect();
		try
		{
			l.log(Level.FINE,"Checking if Table Exists or Not");
			Statement stmt = (Statement) connector.createStatement();
	        String tablecreate = "CREATE TABLE IF NOT EXISTS QNBANK " +
	                       "(qnos INTEGER PRIMARY KEY NOT NULL," +
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
	        l.log(Level.FINE,"Table Checked.");
			
	        stmt.close();
	        connector.close();
		}
		catch(Exception e)
        {
            l.log(Level.FINE,"Was not able to Create the Table!!");
			System.out.println("Sorry!!- Something went wrong " + e.getMessage());
            System.exit(0);
        }
	    String sql = "INSERT INTO QNBANK(qdesc , op1, op2, op3,op4,opA, qhardness, qsubject,qtopic) VALUES(?,?,?,?,?,?,?,?,?)"; 
        l.log(Level.FINE,"Getting into Insert Try! ");
	    try 
        {
	    	Scanner i= new Scanner(System.in);
	    	Connection conn = connect();
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
              
               System.out.println("Enter the  Difficulty Rating - Please give the difficulty rating from 1 - 5, with 1 being easy and 5 being very hard");
               int qhardness = input.nextInt();
               pstmt.setInt(7, qhardness);
               
               pstmt.setString(8, qsubject);
               pstmt.setString(9, qtopic);
               pstmt.executeUpdate();
               System.out.println("The following questions now exist under subject "+qsubject+" and topic "+qtopic+" -");
               String afteradd = "SELECT * FROM QNBANK WHERE qsubject= '"+qsubject+"' AND qtopic= '"+qtopic+"' ;";
               printtable(afteradd,qsubject,qtopic);
        }
        catch(Exception e)
        {
            System.out.println("Sorry!!- Something went wrong really bad " + e.getMessage());
            System.exit(0);
        }
	}
	public static void qedit(String Subj,String topic)
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger l= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		l.log(Level.FINE,"QEdit Entered.");
	
	
	     try {
                Scanner inp=new Scanner(System.in);
	    	  Connection conn = connect();
	          conn.setAutoCommit(false);
	           l.log(Level.FINE,"QEdit Try Entered.");
	         //Show list of Questions that come under that category
	          String printall="SELECT * FROM QNBANK WHERE qtopic= '"+topic+"' AND qsubject= '"+Subj+"';";
	          printtable(printall,Subj,topic);
                  Statement print=conn.createStatement();
                   String sql="SELECT QNOS FROM QNBANK WHERE qtopic= '"+topic+"' AND qsubject= '"+Subj+"';";
                   int arr[]=new int[100];
                    ResultSet d=print.executeQuery(sql);
                    int j=0;
                    while(d.next())
                    {
                        arr[j]=d.getInt("qnos");
                        j++;
                    }
                            
		  System.out.println("Enter the Question Number which you wish to edit:");
	         int qnos=inp.nextInt();
                 inp.nextLine();
                 int flag=0;
                 for(int i=0;i<j;i++)
                 {
                     if(arr[i]==qnos)
                            flag=1;
                 }
                 
	      if(flag==1)
		System.out.println("You wish to update Question "+qnos);
               else
                   System.out.println("Wrong question number");
        
            if(flag==1)
            { 
             String query = "UPDATE QNBANK SET QDESC=?,OP1=?,OP2=?,OP3=?,OP4=?,OPA =?,QHARDNESS =? WHERE QNOS=?";          
             PreparedStatement pstmt = conn.prepareStatement(query);
             System.out.println("Enter the Question"); 
             String qdesc = inp.nextLine();
             pstmt.setString (1,qdesc);
            
              System.out.println("Enter Option 1 ");
               String op1 = inp.nextLine();
               pstmt.setString (2,op1);
              
              
              System.out.println("Enter Option 2 ");
               String op2 = inp.nextLine();
               pstmt.setString(3, op2);
              
              System.out.println("Enter Option 3 ");
              String op3 = inp.nextLine();
             pstmt.setString(4, op3);
              
              System.out.println("Enter Option 4 ");
               String op4 = inp.nextLine();
              pstmt.setString(5, op4);
              
              System.out.println("Enter  the  Correct Option ");
               String correctop = inp.nextLine();
              pstmt.setString(6, correctop);
              
               System.out.println("Enter the  Difficulty Rating - Please give the difficulty rating from 1 - 5, with 1 being easy and 5 being very hard");
               int qhardness = inp.nextInt();
                pstmt.setInt(7, qhardness);
                pstmt.setInt(8,qnos);
              pstmt.executeUpdate();
                  conn.close();
            }   
	     
                }
              catch (Exception e)
            {
                System.err.println("Got an exception! ");
                 System.err.println(e.getMessage());
	   }
        }	
	
	public static void qdelete(String qsubject, String qtopic)
	{
	      Scanner inp=new Scanner(System.in);
	      LogManager logmgr = LogManager.getLogManager();
		  Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		  log.log(Level.FINE,"Entered QDELETE");
	      try {
	    	  Connection conn = connect();
	          conn.setAutoCommit(false);
	    	  log.log(Level.FINE,"Opened database successfully for QDELETE");
	         //Show list of Questions that come under that category
	          String printall="SELECT * FROM QNBANK WHERE qtopic= '"+qtopic+"' AND qsubject= '"+qsubject+"';";
	          printtable(printall,qsubject,qtopic);
	         System.out.println("Enter the Question Number which you wish to delete:");
	         int qdel=inp.nextInt();
	         System.out.println("You wish to delete Question "+qdel);
	         Statement stmt=conn.createStatement();
	         String sql = "DELETE from QNBANK where qnos='"+qdel+"';";
	          stmt.executeUpdate(sql);
	         conn.commit(); 
	        ((Statement) stmt).close();
	         conn.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	}
}

