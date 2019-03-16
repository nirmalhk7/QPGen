package qpgen1;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.sql.*;
import qpgen1.editmode;
import qpgen1.qpgen;
public class Index {
	//Linking the Java program with SQL
	public static void marriage() {
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"Attempting Marriage!");
		 Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      log.log(Level.INFO,"Opened database successfully");
	}
//Main thing
	public static void main(String[] args) {
		Scanner inp=new Scanner(System.in);
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
		System.out.println("Welcome to Question Paper Generator.");
		int choice,choicecheck=0;
		do{
			System.out.println("Looking to Add/Edit/Delete Questions from the database? Enter 1.");
			System.out.println("Looking to Generate a Question Paper? Enter 2.");
			choice=inp.nextInt();
			
			switch(choice)
			{
				case 1:choicecheck=0;
					log.log(Level.INFO,"Edit Mode entered.");
					System.out.println("What topic does the question belong which you want to add/delete/edit?");
					String qtopic=inp.next();
					editmode.qdelete(qtopic);
					break;
				case 2:choicecheck=0;
					log.log(Level.INFO,"QPGM");
					//qpgen.fixQno();
					break;
				default:choicecheck=1;
				System.out.println("Please enter numbers 1 or 2 only.");
			}
	}while(choicecheck!=0);
		marriage();
	}
}
