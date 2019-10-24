package CodeCoverage;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/*
 * This class will print out the classes that we will be visiting (similar to MethodPrinter.java)
 */
public class ClassPrinter extends ClassVisitor {

	// String to hold the class name
    private String className;
    // String to be used as a temporary variable 
	public static String temp;
	
	// Constructor
	public ClassPrinter(final ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }
	
	// Override the superclass method for visit(int, int, String, String, String, String[]) 
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // Set the class name equal to the name given in the arguments
    		className = name;
    		// Call the superclass method
        super.visit(version, access, name, signature, superName, interfaces);
    }

    // Override the superclass method for visitMethod(int, String, String, String, String[])
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		// Call the superclass method and store it in the new MethodVisitor we have created
    		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
    		// Return null if MethodVisitor mv is null else return a new MethodPrinter that we will
    		// create with our MethodVisitor and the className that we stored from the Overridden visit method
        return mv == null ? null : new MethodPrinter(mv, className);
    }
	
}
