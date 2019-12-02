package CodeCoverage;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;

/*
 * This class will be used to transform the class files 
 */
public class MyClassFileTransformer implements ClassFileTransformer{

	// Override the superclass method with our own transform method
    @Override
    public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException 
    {
//			if(s.startsWith("org/joda/time"))
    			// If the class is from the apache commons library, transform the class file
            if(s.startsWith("org/apache/commons/dbutils"))				
			{	
            	// Reader to read in the class file in bytes
            ClassReader reader = new ClassReader(bytes);
            // Writer to write the class file we just read
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
            // Printer to print the class file we just wrote 
            ClassPrinter visitor = new ClassPrinter(writer);
            // Parse the class file
            reader.accept(visitor, 0);
            // Return the transformed class file
            return writer.toByteArray();
			}	
            // We didn't transform the file and simply return the bytes we were given
			return bytes;
    }

}

