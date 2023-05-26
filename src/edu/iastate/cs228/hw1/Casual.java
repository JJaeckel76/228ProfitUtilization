package edu.iastate.cs228.hw1;

public class Casual extends TownCell {
	
	public Casual(Town p, int r, int c) {
		super(p, r, c);
	}
	
	@Override
	public State who() {
		return State.CASUAL;
	}
	
	
	
	@Override
	public TownCell next(Town pNew) {
		if(nCensus[RESELLER] > 0) {
			return new Outage(pNew, row, col);
		}
		if(nCensus[STREAMER] > 0) {
			return new Streamer(pNew, row, col);
		}
		if(nCensus[CASUAL] >= 5) {
			return new Streamer(pNew, row, col);
		}			
		return new Casual(pNew, row, col);
	}
	
	@Override
	public String toString() {
		
		return "C";
		
	}

}
