package CodeCoverage;

import java.lang.instrument.Instrumentation;

/*
 * This class is the primary agent for our project.  It will 
 */
public class Agent 
{	
    public static void premain(String agentArgs, Instrumentation inst)
    {
        inst.addTransformer(new MyClassFileTransformer()); 

    }
}