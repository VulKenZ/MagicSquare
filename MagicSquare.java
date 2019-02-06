/**
 *  This file is to import magic square files and check the validity of the numbers
 *	@author Nicholas Prussen
 *	@version 1.0 CS221 Spring 2019
 */

import java.util.*;
import java.awt.*;
import java.io.*;


public class MagicSquare {
	
	private static int n;
	
	//Copy of N taken in from Driver
	private static int nGlobal;
	
	//initialized main double array for checking
	private static int [][] magicSquare;
	
	//Used for checking matrix
	private static boolean isMatrix = true;
	private static boolean check = true;
	
	//Initialized for writing to file
	private static int [][] outMatrix;
	
	
	
	/**
	 * Executed from the -check command and prints the validity of a matrix read from file
	 * 
	 * @param filename name of the file to import and check, passed to the readMatrix() method
	 */
	public static void checkMatrix(String filename) {
		
		readMatrix(filename);
		
		//Prints out Matrix
		int counter = 0;
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n; y++) {
				counter++;
				System.out.print(magicSquare[x][y] + " ");
				if(counter % n == 0) {
					System.out.println("");
				}
			}
		}
		System.out.println("");
		//End of Printing Matrix
		
		
		
		//This loop is to stop checks from overriding the boolean if one already proved false
		while(check == true) {
			//Start of checking matrix
			int mainCheck = 0;
			
			for(int i = 0; i < n; i++) {
				mainCheck += magicSquare[i][i];
			}
			
			//Rows first
			for(int i = 0; i < n; i++) {
				int rowNumber = 0;
				for(int k = 0; k < n; k++) {
					rowNumber += magicSquare[i][k];
				}
				if(mainCheck != rowNumber) {
					check = false;
					isMatrix = false;
				}
			}
		
			//Columns next
			for(int i = 0; i < n; i++) {
				int colNumber = 0;
				for(int k = 0; k < n; k++) {
					colNumber += magicSquare[k][i];
				}
				//Check if numbers match
				if(mainCheck != colNumber) {
					check = false;
					isMatrix = false;
				}
			}
			
			//If nothing returns false, end the loop
			check = false;
		}
		//End of checking matrix
		
		
		//Final matrix determination
		if(isMatrix == false) {
			System.out.println("is not a magic square");
		}
		else {
			System.out.println("is a magic square");
		}
		
		
	}
	
	/**
	 * Reads in the matrix from file and writes to a temporary double array
	 * @param filename filename passed down from checkMatrix(), file to check
	 */
	private static void readMatrix(String filename) {
		try {
			Scanner file = new Scanner(new File(filename));
			int counter = 0;
			n = file.nextInt();
			System.out.println("The matrix: ");
			System.out.println("");
			magicSquare = new int[n][n];
			for(int x = 0; x < n; x++) {
				for(int y = 0; y < n; y++) {
					magicSquare[x][y] = file.nextInt();
				}
			}
		}
		//File not found, only needed exception
		catch(FileNotFoundException e) {
			System.out.println("File Not Found, Try Again");
		}
	}
	
	/**
	 * This method creates a magic square inside a double array and calls writeMatrix() to write to file
	 * 
	 * @param N number used to determine the gridsize
	 * @param filename used to pass through writeMatrix() to set filename
	 * @throws IOException
	 */
	public static void createMagicSquare(int N, String filename) throws IOException {
		//Assigning global variable
		nGlobal = N;
		
		//Algorithm for making magic square
		if(N%2 != 0) {
			outMatrix = new int [N][N];
			int row = N-1;
			int col = N/2;
			outMatrix[row][col] = 1;
			for(int i = 2; i <= N*N; i++) {
				if(outMatrix[(row+1) % N][(col + 1) % N] == 0) {
					row = (row + 1) % N;
					col = (col + 1) % N;
				}
				else {
					row = (row - 1 + N) % N;
				}
				outMatrix[row][col] = i;
			}
			//Print the Matrix to file
			writeMatrix(filename);
		}
		
		//Throw exception for even numbers
		else {
			System.out.println("This number must be odd!");
		}
		
		
		
		
	}
	
	/**
	 * This takes the global array set by createMagicSquare() and writes to file in specific format
	 * 
	 * @param filename filename passed from writeMagicSquare() to use when creating
	 * @throws IOException
	 */
	private static void writeMatrix(String filename) throws IOException {
		File file = new File(filename);
		PrintWriter outFile = new PrintWriter(new FileWriter(file));
		
		//Print the grid number of magic square
		outFile.println(nGlobal);
		
		//Loop and write square to file
		int counter = 0;
		for(int x = 0; x < nGlobal; x++) {
			for(int y = 0; y < nGlobal; y++) {
				counter++;
				outFile.print(outMatrix[x][y] + " ");
				if(counter % nGlobal == 0) {
					outFile.println("");
				}
			}
		}
		outFile.close();
	}
}
