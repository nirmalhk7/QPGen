package qpgen1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class qpgen {
	static int timecalled;
	qpgen(){
		timecalled=0;
	}
	public static void firsttimerun() {
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		Scanner inp=new Scanner(System.in);
		System.out.println("University Name?:");
		String data=inp.nextLine();
		System.out.println("Subject Name?:");
		String sbj=inp.nextLine();
		System.out.println("Date of Examination:");
		String date=inp.nextLine();
		File file = new File("TestPaper.txt");
		 FileWriter fr = null;
	        try {
	            fr = new FileWriter(file);
	            fr.write(data+"\n"+sbj+"\n"+date+"\n\nNAME:-\nROLL NO.:-\nCLASS:-\n\n_________________________________________________________________\n");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally
	        {
	            //close resources
	            try {
	                fr.close();
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    log.log(Level.INFO,"First Run Successfull ");
	}
	public static void writeonfile(String data)
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"QPGen:FixQno"); 
		if(timecalled==0)
		{
			firsttimerun();
		}
		File file = new File("TestPaper.txt");
		 FileWriter fr = null;
	        try {
	            fr = new FileWriter(file,true);
	            fr.write(data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally
	        {
	            //close resources
	            try {
	                fr.close();
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    log.log(Level.INFO,"Filestream successful");
	 }
	public void fixQno()
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"QPGen:FixQno");
	}
}
