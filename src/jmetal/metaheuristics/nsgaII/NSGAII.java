//  NSGAII.java
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

package jmetal.metaheuristics.nsgaII;

import java.util.ArrayList;
import java.util.Random;

import jmetal.core.*;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Distance;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;
import jmetal.util.Ranking;
import jmetal.util.comparators.CrowdingComparator;
import Helper.Dread;
import Helper.Service;
import Helper.ServiceClass;;
/** 
 *  Implementation of NSGA-II.
 *  This implementation of NSGA-II makes use of a QualityIndicator object
 *  to obtained the convergence speed of the algorithm. This version is used
 *  in the paper:
 *     A.J. Nebro, J.J. Durillo, C.A. Coello Coello, F. Luna, E. Alba 
 *     "A Study of Convergence Speed in Multi-Objective Metaheuristics." 
 *     To be presented in: PPSN'08. Dortmund. September 2008.
 */

public class NSGAII extends Algorithm {
//	public static ArrayList<ServiceClass> classes=Chromo.ClassPool;	
	static Random random = new Random();
  /**
   * Constructor
   * @param problem Problem to solve
   */
  public NSGAII(Problem problem) {
    super (problem) ;
  } // NSGAII

  /**   
   * Runs the NSGA-II algorithm.
   * @return a <code>SolutionSet</code> that is a set of non dominated solutions
   * as a result of the algorithm execution
   * @throws JMException 
   */
  public SolutionSet execute() throws JMException, ClassNotFoundException {
    int populationSize;
    int maxEvaluations;
    int evaluations;
	ArrayList<Service> BasicChmomsome ;

    ArrayList<Service> Spool;// added by wafaa 1-3-2015
    ArrayList<ServiceClass> classes; //added by wafaa 8-2-2016
    QualityIndicator indicators; // QualityIndicator object
    int requiredEvaluations; // Use in the example of use of the
    // indicators object (see below)

    SolutionSet population;
    SolutionSet offspringPopulation;
    SolutionSet union;

    Operator mutationOperator;
    Operator crossoverOperator;
    Operator selectionOperator;

    Distance distance = new Distance();

    //Read the parameters
    populationSize = ((Integer) getInputParameter("populationSize")).intValue();
    maxEvaluations = ((Integer) getInputParameter("maxEvaluations")).intValue();
    indicators = (QualityIndicator) getInputParameter("indicators");
    BasicChmomsome=(ArrayList)getInputParameter("BasicSolution");

    Spool = (ArrayList<Service>) getInputParameter("ServicePool");// added by wafaa 1-3-2015
    System.out.println("read services parameters are readed");
    classes=(ArrayList<ServiceClass>) getInputParameter("ClassPool");// added by wafaa 8-2-2016
    System.out.println("read classes ");
    //Initialize the variables

//popolation= create basic popolatiion /////
    population = new SolutionSet(populationSize);// ma bt3mal eshe hon 
    evaluations = 0;

    requiredEvaluations = 0;
    //System.out.println("creat pop set");
    //Read the operators
   mutationOperator = operators_.get("mutation");
    crossoverOperator = operators_.get("crossover");
    selectionOperator = operators_.get("selection");
//System.out.println("Read operatores set      ttttttt");
    // Create the initial solutionSet
    Solution newSolution;
    // create population 
    for (int i = 0; i <populationSize; i++) {
      newSolution = new Solution(problem_);
     // System.out.println("Step x " +Spool.size());
     // System.out.println("Step y " );
    
      newSolution.setCompositionService(getMyServices(Spool, classes));
   
      
      
      //assssssssssssssskkkkkkkkk  
    problem_.evaluate(newSolution);
    //problem_.evaluateConstraints(newSolution);
   evaluations++;
     
      population.add(newSolution);
    //  System.out.println("add pop"+newSolution.getCompositionService().get(5).getServiceId() );
    } //for   
    
    for (int i = 0; i < populationSize; i++) {
//System.out.println(populationSize   +" popppp");
    for(int x=0;x<population.get(i).getCompositionService().size();x++){ // -1 added 8-2
  //  	System.out.println("Print pop " );
    //	System.out.println("Solution"+i+" "+"Service "+x+ ""+population.get(i).getCompositionService().get(x).getServiceId());
    	
    	
    	
    }
    	
    	
    }

    // Generations 
   while (evaluations < maxEvaluations) {
	   System.out.println("start eval " );
      // Create the offSpring solutionSet      
      offspringPopulation = new SolutionSet(populationSize);
      Solution[] parents = new Solution[2];
      //if (populationSize!=100)// 7allllllllllllllll 
      for (int i = 0; i < (populationSize / 2); i++) {
        if (evaluations < maxEvaluations) {
          //obtain parents
          parents[0] = (Solution) selectionOperator.execute(population);
          parents[1] = (Solution) selectionOperator.execute(population);
          Solution[] offSpring = (Solution[]) crossoverOperator.execute(parents);
        
          System.out.println(offSpring[0].getCompositionService().size()+"       size 1  ");

          System.out.println(offSpring[1].getCompositionService().size()+"       size 2  ");
          
          System.out.println("MUTEEEEEEEEEE");
          
       mutationOperator.execute(offSpring[0]);
       mutationOperator.execute(offSpring[1]);
       for (int e=0;e<offSpring[0].getCompositionService().size();e++)
    	   System.out.println("  1rst offsbring After mutation"+  offSpring[0].getCompositionService().get(e).getServiceId());
       for (int e=0;e<offSpring[1].getCompositionService().size();e++)
    	   System.out.println("2nd offsbring After mutation"+  offSpring[1].getCompositionService().get(e).getServiceId());
       System.out.println("finish cross over  " );
         problem_.evaluate(offSpring[0]);
        // problem_.evaluateConstraints(offSpring[0]);
        problem_.evaluate(offSpring[1]);
        //problem_.evaluateConstraints(offSpring[1]);
          System.out.println("end evaluate" );
          offspringPopulation.add(offSpring[0]);
          offspringPopulation.add(offSpring[1]);
          evaluations += 2;
          
          System.out.print("2 pop added ");
        } // if                            
      } // for

      // Create the solutionSet union of solutionSet and offSpring
      union = ((SolutionSet) population).union(offspringPopulation);
/////////////////////// problem start her 
      // Ranking the union
      Ranking ranking = new Ranking(union);
     // System.out.println("Rank  " );
    
      
      int remain = populationSize;
      int index = 0;
      SolutionSet front = null;
      population.clear();
      System.out.println("Remain  "+ remain );
      // Obtain the next front
      front = ranking.getSubfront(index);
      System.out.println("front  "+ front.size() );
   // problem start her 
      while ((remain > 0) && (remain >= front.size())) { 
        //Assign crowding distance to individuals
        distance.crowdingDistanceAssignment(front, problem_.getNumberOfObjectives());
        //Add the individuals of this front
        for (int k = 0; k < front.size(); k++) {
          //  System.out.println("s  " );
        	population.add(front.get(k));
        } // for

        //Decrement remain
        remain = remain - front.size();
System.out.println(remain);
System.out.println(front.size());
        //Obtain the next front
        index++;
        if (remain > 0) {
         front = ranking.getSubfront(index);
        } // if        
        
      } // while

      // Remain is less than front(index).size, insert only the best one
      if (remain > 0) {  // front contains individuals to insert                        
        distance.crowdingDistanceAssignment(front, problem_.getNumberOfObjectives());
        front.sort(new CrowdingComparator());
        for (int k = 0; k < remain; k++) {
          population.add(front.get(k));
          System.out.println("line 204 " );
        } // for

       remain = 0;
      } // if                               

      // This piece of code shows how to use the indicator object into the code
      // of NSGA-II. In particular, it finds the number of evaluations required
      // by the algorithm to obtain a Pareto front with a hypervolume higher
      // than the hypervolume of the true Pareto front.
      if ((indicators != null) &&
          (requiredEvaluations == 0)) {
        double HV = indicators.getHypervolume(population);
        if (HV >= (0.98 * indicators.getTrueParetoFrontHypervolume())) {
          requiredEvaluations = evaluations;
        } // if
      } // if
    } // while

    // Return as output parameter the required evaluations
  //  setOutputParameter("evaluations", requiredEvaluations);

    // Return the first non-dominated front
   Ranking ranking = new Ranking(population);
   ranking.getSubfront(0).printFeasibleFUN("FUN_NSGAII") ;

   return ranking.getSubfront(0);
 
  } // execute
  
  
  
  
  
  
  
  
  
  private static ArrayList<Service> fillarray(ServiceClass class1, ArrayList<Service> AllServicePool) {
	  ArrayList<Service> SolutionTempClass =new ArrayList<Service>();
	  
	  for (int i=0;i<AllServicePool.size();i++){
		if (AllServicePool.get(i).getSeviceClass().equals(class1.getClassid()))
			SolutionTempClass.add(AllServicePool.get(i));
		//System.out.println("Class temp  fill " );
		}
		return SolutionTempClass;
	}

  ArrayList<Service> getMyServices( ArrayList<Service> AllServicePool, ArrayList<ServiceClass> classes){
	
	  ArrayList<Service> SoutionServices = new ArrayList<Service> ();
	  ArrayList<Service>TempClass; 
	  //ServiceClass class1
	 System.out.println("get my classes "+ classes.size());

	 System.out.println("get my services "+ AllServicePool.size());
	    	for (int i=0; i< classes.size(); i++){
	    		 TempClass =new ArrayList<Service>();  

	    		// System.out.println("Step 1");
	    		TempClass=fillarray(classes.get(i), AllServicePool);
	    		//System.out.println("call fill array " );
		//BasicChmomsome.add();
	//	Solution 	BasicChomosome = new Solution();
		
		
		
		//Service service;

		//BasicChmomsome.add(TempClass.get(j));
		
	  
	 // System.out.println("Step 1");
	  
	  //ArrayList<Service> SoutionServicesClassA=new ArrayList<Service>();
	  //ArrayList<Service> SoutionServicesClassB=new ArrayList<Service>();
	  //ArrayList<Service> SoutionServicesClassC=new ArrayList<Service>();
	  //ArrayList<Service> SoutionServicesClassD=new ArrayList<Service>();
	  
	/*  for(int s=0;s<AllServicePool.size();s++){
		  
		  System.out.println("Step 2");
		  
		  
		  System.out.println( AllServicePool.get(s).getSeviceClass());
		if(AllServicePool.get(s).getSeviceClass().equals("A")){
			SoutionServicesClassA.add(AllServicePool.get(s));
			
		}
		
		if(AllServicePool.get(s).getSeviceClass().equals("B")){
			SoutionServicesClassB.add(AllServicePool.get(s));
			
		}
		
		if(AllServicePool.get(s).getSeviceClass().equals("C")){
			SoutionServicesClassC.add(AllServicePool.get(s));
			
		}
		
		if(AllServicePool.get(s).getSeviceClass().equals("D")){
			SoutionServicesClassD.add(AllServicePool.get(s));
			
		}
		
		
		  
	  }
*/	  
	  
	  //System.out.println("SoutionServicesClassA.size()" +SoutionServicesClassA.size());
	int RandomVal=random.nextInt(TempClass.size()-1);
//	System.out.println("RandomVal"+RandomVal);
	SoutionServices.add(TempClass.get(RandomVal));
	//System.out.println("add from temp class " );
	// System.out.println(SoutionServicesClassB.size());
	
 //RandomVal= random.nextInt(SoutionServicesClassB.size()-1);
	//System.out.println("RandomVal"+RandomVal);
	//SoutionServices.add(SoutionServicesClassB.get(RandomVal));
	 
	 //System.out.println(SoutionServicesClassC.size());
	 //RandomVal= random.nextInt(SoutionServicesClassC.size()-1);
		
		//SoutionServices.add(SoutionServicesClassC.get(RandomVal));
		//System.out.println("RandomVal"+RandomVal);
		/* RandomVal= random.nextInt(SoutionServicesClassD.size());
			
			SoutionServices.add(SoutionServicesClassD.get(RandomVal));*/
	 
	 // return SoutionServices;
	
	    	}
	    	System.out.println(SoutionServices.size()+ "SoutionServices    SIZE IN NSGAII ");
  return SoutionServices;
  }
  
} // NSGA-II
