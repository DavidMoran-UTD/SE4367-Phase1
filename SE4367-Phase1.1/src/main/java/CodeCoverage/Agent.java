package CodeCoverage;

import java.lang.instrument.Instrumentation;

/*
 * This class is the primary agent for our project.  It will 
 */
public class Agent 
{	
	// This is our premain method that executes first
    public static void premain(String agentArgs, Instrumentation inst)
    {
    		// Add the transformed class file to the Instrumentation 
        inst.addTransformer(new MyClassFileTransformer()); 

    }
}