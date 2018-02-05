
package org.openqa.selenium.net;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;		
import javax.net.ssl.HttpsURLConnection;


public class HttpDownloadUtility
{

	private static final int BUFFER_SIZE = 4096;
	 
	// Acess URLs of the files to be downloaded
   public static ArrayList<String> accessLinks(String fileURL)
      {
		//U have to add CromeDriver.exe
			File file = new File("chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

			WebDriver driver = new ChromeDriver();	
			driver.get(fileURL);					
			int count =0;
			ArrayList<String> links = new ArrayList<String>();
     
			for(int i=2;i<7;i++)
			{
    	 
				for(int j=2;j<7;j++)
				{
					WebElement wb = driver.findElement(By.xpath("//*[@id='wxDetail']/article/table[1]/tbody/tr["+i+"]/td["+j+"]/a"));				
					String url = wb.getAttribute("href");	     
					links.add(url);
				}
			}
    	   
		 return links;	    
	}
	
       
// Download single file
  public static void copyURLToFile(URL url, File file)
  {	
	try 
	{
			InputStream input = url.openStream();
			if (file.exists()) 
			{
				if (file.isDirectory())
					throw new IOException("File '" + file + "' is a directory");
				
				if (!file.canWrite())
					throw new IOException("File '" + file + "' cannot be written");
			}
			else
			{
				File parent = file.getParentFile();
				if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}

			FileOutputStream output = new FileOutputStream(file);

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer)))
			{
				output.write(buffer, 0, n);
			}

			input.close();
			output.close();
			
			System.out.println("File '" + file + "' downloaded successfully!");
			
		}
		catch(IOException ioEx) 
		{
			ioEx.printStackTrace();
		}
	}

    
//Fetch URLs and Download Files
public static void downLoadFiles(String fileURL)
{	
	ArrayList<String> links = new ArrayList<String>();	
	try 
	{      
     	links = HttpDownloadUtility.accessLinks(fileURL);
     
    	int count=1;
    	for (String fileToDownload : links)
    	{
    		//System.out.println(fileToDownload);
    		fileToDownload="https"+fileToDownload.substring(4,fileToDownload.length());
    		System.out.println(fileToDownload);
    		URL url = new URL(fileToDownload);
    		
    		//File where to be downloaded
    		File file = new File("DownLoadedFiles\\file"+count+".txt");
    		count++;
    		HttpDownloadUtility.copyURLToFile(url, file);
    		
    	}
    	
    	
    	
        
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

}