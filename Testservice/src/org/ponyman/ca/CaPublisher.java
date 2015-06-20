package org.ponyman.ca;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.ponyman.ca.business.CaServiceImpl;


/**
 * Backbone of WebService exposing the functional webmethod.
 * 
 * @author Ponyman
 * @version 1.0
 */

@WebService
public class CaPublisher {
	
	CaServiceImpl caService = new CaServiceImpl();

	/**
	 * 
	 * @param N Size of the automata. 
	 * @param rule Automata rule.
	 * @return Call to actual function executing business logic part.
	 */
	@WebMethod
	public List<Integer> getCaData(int N, int rule)
	{
		return caService.getCaData(N, rule);
	}
}
