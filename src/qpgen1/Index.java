package qpgen1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import qpgen1.editmode;
import qpgen1.qpgmode;
public class Index {
private static Logger logg;
	//Linking the Java program with SQL
	public static void marriage() {
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"Marriage Successful!");
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
				break;
			case 2:choicecheck=0;
				log.log(Level.INFO,"QPGM");
				break;
			default:choicecheck=1;
			}
	}while(choicecheck!=0);
		marriage();
	}
}
