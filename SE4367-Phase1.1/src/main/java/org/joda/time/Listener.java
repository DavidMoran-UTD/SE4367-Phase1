
package org.joda.time;
 
import java.io.*;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import CodeCoverage.StatementCoverageData;

/*
 * This class is the JUnit Listener that will listen for the beginning and end of the JUnit test methods
 */
public class Listener extends RunListener
{
	// Create a FileWriter so we can write to our file	
	static FileWriter writer;
//	
	// This method listens to see when the JUnit test has started
	public void testRunStarted(Description description) throws java.lang.Exception 
	{
          try 
	  {
        	 // Create a file for output
		 File testFile = new File("stmt-cov.txt");
		 // If the file already exists, delete it 
		 if (testFile.exists())
			 testFile.delete();
         else		  
        	 	// Otherwise, create a new file
        	 	testFile.createNewFile();
		 // Link the writer to the file we just created
		 writer = new FileWriter("stmt-cov.txt",false);
//		 writer.write("Hello World");
	  }
      // Print out the stack if we encounter any errors
	  catch (Exception ex) 
	  {
	          ex.printStackTrace();
	
	  }
        }	
	
	// This method will listen for when the JUnit test have finished
	public void testRunFinished(Result result) throws java.lang.Exception
	{
		// Close the writer
		writer.close();
	}
	
	// This method will listen for when an atomic test is finished
	public void testFinished(Description description) throws java.lang.Exception
	{
		// Write the statement coverage we have collected into the file
        StatementCoverageData.writeIntoFile(writer);
    }

	// This method will listen for when an atomic test is starting
	public void testStarted(Description description) throws java.lang.Exception
	{
		// Add the following line to the writer 
		writer.write("[TEST] "+description.getClassName()+":"+description.getMethodName() + System.lineSeparator());
	}
}
