package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author <<Write your name here>>
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File inputFile = new File(inputFileName);
		Scanner scan = new Scanner(inputFile);

		
		String lenWid = scan.nextLine();
		length = Integer.parseInt(lenWid.split(" +")[0]);
		width = Integer.parseInt(lenWid.split(" +")[1]);

		
		grid = new TownCell[length][width]; // create the Town
		for(int r = 0; r<length; r++) {
			String line = scan.nextLine();
			for(int c=0; c<width; c++)
				switch (line.charAt(c*2)) {
				case 'R':
					grid[r][c] = new Reseller(this, r, c);
					break;
				case 'E': 
					grid[r][c] = new Empty(this, r, c);
					break;
				case 'C': 
					grid[r][c] = new Casual(this, r, c);
					break;
				case 'O': 
					grid[r][c] = new Outage(this, r, c);
					break;
				case 'S': 
					grid[r][c] = new Streamer(this, r, c);
					break;
				}
		}
		scan.close();
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for (int r = 0; r < length; r++)
			for (int c = 0; c < width; c++) {
				switch (rand.nextInt(5)) {
					case TownCell.RESELLER:
						grid[r][c] = new Reseller(this, r, c);
						break;
					case TownCell.EMPTY: 
						grid[r][c] = new Empty(this, r, c);
						break;
					case TownCell.CASUAL:  
						grid[r][c] = new Casual(this, r, c);
						break;
					case TownCell.OUTAGE:  
						grid[r][c] = new Outage(this, r, c);
						break;
					case TownCell.STREAMER: 
						grid[r][c] = new Streamer(this, r, c);
						break;
				}
			}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		for (TownCell[] row : grid) { 
			for (TownCell l : row) {
				s += l + "\t";
			}
			s += "\n"; 
		}
		return s;
	}
}
