package org.openqa.selenium.net;
import java.io.*;

public class ReadFromTxtWriteToCSV 
{
	//Function to read from Text and Write to CSV
	public static void readFromTxtWriteToCSV(String DownloadedFilesfolderLocation,String CSVFile) throws Exception 
	{
		// TODO Auto-generated method stub
		File DownloadedFilesfolder = new File(DownloadedFilesfolderLocation);
		FileWriter csvwriter = new FileWriter(CSVFile);
		PrintWriter writeToCSV = new PrintWriter(csvwriter);
	  
		File[] listOfFiles = DownloadedFilesfolder.listFiles();
		
			for (int i = 0; i < listOfFiles.length; i++)
			{
				
				File file = new File(listOfFiles[i].getAbsolutePath());
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while((line=br.readLine())=="\n")
				{						  
				}
				int linecount =1;	
				
				String[] result = line.split("\\s+");
				String country = result[0];				
				String temp = result[1];
				
				if(temp.equals("Minimum"))
				{
					temp = "Min Temp";
				}
				if(temp.equals("Maximum"))
				{
					temp = "Max Temp";
				}
				
				if(temp.equals("Mean"))
				{
					temp = "Mean Temp";
				}
				String  heading="";
				while(linecount!=8)
				{
					heading=br.readLine();
					linecount++;
					
				}				
				
				String[] headings = heading.split("\\s+");
				int headingLength = headings.length;
				while ((line = br.readLine()) != null)
				{	
					int month=1;
				    result = line.split("\\s+");
				    
				    result = line.split("\\s+");
				    
				    if(result.length<headingLength)
				    {
				    	result= handleMissingData(line,headingLength);
				    }
					for(int j=1;j<result.length-2;j=j+2)
				    {
						writeToCSV.println(country +"," +temp +","+result[j]+","+headings[month] +","+result[j+1]);
						 month=month + 2;							   
				    }
				}	
			  }
			
			System.out.println("Data has been written to CSV file.");
			//Flush the output to the file
			writeToCSV.flush();
			       
			//Close the Print Writer
			writeToCSV.close();
				       
			//Close the File Writer
		    csvwriter.close();  
	  }
	
	
	private static String[] handleMissingData(String line, int headingLength) {
		// TODO Auto-generated method stub
		int countSpaces=0;
		  for (int i=0;i<line.length();i++){
	           if(line.charAt(i)==' ')
	        	   countSpaces++;
	           else
	        	   countSpaces=0;
	       
	            if(countSpaces>=12){
	        	//countSpaces.out.println("Do");
	            	line=line.substring(0,i-countSpaces)+" NA NA "+line.substring(i,line.length());
	            	countSpaces=0;
	        }
	            
		   }		  
		  String[] finalresult = new String[headingLength];
		  String[] result = line.split("\\s+");
		  int i=0;
		  while(i<result.length)
		  {
			  finalresult[i]=result[i];
              i++;
		  }
		 
		  
		  while (i<headingLength)
		  {
			  finalresult[i]="NA";			 
			  i++;
			  
		  }
		  
		return finalresult;
	}
	
	
}

