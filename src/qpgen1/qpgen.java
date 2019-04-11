package qpgen1;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*; 
import java.util.*; 

public class qpgen {
	 static int n;
	
    public static int qno[]= new int[100];
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
    public static int[] fixQno()
	{
		LogManager logmgr = LogManager.getLogManager();
		Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.log(Level.INFO,"QPGen:FixQno");
        try
		{ 
		   	Scanner scan=new Scanner(System.in);
		 		
	   		Connection con = editmode.connect();
			Statement printall=con.createStatement();
		
			String sql="SELECT QSUBJECT FROM QNBANK";
			ResultSet r = printall.executeQuery(sql);
            System.out.println("Following is the list of subjects:-");
			int count=0;
			while(r.next())
			{
				count++;
			}
			 r = printall.executeQuery(sql);
	 		String s[]=new String[count];
	 		String qsubject;
      			s[0]=r.getString("qsubject");
    			int j=1;
			while ( r.next() ) 
			{	int flag=0;
			  	qsubject=r.getString("qsubject");
			 	for(int i=0;i<j;i++)
			 	{
			 		if(s[i].equals(qsubject))
			 		{
			 			flag=1;
			 			break;
			 		}
			 	
			         }
					 if(flag==0)
					 {
			 			s[j]=qsubject;
			 			j++;
			 		 }
				}

					for(int i=0;i<j;i++)
					{
						System.out.println(s[i]);
					}
				System.out.println("\nEnter Subject of the Test:-");
				String qsub=scan.next();
				
                con = editmode.connect();
				Statement print=con.createStatement();
				System.out.println("Topic List");
				sql="SELECT DISTINCT QTOPIC FROM QNBANK WHERE QSUBJECT ='"+qsub+"';";
                                    
				ResultSet a = print.executeQuery(sql);
                                    
			        count=0;
				while(a.next())
				{
					count++;
				}
				a= print.executeQuery(sql);
	 			String []s2=new String[count];                  
      			j=0;
                String qtopic;
				while ( a.next() )
			    {
					int flag=0;
				 	 qtopic=a.getString("qtopic");
                    System.out.println(qtopic);
			    }
				System.out.println("Enter topic of the test:-");
				String topic=scan.next();
				int arr[]=new int[100];
                            print=con.createStatement();
			    sql="SELECT QSUBJECT,QTOPIC,QNOS FROM QNBANK";
			    ResultSet d=print.executeQuery(sql);
			    int i=0;
                while(d.next())
                {
            	
            	  String subj=d.getString("qsubject");
            	  String top=d.getString("qtopic");
            	  int no=d.getInt("qnos");
            	  if(subj.equals(qsub)&&(top.equals(topic)))
            	  {
            		
            		  arr[i]=no;
            		  i=i+1;
            	  }
               }
          
       
            System.out.println("enter the number of questions");
            n=scan.nextInt();
            

             ArrayList<Integer> list = new ArrayList<Integer>();
   			 for (int k=0; k<i; k++) {
        			list.add(new Integer(k));
   				 }
    		Collections.shuffle(list);
    		int []qno=new int[100];
         if(n<i)
         {
         }
         else
         {  n=i;
            System.out.println("The no. of questions of the topic are "+n);
         }
         if(n!=0)
         {
    		for(int k=0;k<n;k++)
          	{
           	 		qno[k]=arr[list.get(k)];
           	}
                    log.log(Level.INFO,"the question numbers are-");
                   
                    for(int k=0;k<n;k++)
                           System.out.println(qno[k]);
         }          

           }	 
                catch (Exception e)
                 {
                 System.err.println("Got an exception! ");
                 System.err.println(e.getMessage());
                }
		
	return qno;
	}
	
 public static String Question_paper()
 {
	 LogManager logmgr = LogManager.getLogManager();
	 Logger log= logmgr.getLogger(Logger.GLOBAL_LOGGER_NAME);
			
	 int ans[]= fixQno();
     String s=new String();
     try
	{
			Connection con = editmode.connect();
			Statement printall=con.createStatement();
			printall=con.createStatement();
			for(int i=0;i<n;i++)
	   		{
		 		String sql="SELECT * FROM QNBANK WHERE QNOS='"+qno[i]+"';";
		 		ResultSet r = printall.executeQuery(sql);
		 		while ( r.next() )
		 		{
		     	   int no = r.getInt("qnos");
		     	   String  desc = r.getString("qdesc");
		     	   String op1 = r.getString("op1");
		     	   String op2 = r.getString("op2");
		     	   String op3 = r.getString("op3");
		     	   String op4 = r.getString("op4");
		     	   String correctop = r.getString("opA");
		     	   int qhardness=r.getInt("qhardness");
		     	   String qsubject=r.getString("qsubject");
		     	   String qtopic=r.getString("qtopic");
		           s+="i+1"+"\n";
		           s+= "QUESTION = " + desc +"\n" ;
		           s+= "OPTION 1 = " + op1 +"\n";
		            s+= "OPTION 2 = " + op2 +"\n";
		            s+= "OPTION 3 = " + op3 +"\n";
		            s+="OPTION 4 = " + op4 +"\n";
		            log.log(Level.INFO,"Question String Created");
		            writeonfile(s);
		           
		 		}
	   		}
		}		
	catch (SQLException e1)
	{
		// TODO Auto-generated catch block
		log.log(Level.INFO,"Error!");
		e1.printStackTrace();
	}
	
	return s;
}
public static String Answer_paper()
{
		
    String s=new String();
		
	try
	{
		Connection con = editmode.connect();
		Statement printall=con.createStatement();
		printall=con.createStatement();
		for(int i=0;i<n;i++)
   		{		 
	 		String sql="SELECT * FROM QNBANK WHERE QNOS='"+qno[i]+"';";
	 		ResultSet r = printall.executeQuery(sql);
	 		while ( r.next() ) 
	 		{
	     	   int no = r.getInt("qnos");
	     	   String  desc = r.getString("qdesc");
	     	   String op1 = r.getString("op1");
	     	   String op2 = r.getString("op2");
	     	   String op3 = r.getString("op3");
	     	   String op4 = r.getString("op4");
	     	   String correctop = r.getString("opA");
	     	   int qhardness=r.getInt("qhardness");
	     	   String qsubject=r.getString("qsubject");
	     	   String qtopic=r.getString("qtopic");
	           
	           s+= "QUESTION = " + desc +"\n";
	           s+= "OPTION 1 = " + op1 +"\n";
	            s+= "OPTION 2 = " + op2+"\n";
	            s+= "OPTION 3 = " + op3+"\n";
	            s+="OPTION 4 = " + op4+"\n";
	           s+="CORRECT OPTION = "+ correctop+"\n";
	       }
   		}
    }	
	 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return s;
	}

}
