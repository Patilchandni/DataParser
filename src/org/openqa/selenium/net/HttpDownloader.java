package org.openqa.selenium.net;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HttpDownloader {

	public static void main(String[] args) throws Exception{
		
		 String fileURL = "https://www.metoffice.gov.uk/climate/uk/summaries/datasets#rankOrdered"; 		 
		 HttpDownloadUtility.downLoadFiles(fileURL);
	    
	     String DownLoadedFilesFolder = "DownLoadedFiles";
	     String CSVFileName = "Weather.csv";
	   //  ReadFromTxtWriteToCSV.readFromTxtWriteToCSV(DownLoadedFilesFolder,CSVFileName );
	}
}
