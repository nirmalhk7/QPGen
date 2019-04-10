/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.*;
import java.util.Scanner;
import java.sql.*;
package qpgen1;

/**
 *
 * @author HP
 */

public void qEdit( String Subj,String topic ) {
                
		LogManager logmgr = LogManager.getLogManager();
		Logger l= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		l.log(Level.INFO,"QEdit Entered.");
	
	
	     try {
                Scanner inp=new Scanner(System.in);
	    	  Connection conn = connect();
	          conn.setAutoCommit(false);
	           l.log(Level.INFO,"QEdit Try Entered.");
	         //Show list of Questions that come under that category
	          String printall="SELECT * FROM QBANK WHERE qtopic= '"+topic+"' AND qsubject= '"+Subj+"';";
	          printtable(printall,Subj,topic);
		  System.out.println("Enter the Question Number which you wish to edit:");
	         int qnos=inp.nextInt();
	      
		System.out.println("You wish to update Question "+qnos);
                
        
                
             String query = "UPDATE QBANK SET QDESC=?,OP1=?,OP2=?,OP3=?,OP4=?,CORRECTOP =?,QHARDNESS =? WHERE QNOS=?";          
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
              catch (Exception e)
            {
                System.err.println("Got an exception! ");
                 System.err.println(e.getMessage());
            }
}

