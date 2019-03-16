package qpgen1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class editmode {
	public static void qdelete(String topic) {
	//Set up logger.
   	  
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
   	  	log.log(Level.FINE,"QDelete Entered.");
   	  	Scanner inp=new Scanner(System.in);
   	  	Connection c = null;
   	  	java.sql.Statement stmt = null;
   	  	try {
			 log.log(Level.FINER,"Opened database successfully");
			 stmt = c.createStatement();
			 //Show list of Questions that come under that category
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM QNLIST WHERE qtopic='"+topic+"';");
			 while ( rs.next() )
			 {
				 int qnos = rs.getInt("qnos");
				 String  qdesc = rs.getString("qdesc");
				 String  qhardness = rs.getString("qhardness");
				 String qtopic = rs.getString("qtopic");
				 System.out.println( "QNOS = " + qnos );
				 System.out.println( "QDESC = " + qdesc );
				 System.out.println( "QHARDNESS="+ qhardness );
				 System.out.println( "QTOPIC = " + qtopic );
			 }
         int qnos2edit=0;
         //Find out the QnNos to delete.
         System.out.println("Enter the Question Number which you wish to delete:");
         qnos2edit=inp.nextInt();
         String sql = "DELETE from QNLIST where qtopic='"+topic+"'AND qnos='"+qnos2edit+"'";
         stmt.executeUpdate(sql);
         System.out.println("Question Has been Deleted from the database. This is what your question list of "+topic+"topic looks like:");
         ResultSet rs2=stmt.executeQuery("SELECT * FROM QNLIST WHERE TOPIC='"+topic+"';");
         while ( rs.next() )
         {
	         int qnos = rs.getInt("qnos");
	         String  qdesc = rs.getString("qdesc");
	         String  qhardness = rs.getString("qhardness");
	         String qtopic = rs.getString("qtopic");
	         System.out.println( "QNOS = " + qnos );
	         System.out.println( "QDESC = " + qdesc );
	         System.out.println( "QHARDNESS="+ qhardness );
	         System.out.println( "QTOPIC = " + qtopic );
         }
         c.commit(); 
		rs.close();
		stmt.close();
		c.close();
		} catch ( Exception e ) {
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		}
		log.log(Level.FINE,"Operation done successfully");
   }

}

