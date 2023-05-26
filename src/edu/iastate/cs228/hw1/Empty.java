package edu.iastate.cs228.hw1;

public class Empty extends TownCell{
	
	public Empty(Town p, int r, int c) {
		
		super(p, r, c);
		
	}

	@Override
	public State who() {
		
		return State.EMPTY;
		
	}

	@Override
	public TownCell next(Town pNew) {
		
		return new Casual(pNew, row, col);
		
	}

	@Override
	public String toString() {
		
		return "E";
		
	}

}
