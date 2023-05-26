package edu.iastate.cs228.hw1;

public class Streamer extends TownCell{
	
	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}
	
	@Override
	public State who() {
		return State.STREAMER;
	}
	
	@Override
	public TownCell next(Town pNew) {
		if(nCensus[RESELLER] > 0) {
			return new Outage(pNew, row, col);
		}
		if(nCensus[OUTAGE] > 0) {
			return new Empty(pNew, row, col);
		}
		if(nCensus[CASUAL] >= 5) {
			return new Streamer(pNew, row, col);
		} 
		return new Streamer(pNew, row, col);
	}
	
	@Override
	public String toString() {
		
		return "S";
		
	}

}
