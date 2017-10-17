//  SinglePointCrossover.java
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

package jmetal.operators.crossover;

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
import java.util.Random;

import Helper.Service;

/**
 * This class allows to apply a Single Point crossover operator using two parent
 * solutions.
 */
public class SOACrossover extends Crossover {
  /**
   * Valid solution types to apply this operator 
   */
	 protected static Random random = new Random();
	private static final List VALID_TYPES = Arrays.asList(SOA_SOLUTION_TYPE.class,
		  SOA_SOLUTION_TYPE.class,
		  SOA_SOLUTION_TYPE.class) ;

  private Double crossoverProbability_ = null;
  private int corssoverPoint_=0; 
  private int Solutionlength_=0; 

  /**
   * Constructor
   * Creates a new instance of the single point crossover operator
   */
  public SOACrossover(HashMap<String, Object> parameters) {
  	super(parameters) ;
  	if (parameters.get("probability") != null)
  		crossoverProbability_ = (Double) parameters.get("probability") ; 
  	
  	crossoverProbability_ = (Double) parameters.get("probability") ;
  System.out.println("cross over Prob    "+crossoverProbability_);
  	//corssoverPoint_= parameters.get("corssoverPoint") ;
  	
  Solutionlength_=(Integer)parameters.get("Solutionlength") ;
  	// System.out.println("cross over length    "+Solutionlength_);
  } // SinglePointCrossover


  /**
   * Constructor
   * Creates a new instance of the single point crossover operator
   */
  //public SinglePointCrossover(Properties properties) {
  //    this();
  //} // SinglePointCrossover

  /**
   * Perform the crossover operation.
   * @param probability Crossover probability
   * @param parent1 The first parent
   * @param parent2 The second parent   
   * @return An array containig the two offsprings
   * @throws JMException
   */
  public Solution[] doCrossover(double probability,
          
		  
		  
		  Solution parent1,
          Solution parent2) throws JMException {
	  System.out.println("parent1  size"+ parent1.getCompositionService().size()+"         parent2  size"+ parent2.getCompositionService().size() );
	  
	  
	  
	  System.out.println("parent1.getCompositionService().size()  "+ parent1.getCompositionService().size());
	  corssoverPoint_= random.nextInt(parent1.getCompositionService().size());//(Math.abs(PseudoRandom.randInt()%parent1.getCompositionService().size()))+1;  
    System.out.println("corssoverPoint_  **************   "+corssoverPoint_);
	  Solution[] offSpring = new Solution[2];
    offSpring[0] = new Solution(parent1);
    offSpring[1] = new Solution(parent2);
    ArrayList<Service> ServiceCompostionOne = new  ArrayList<Service>(); // new array list of service 
	  ArrayList<Service> ServiceCompostionTwo = new  ArrayList<Service>();
    try {
      if (PseudoRandom.randDouble() < probability) {
    	  System.out.print("JUST TO TEST WHERE PROBLEM"+parent1.getCompositionService().get(1).getServiceId());
    	  System.out.println();
    	  System.out.println();
    	  ServiceCompostionOne=new   ArrayList<Service>();
    	  for (int part1=0;part1<corssoverPoint_;part1++){
    		 // System.out.println(part1+"    =");
    	//	  System.out.println( (parent1.getCompositionService().get(part1).getServiceId()));
    		  //parent1.getCompositionService().get(part1).getServiceId()
    	    	 
    	 //ServiceCompostionOne.set(part1, parent1.getCompositionService().get(part1));
    	 ServiceCompostionOne.add(parent1.getCompositionService().get(part1));
    	 //set(part1, parent1.getCompositionService().get(part1));
     	 
    	  }
   
    	  
    	  for (int part2=corssoverPoint_;part2<Solutionlength_;part2++){
    	 // for (int part2=corssoverPoint_;part2<parent1.getCompositionService().size();part2++){ 
    	  //ServiceCompostionOne.set(part2, parent2.getCompositionService().get(part2));
    		
    	  ServiceCompostionOne.add(parent2.getCompositionService().get(part2));
    	    	 
    		  System.out.println( (parent1.getCompositionService().get(part2).getServiceId()));
    	  }
    	  
    
    	  
    	  for (int part1=0;part1<corssoverPoint_;part1++){
    		//  ServiceCompostionTwo.set(part1, parent2.getCompositionService().get(part1));
    		  ServiceCompostionTwo.add(parent1.getCompositionService().get(part1));
    	    	 
    	  }
   
    	  
    	  for (int part2=corssoverPoint_;part2<Solutionlength_;part2++)
    		//  for (int part2=corssoverPoint_;part2<parent1.getCompositionService().size();part2++)
    	  {
    		  
    		// ServiceCompostionTwo.set(part2, parent1.getCompositionService().get(part2));
    		 ServiceCompostionTwo.add(parent2.getCompositionService().get(part2));
        	 
    	  }
    	  
    	  // added 9-2-2016
    	  System.out.println(ServiceCompostionOne.size() + "  drgn " );

    	    System.out.println(ServiceCompostionTwo.size()+ "   drgn");

    	  offSpring[0].setCompositionService(ServiceCompostionOne);
    	    offSpring[1].setCompositionService(ServiceCompostionTwo); 
// System.out.println( offSpring[0].getCompositionService().size()+ "+   SIZE 1");
//System.out.println( offSpring[1].getCompositionService().size()+ "+   SIZE 2");
       
      }
   
    } catch (ClassCastException e1) {
      Configuration.logger_.severe("SinglePointCrossover.doCrossover: Cannot perfom " +
              "SinglePointCrossover");
      Class cls = java.lang.String.class;
      String name = cls.getName();
      throw new JMException("Exception in " + name + ".doCrossover()");
    }
       //offSpring[0].setCompositionService(ServiceCompostionOne);
  //  offSpring[1].setCompositionService(ServiceCompostionTwo);
     return offSpring;
  } // doCrossover

  /**
   * Executes the operation
   * @param object An object containing an array of two solutions
   * @return An object containing an array with the offSprings
   * @throws JMException
   */
  public Object execute(Object object) throws JMException {
    Solution[] parents = (Solution[]) object;

    if (!(VALID_TYPES.contains(parents[0].getType().getClass())  &&
        VALID_TYPES.contains(parents[1].getType().getClass())) ) {

      Configuration.logger_.severe("SinglePointCrossover.execute: the solutions " +
              "are not of the right type. The type should be 'Binary' or 'Int', but " +
              parents[0].getType() + " and " +
              parents[1].getType() + " are obtained");

      Class cls = java.lang.String.class;
      String name = cls.getName();
      throw new JMException("Exception in " + name + ".execute()");
    } // if

    if (parents.length < 2) {
      Configuration.logger_.severe("SinglePointCrossover.execute: operator " +
              "needs two parents");
      Class cls = java.lang.String.class;
      String name = cls.getName();
      throw new JMException("Exception in " + name + ".execute()");
    } 
    
    Solution[] offSpring;
    offSpring = doCrossover(crossoverProbability_,
            parents[0],
            parents[1]);

    //-> Update the offSpring solutions
    for (int i = 0; i < offSpring.length; i++) {
      offSpring[i].setCrowdingDistance(0.0);
      offSpring[i].setRank(0);
    }
    return offSpring;
  } // execute
} // SinglePointCrossover
