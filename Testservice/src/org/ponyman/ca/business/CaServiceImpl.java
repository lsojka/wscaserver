package org.ponyman.ca.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of cellular automata business logic and inner workings.
 * <p>
 * @author Ponyman
 * @version 1.0
 */
public class CaServiceImpl {
	
	/**
	 * Summary of states of the automata.
	 */
	List<Integer> grid = new ArrayList<>();
	/**
	 * Current, singular, statate of the automata.
	 * (May be interpreted as strip of cells.)
	 */
	List<Integer> strip = new ArrayList<>();
	
	/**
	 * Decimal representation of the automata rule.
	 */
	int irule;
	/**
	 * Size of the automata.
	 */
	int N;
	/**
	 * Binary representation of the automata rule used in transformation steps.
	 */
	List<Integer> ruletab = new ArrayList<>();
	
	
	public CaServiceImpl() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * Function assembling and executing all steps of simulation.
	 * 
	 * @param _N Size of the automata.
	 * @param _rule Rule of the automata.
	 */
	public List<Integer> getCaData(int _N, int _rule)
	{
		// CLEAP UP EVERYTING BEFORE ANOTHER USE
		ruletab.clear();
		grid.clear();
		strip.clear();
		
		//1. get/assign the input data + the rule
		N = _N;
		setRule(_rule);
		//2. set up the grid -> no need for that
		//3. place the start
		for(int i = 0; i < N; i++)
			strip.add(0);
		strip.set(_N/2, 1);
		grid.addAll(strip);
		//4. walk the strip
		for(int i = 0; i<N-1; i++)
		{
			//grid.addAll(strip);
			this.walkTheStrip();
			grid.addAll(strip);		
		}
		
		//X. checksum
		return grid;
	}
	
	/**
	 * Transforming decimal rule to binary array used in transformations.
	 * 
	 * @param _r Rule in decimal representation.
	 */
	private void setRule(int _r)
	{
		String temp = Integer.toBinaryString(_r);
		for(int i = 0; i<temp.length(); i++)
		{
			this.ruletab.add( Character.getNumericValue( temp.charAt(i) ) );
		}
		
		while(ruletab.size() < 8)
		{
			ruletab.add(0, 0);
		}
	}
	
	/**
	 * Transformation method. 
	 * <p>
	 * Determines state of the cell in next step, using automat rule and states of the cell with its neighbours..
	 * @param p State of left hand-side neighbour cell.
	 * @param q State of the cell in question.
	 * @param r State of right hand-side neighbour cell.
	 * @return State of cell in next step.
	 */
	private int applyTheRule(int p, int q, int r)
	{
	    if      (p == 1 && q == 1 && r == 1) return ruletab.get(0);//this->ruletab[0];
	    else if (p == 1 && q == 1 && r == 0) return ruletab.get(1);
	    else if (p == 1 && q == 0 && r == 1) return ruletab.get(2);
	    else if (p == 1 && q == 0 && r == 0) return ruletab.get(3);
	    else if (p == 0 && q == 1 && r == 1) return ruletab.get(4);
	    else if (p == 0 && q == 1 && r == 0) return ruletab.get(5);
	    else if (p == 0 && q == 0 && r == 1) return ruletab.get(6);
	    else if (p == 0 && q == 0 && r == 0) return ruletab.get(7);

	    return 0;
	}
	
	/**
	 * Method performing single step of simulation on whole automata.
	 */
	private void walkTheStrip()
	{
		List<Integer> _strip = new ArrayList<>();
		for(int i = 0; i < N; i++)
			_strip.add(0);
		int p = 0;
		int q = 0;
		int r = 0;
		//
		for(int i = 0; i < N; i++)
		{
			if(i == 0)
				p = 0;
			else
				p = strip.get(i - 1);
			q = strip.get(i);
			if(i == N - 1)
				r = 0;
			else
				r = strip.get(i+1);
			_strip.set(i, applyTheRule(p,q,r));
		}
		strip.clear();
		strip.addAll(_strip);
	}
}	