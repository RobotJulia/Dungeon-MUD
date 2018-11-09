// This is a utility for working with files
import java.util.Scanner;
import java.io.*;

public class FileUtil
{
	// This method returns a Scanner reference using a try-catch structure.
	//  Use this method when the file name is already known...
   public static Scanner getScanner(String fileName)
   {
      Scanner fileScanner = null;
      File fileHandle;
   	
      try
      {
      //  Get a "handle" on the file...
         fileHandle = new File(fileName);
      
      //  Attach the file handle to the Scanner object...
         fileScanner = new Scanner(fileHandle);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File " + fileName + " was not found.");
      }
   	//  Your work is done here - return the result of your labor...
      return  fileScanner;      
   }
   
	//  _______________________________________________________
	//  This method also returns a file handle.
	//    Use this method when you want to prompt the user for the file name...
   public static Scanner openInputFile() throws IOException
   {
      Scanner kb;  
      Scanner fileScanner = null;
      File fileHandle;
      String fileName;
   	
   	//  Get a keyboard scanner object...		
      kb = new Scanner(System.in);
   	
   	//  Prompt the user for a file name...
      System.out.print("\nPlease enter the name of the file to open: ");
      fileName = kb.nextLine();
   	
   	//  Get a "handle" on the file...
      fileHandle = new File(fileName);
   	
      //  Attach the file handle to the Scanner object...
      fileScanner = new Scanner(fileHandle);
   
      return fileScanner;
   }


	//  Output file utilities____________________________________
					
   public static PrintStream openOutputFile(String fileName) throws IOException
   {
      PrintStream fileWriter;
      fileWriter = new PrintStream(fileName);
   				
      return fileWriter;
   }
	
	// __________________________________________________________
   public static PrintStream openOutputFile() throws IOException
   {
      Scanner kb;  
      PrintStream fileWriter;
      String fileName;
   	
   	//  Get a keyboard scanner object...		
      kb = new Scanner(System.in);
   	
   	//  Prompt the user for a file name...
      System.out.print("\nPlease enter the name of the file to open: ");
      fileName = kb.nextLine();
   	
      fileWriter = new PrintStream(fileName);
   				
      return fileWriter;
   }

   public static Scanner openInputFile(String fileName) throws FileNotFoundException {
	Scanner kb;  
    Scanner fileScanner = null;
    File fileHandle;
    
 	//  Get a "handle" on the file...
    fileHandle = new File(fileName);
 	
    //  Attach the file handle to the Scanner object...
    fileScanner = new Scanner(fileHandle);
 
    return fileScanner;
	
	}   
}

//  =========================================================

//  Note this is a tester class for file stuff:
// import java.io.*;
// import java.util.Scanner;
// 
// //  This class will use the FileUtil class to open a file
// //    and then will read the records...
// 
// public class FileTester
// {
// 
// 	public static void main(String[] args) throws IOException
// 	{
// 			
// 			Scanner fin;
// 			String sWork;
// 			
// 			// This version is called when you know the name of the file..
// 			fin = FileUtil.openInputFile("test.txt");
// 			
// 			// This is the basic structure for reading a file...
// 			while (fin.hasNext())
// 			{
// 				sWork = fin.nextLine();
// 				System.out.println(sWork);			
// 			}
// 			
// 			fin.close();
// 
// 			// This version is called when you don't know the file name...
// 			fin = FileUtil.openInputFile();
// 
// 			// This is the basic structure for reading a file...
// 			while (fin.hasNext())
// 			{
// 				sWork = fin.nextLine();
// 				System.out.println(sWork);			
// 			}
// 			
// 			fin.close();
// 	}
// }