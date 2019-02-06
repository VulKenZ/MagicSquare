import java.io.IOException;

/**
 *  This file is the main driver to call the MagicSquare class
 *	@author Nicholas Prussen
 *	@version 1.0 CS221 Spring 2019
 */
	
public class MagicSquareDriver {
	
	private static String filename;
	private static String method;
	private static int createNumber;
	
	/**
	 * This takes in the commandline arguments and calls the specific methods from MagicSquare
	 * @param args commandline inputs
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		//Assign first argument to method variable
		method = args[0];
		
		
		//Executes check method
		if(method.equals("-check")) {
			//Test whether reading in is working correctly
			filename = args[1];
			MagicSquare.checkMatrix(filename);
		}
		
		//Executes create method
		else if (method.equals("-create")) {
			filename = args[1];
			createNumber = Integer.parseInt(args[2]);
			MagicSquare.createMagicSquare(createNumber, filename);
		}
		
		//Error handling exception
		else {
			System.out.println("Not a valid input, try again");
		}
	}

}
