package edu.iastate.cs228.hw1;

public class Reseller extends TownCell{
	
	public Reseller(Town p, int r, int c) {
		super(p, r, c);
		
	}

	@Override
	public State who() {
		
		return State.RESELLER;
	}
	
	/**
	 * Rules for Reseller 
	 */
	@Override
	public TownCell next(Town pNew) {
		
		if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) { 
			return new Empty(pNew, row, col);
		}
		if(nCensus[CASUAL] >= 5) {
			return new Streamer(pNew, row, col);
		}
		else {
			return new Reseller(pNew, row, col);
		}
		
	}

	@Override
	public String toString() {
		
		return "R";
		
	}
	

}
