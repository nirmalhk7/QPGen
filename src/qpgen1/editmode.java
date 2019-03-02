package qpgen1;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class editmode {
	public void qadd()
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"Qadd Entered.");
	}
	public void qdelete( String topic[] ) {
	      Scanner inp=new Scanner(System.in);
		  Connection c = null;
	      java.sql.Statement stmt = null;
	      LogManager logmgr = LogManager.getLogManager();
			Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	      try {
	         log.log(Level.FINER,"Opened database successfully");
	         stmt = c.createStatement();
	         //Show list of Questions that come under that category
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM QNLIST WHERE TOPIC='"+topic+"';");
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
	         int qnos=0;
	         System.out.println("Enter the Question Number which you wish to delete:");
	         qnos=inp.nextInt();
	         String sql = "DELETE from QNLIST where TOPIC='"+topic+"';";
	         stmt.executeUpdate(sql);
	         c.commit(); 
	      rs.close();
	      stmt.close();
	      c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	   }
	}
	public static void main()
	{
	}
}
