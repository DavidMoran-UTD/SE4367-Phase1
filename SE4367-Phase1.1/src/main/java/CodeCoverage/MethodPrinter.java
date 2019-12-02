package CodeCoverage;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/*
 * This class will print out the methods that we are going through 
 */
class MethodPrinter extends MethodVisitor implements Opcodes {

	// String to hold the name
	String className;
	
	// This method will print the class name that we are visiting
    public MethodPrinter(final MethodVisitor mv, String className) {
    			// Call the method from the superclass
            super(ASM5, mv);
            // Set the name to the one given in the arguments
            this.className=className;
    }
		
    // Override the visitLineNumber method from the superclass that will print out the information 
    // for the class that we are visiting
	@Override
	public void visitLineNumber(int line, Label start) {	
			// Print the name and the line number that we are visiting
			mv.visitLdcInsn(className+":"+line+"\n");
			// Call visitMethodInsn
			mv.visitMethodInsn(INVOKESTATIC, "CodeCoverage/StatementCoverageData", "lineExecuted", "(Ljava/lang/String;)V", false);
			// Call the superclass method with our parameters
			super.visitLineNumber(line, start);
	}
}