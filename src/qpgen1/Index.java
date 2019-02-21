package qpgen1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Index {
//Linking the Java program with SQL
	public static void marriage() {
		System.out.println("\nMarriage successful");
	}
//Main thing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner inp=new Scanner(System.in);
		System.out.println("Welcome to Question Paper Generator.");
		System.out.println("Looking to Add/Edit/Delete Questions from the database? Enter 1.");
		System.out.println("Looking to Generate a Question Paper? Enter 2.");
		int choice=inp.nextInt();
		marriage();
	}

}
