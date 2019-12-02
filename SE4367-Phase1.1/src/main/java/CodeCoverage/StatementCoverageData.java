package CodeCoverage;

import java.io.*;
import java.util.*;

/*
 * This class will be used to record the statement coverage data.
 * It will store the lines that we have executed in a hash which we will then write into a file 
 * so the user can open the file and see all of the lines that have been executed. 
 */
public class StatementCoverageData {
	// Create the hash that we will be using to store the Statement Coverage Data
	static HashSet<String> coverageHash = new HashSet<String>();
		
	// This method will add the lines that we have covered into the hash
	public static void lineExecuted(String str) 
	{
		// Add the line to the hash
		coverageHash.add(str);
	}
	
	// This method will print the lines from the hash into a file 
	public static void writeIntoFile(FileWriter writer)
	{
		// Try-catch block that will print the lines into a file
		try 
		{		  
			// Iterator that will be used to iterate through the hash
		    Iterator iterator = coverageHash.iterator();  
            String temp="";	// Temporary variable used for the itration
            // While there is data in the iterator, add a line from the iterator to the temporary string
            while (iterator.hasNext())
                temp+= iterator.next() + System.lineSeparator();  
            // Print the string to the file
			writer.write(temp);	
			// Clear the Hash
			coverageHash.clear();
		}
		// Catch the exceptions and print the stack when we encounter an exception
		catch (Exception ex) 
		{
			ex.printStackTrace();	
		}
	}		
}
