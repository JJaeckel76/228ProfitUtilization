package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Justin Jaeckel
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town oldTown) {
		Town newTown = new Town(oldTown.getLength(), oldTown.getWidth());
		
		for (int r = 0; r < oldTown.getLength(); r++) {
			for (int c = 0; c < oldTown.getWidth(); c++) {
				
				oldTown.grid[r][c].census(TownCell.nCensus);				
				newTown.grid[r][c] = oldTown.grid[r][c].next(newTown);
				
				if(oldTown.grid[r][c].who() != State.OUTAGE && oldTown.grid[r][c].who() != State.RESELLER && (TownCell.nCensus[TownCell.EMPTY] + TownCell.nCensus[TownCell.OUTAGE])< 2) {
					newTown.grid[r][c] = new Reseller(newTown, r, c);
				}
				
			}
		}
		return newTown;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int profit = 0;
		for (int row = 0; row < town.getLength(); row++) {
			for (int col = 0; col < town.getWidth(); col++) {
				if(town.grid[row][col].who() == State.CASUAL) {
					profit++;
				}
			}
		}
		return profit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Town town = null;
		
		
		System.out.println("Enter 1 to fill grid from a file. Enter 2 to fill randomly with seed\n");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		
		if(option == 1) {
			System.out.print("File name: ");
			try {
				town = new Town(scan.next());
			} catch (FileNotFoundException e) {
				System.out.println("Can't open file. Check if it exists and is in the right format.");
				return;
			}
		}
		else {
			System.out.print("Enter rows columns and seed: ");
			int r, c, s;
			r = scan.nextInt();
			c = scan.nextInt();
			s = scan.nextInt();
			town = new Town(r, c);
			town.randomInit(s);
		}
		int p = 0;
		
		for(int itr = 0; itr < 12; itr++) {
			p += getProfit(town);
			town = updatePlain(town);
			
		}
		
        double adv = p / 12.0;
        double percentage = adv / (town.getLength() * town.getWidth()) * 100;
        
        System.out.printf("%.2f", percentage);
        System.out.println("%");
	}
	
	public static double getYearProfit(Town town) {
		int p = 0;
		for(int itr = 0; itr < 12; itr++) {
			p += getProfit(town);
			town = updatePlain(town);
			
		}
        double adv = p / 12.0;
        double percentage = adv / (town.getLength() * town.getWidth()) * 100;
        
        return percentage;
	
	}
}
