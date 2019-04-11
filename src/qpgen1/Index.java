package qpgen1;import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import qpgen1.editmode;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import qpgen1.editmode;
import qpgen1.qpgen;
public class Index {
//Main thing
	public static void main(String[] args) 
	{
		Scanner inp=new Scanner(System.in);
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		String choice1;
		System.out.println("Welcome to Question Paper Generator.");
		int choice,choicecheck=0;
		do{
			do{
				System.out.println("Looking to Add/Edit/Delete Questions from the database? Enter 1.");
				System.out.println("Looking to Generate a Question Paper? Enter 2.");
				choice=inp.nextInt();
				int choice2;
				switch(choice)
				{
					case 1:choicecheck=0;
						log.log(Level.INFO,"Edit Mode entered.");
						System.out.println("What subject does the question belong which you want to add/delete/edit?");
						String qsubj=inp.next();
						System.out.println("What topic does the question belong which you want to add/delete/edit?");
						String qtopic=inp.next();
						System.out.println("Enter:");
						System.out.println("\t1 for Adding a Question");
						System.out.println("\t2 to Delete a Question");
						System.out.println("\t3 to Edit a Question");
						choice2=inp.nextInt();
						switch(choice2)
						{
						case 1: System.out.println("You wish to ADD a question.");
								editmode.qadd(qsubj,qtopic);
								break;
						case 2: System.out.println("You wish to DELETE a question.");
								editmode.qdelete(qsubj,qtopic);
								break;
						case 3: System.out.println("You wish to EDIT a question.");
								editmode.qedit(qsubj,qtopic);
								break;
						}
						break;
					case 2:choicecheck=0;
						log.log(Level.INFO,"QPGM");
						System.out.println("Following Questions are being printed in QuestionBank.db:-");
						qpgen.Question_paper();
	                    System.out.println("ANSWER PAPER");				
	                    String s2=qpgen.Answer_paper();
	                    System.out.println(s2);
		                                 break;
					default:choicecheck=1;
					System.out.println("Please enter numbers 1 or 2 only.");
				}
			}while(choicecheck!=0);
		System.out.println("Successfully Executed. Enter 0 to exit and anything else to remain in the program:");
		choice1=inp.next();
		}while(choice1!="0");
	}
}
