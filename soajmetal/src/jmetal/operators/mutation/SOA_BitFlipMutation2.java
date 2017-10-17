//  BitFlipMutation.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package jmetal.operators.mutation;

import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.BinarySolutionType;
import jmetal.encodings.solutionType.IntSolutionType;
import jmetal.encodings.solutionType.SOA_SOLUTION_TYPE;
import jmetal.encodings.variable.Binary;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Helper.Service;
import Helper.inputdataSOA;
import jmetal.core.Solution;
import jmetal.core.Solution;

/**
 * This class implements a bit flip mutation operator.
 * NOTE: the operator is applied to binary or integer solutions, considering the
 * whole solution as a single encodings.variable.
 */
public class SOA_BitFlipMutation2 extends Mutation {
  /**
   * Valid solution types to apply this operator 
   */

	//private static final List VALID_TYPES = Arrays.asList(BinarySolutionType.class,
      //BinaryRealSolutionType.class,
      //IntSolutionType.class) ;
	private static final List VALID_TYPES = Arrays.asList(SOA_SOLUTION_TYPE.class,
			  SOA_SOLUTION_TYPE.class,
			  SOA_SOLUTION_TYPE.class) ;
public static inputdataSOA z=new inputdataSOA();
public ArrayList<Service> RequiredClass=new ArrayList<Service>();
  
private Double mutationProbability_ = null ;
  
	/**
	 * Constructor
	 * Creates a new instance of the Bit Flip mutation operator
	 */
	public SOA_BitFlipMutation2(HashMap<String, Object> parameters) {
		super(parameters) ;
  	if (parameters.get("probability") != null)
  		mutationProbability_ = (Double) parameters.get("probability") ;  		
	} // BitFlipMutation

	/**
	 * Perform the mutation operation
	 * @param probability Mutation probability
	 * @param solution The solution to mutate
	 * @throws JMException
	 */
	public void doMutation(double probability, Solution offsbring) throws JMException {
	
	
		    
		    try {
		    	//System.out.println(offsbring.get);
		    int chrmosomesize= offsbring.getCompositionService().size();
		      //int random = PseudoRandom.randInt()%chrmosomesize;
		      int random = PseudoRandom.randInt()%chrmosomesize;
		      if (random<probability){
		    	  System.out.println("    classs   "+offsbring.getCompositionService().get(random).getServiceClass());//getSeviceClass();
			      
		    	  String Sclass=(String) offsbring.getCompositionService().get(random).getServiceClass();//getSeviceClass();
		      
		      for (int i=0; i<z.ServicePool.size();i++){
	//  if (z.ServicePool.get(i).getServiceFunctionality().equalsIgnoreCase(Sclass))
		    	  if (z.ServicePool.get(i).getServiceClass().equalsIgnoreCase(Sclass))
			RequiredClass.add(z.ServicePool.get(i));
		      }
		   //   z.ServicePool.get(9).
		int random2 = (PseudoRandom.randInt())%(RequiredClass.size());
		offsbring.getCompositionService().set(random,RequiredClass.get(random2));
		    		
		
		offsbring.getCompositionService().set(random,RequiredClass.get(random2));		    	  
		    
		      }
		    } catch (ClassCastException e1) {
		      Configuration.logger_.severe("SinglePointCrossover.doCrossover: Cannot perfom " +
		              "SinglePointCrossover");
		      Class cls = java.lang.String.class;
		      String name = cls.getName();
		      throw new JMException("Exception in " + name + ".doCrossover()");
		    }
		    
		    
		  } 
		
 // doMutation

	/**
	 * Executes the operation
	 * @param object An object containing a solution to mutate
	 * @return An object containing the mutated solution
	 * @throws JMException 
	 */
	public Object execute(Object object) throws JMException {
		Solution solution = (Solution) object;

		if (!VALID_TYPES.contains(solution.getType().getClass())) {
			Configuration.logger_.severe("BitFlipMutation.execute: the solution " +
					"is not of the right type. The type should be 'Binary', " +
					"'BinaryReal' or 'Int', but " + solution.getType() + " is obtained");

			Class cls = java.lang.String.class;
			String name = cls.getName();
			throw new JMException("Exception in " + name + ".execute()");
		} // if 

		doMutation(mutationProbability_, solution);
		return solution;
	} // execute
} // BitFlipMutation
