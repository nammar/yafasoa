//  NSGAII_main.java
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

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.SolutionSet;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.problems.ProblemFactory;
import jmetal.problems.SOA_PROBELM;
import jmetal.problems.ZDT.ZDT3;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import Helper.Dread;
import Helper.Service;
import Helper.ServiceClass;
import Helper.inputdataSOA;
import Helper.SecurityService;
import Helper.ReadSecurityService;
/** 
 * Class to configure and execute the NSGA-II algorithm.  
 *     
 * Besides the classic NSGA-II, a steady-state version (ssNSGAII) is also
 * included (See: J.J. Durillo, A.J. Nebro, F. Luna and E. Alba 
 *                  "On the Effect of the Steady-State Selection Scheme in 
 *                  Multi-Objective Genetic Algorithms"
 *                  5th International Conference, EMO 2009, pp: 183-197. 
 *                  April 2009)
 */ 

public class NSGAII_main {
	//public static int NoOFCLASS=5;
	public static Logger      logger_ ;      // Logger object
    public static inputdataSOA Chromo=new inputdataSOA();// define chromoooo wafaa 1-3-15 
    public static Dread dread=new Dread();// added wafaa 6-2-2016
	public static ArrayList<Service> BasicChmomsome =new ArrayList<Service>();
	//ReadServiceClass();
	public static ArrayList<ServiceClass> classes=Chromo.ClassPool;
	public static ArrayList<Service> AllServicePool=Chromo.ServicePool;
	public static	ArrayList<Service> TempClass =new ArrayList<Service>();
	//public static ArrayList<Service> Class2 =new ArrayList<Service>();
	//public static ArrayList<Service> Class3 =new ArrayList<Service>();
	//public static ArrayList<Service> Class4 =new ArrayList<Service>();// define chromoooo wafaa 1-3-15 
  public static FileHandler fileHandler_ ; // FileHandler object
  
  //public static ArrayList<Service> AllServicePool;

  /**
   * @param args Command line arguments.
   * @throws JMException 
   * @throws IOException 
   * @throws SecurityException 
   * Usage: three options
   *      - jmetal.metaheuristics.nsgaII.NSGAII_main
   *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName
   *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName paretoFrontFile
   */
  public static void main(String [] args) throws 
                                  JMException, 
                                  SecurityException, 
                                  IOException, 
                                  ClassNotFoundException {
    Problem   problem   ; // The problem to solve
    Algorithm algorithm ; // The algorithm to use
    Operator  crossover ; // Crossover operator
    Operator  mutation  ; // Mutation operator
    Operator  selection ; // Selection operator
   
    HashMap  parameters ; // Operator parameters
    
    QualityIndicator indicators ; // Object to get quality indicators

    // Logger object and file to store log messages
    logger_      = Configuration.logger_ ;
    fileHandler_ = new FileHandler("NSGAII_main.log"); 
    logger_.addHandler(fileHandler_) ;
        
    indicators = null ;
    if (args.length == 1) {
      Object [] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0],params);
    } // if
    else if (args.length == 2) {
      Object [] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0],params);
      indicators = new QualityIndicator(problem, args[1]) ;
    } // if
    else { // Default problem
      //problem = new Kursawe("Real", 3);
      //problem = new Kursawe("BinaryReal", 3);
      //problem = new Water("Real");
     // problem = new ZDT3("ArrayReal", 30);
    	// Added 09-03-2015	
//    	Chromo.ReadServices("C:\\Users\\ahmad.zeidan\\Downloads\\input.txt");
    	Chromo.ReadServices();// edited 28 -1-2016  void 
    	Chromo.ReadServiceClass();
    	
    	//dread.CalculateDREAD(STlist, stest2);
   
	// for(int i=0; i<AllServicePool.size();i++){
//System.out.println(AllServicePool.size());

/*System.out.println(i);
		System.out.println(AllServicePool.get(i).getSeviceId()+"   " +
				AllServicePool.get(i).getSeviceClass()+"  "
				+ AllServicePool.get(i).getServiceFunctionality()+
				"  "+AllServicePool.get(i).getSeviceName()+"  "
				+AllServicePool.get(i).getServiceCost()+
				" "+AllServicePool.get(i).getServiceSercurity()+
				"  "+AllServicePool.get(i).getServiceExecutionTime());*/
	 //}
   
	//END  Added 09-03-2015 
    	// System.out.print("2");
    	problem=new SOA_PROBELM("SOA_SOLUTION_TYPE");// added by wafaa 22-2-15//////t3deeeeel 
        //System.out.print("1");
  //problem = new ConstrEx("Real");
      //problem = new DTLZ1("Real");
      //problem = new OKA2("Real") ;
    // else
    	
    	//aaaaaaaassssssssssssssskkkkkkkk
    			createBasic(); 
     algorithm = new NSGAII(problem);
    //algorithm = new ssNSGAII(problem);

    // Algorithm parameters
    algorithm.setInputParameter("populationSize",100);
     algorithm.setInputParameter("maxEvaluations",25000);
    algorithm.setInputParameter("ServicePool", AllServicePool);
    algorithm.setInputParameter("ClassPool", classes);
    algorithm.setInputParameter("BasicSolution",BasicChmomsome);
    // Mutation and Crossover for Real codification 
    parameters = new HashMap() ;
    parameters.put("probability", 0.9) ;
    parameters.put("distributionIndex", 20.0) ;
    parameters.put("Solutionlength", 9) ;  // added by wafaa 19-2-2016 
    //  need to read cromoshome size must be change each BP
    crossover = CrossoverFactory.getCrossoverOperator("SOACrossover", parameters);   // edited by wafaa 22-2-15           
    //System.out.println("cross over done    -0-0-0-0 ");
    // start mutation input 
    parameters = new HashMap() ;

    //parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
    parameters.put("probability", 0.9) ;
  
    parameters.put("distributionIndex", 20.0) ;
    mutation = MutationFactory.getMutationOperator("SOA_BitFlipMutation", parameters);                    // edited by wafaa 22-2-15 
    //System.out.print("Do mutation");
    // Selection Operator 
    parameters = null ;
     selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;                           

    // Add the operators to the algorithm
    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("mutation",mutation);
    System.out.println("add mutation operator");
    algorithm.addOperator("selection",selection);

    // Add the indicator object to the algorithm
    algorithm.setInputParameter("indicators", indicators) ;
    
    // Execute the Algorithm
    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    ///////
    long estimatedTime = System.currentTimeMillis() - initTime;
    System.out.println("before write");
    // Result messages 
    logger_.info("Total execution time: "+estimatedTime + "ms");
    logger_.info("Variables values have been writen to file VAR");
    population.printVariablesToFile("VAR");    
     logger_.info("Objectives values have been writen to file FUN");
   
     population.printObjectivesToFile("FUN");
  System.out.println("wafaa");
     if (indicators != null) {
     logger_.info("Quality indicators") ;
      logger_.info("Hypervolume: " + indicators.getHypervolume(population)) ;
      logger_.info("GD         : " + indicators.getGD(population)) ;
     logger_.info("IGD        : " + indicators.getIGD(population)) ;
      logger_.info("Spread     : " + indicators.getSpread(population)) ;
     logger_.info("Epsilon    : " + indicators.getEpsilon(population)) ;  
     
       int evaluations = ((Integer)algorithm.getOutputParameter("evaluations")).intValue();
     logger_.info("Speed      : " + evaluations + " evaluations") ;      
     } // if
    }
  } //main

private static void createBasic() {
	// TODO Auto-generated method stub
//	Toclasses(); //removed by wafaa 6-2-2016
	BasicChmomsome=new ArrayList<Service>();
	int j=0;
	//System.out.println(classes.size()+"Classes");
	for (int i=0; i<= classes.size()-1; i++){
		fillarray(classes.get(i));
		//j= PseudoRandom.randInt();
		
		/////////BasicChmomsome.add();
		//Solution 	BasicChomosome = new Solution();
		j= PseudoRandom.randInt()%(classes.size());
		//j=j %(classes.size());
	//	j= PseudoRandom.randInt()%(classes.size());

		j=Math.abs( PseudoRandom.randInt()%(TempClass.size()));

		
		//Service service;
		System.out.println(TempClass.size()+"   TempClass"+ "j  "+j);

		BasicChmomsome.add(TempClass.get(j));
		TempClass =new ArrayList<Service>();
		//j= PseudoRandom.randInt();
		//j=j %NoOFCLASS;
		//BasicChmomsome.add(Class2.get(j));
		
		//j= PseudoRandom.randInt();
		//j=j %NoOFCLASS;
		//BasicChmomsome.add(Class3.get(j));
		//j= PseudoRandom.randInt();
		//j=j %NoOFCLASS;
		//BasicChmomsome.add(Class4.get(j));
	}
	

	
}
private static void fillarray(ServiceClass class1) {
	// TODO Auto-generated method stub
	for (int i=0;i<AllServicePool.size();i++){
	if (AllServicePool.get(i).getSeviceClass().equals(class1.getClassid()))
		{TempClass.add(AllServicePool.get(i));
	//Dread.CalculateDREAD(AllServicePool.get(i)); // calculate 
	//		System.out.println("   ******************printing C serv "+AllServicePool.get(i).getConfidentialitySeverity());
		
		}
		}
}

/*removed by wafaa 6-2-2016*/
/*private static void Toclasses() {
	// TODO Auto-generated method stub
	int j=PseudoRandom.randInt();
	//for (int i=0; i< classes.size(); i++){
		ArrayList<ArrayList> s = new ArrayList();
	for (int c=0; c < classes.size();c++)

	for (int i=0; i< AllServicePool.size(); i++){
	if (AllServicePool.get(i).getSeviceClass().equals(classes.get(c)))
			//Class1.add(Chromo.ServicePool.get(i));
	
		else
	if (Chromo.ServicePool.get(i).getSeviceClass().equals("B"))
		Class2.add(Chromo.ServicePool.get(i));
	else
	if (Chromo.ServicePool.get(i).getSeviceClass().equals("C"))
		Class3.add(Chromo.ServicePool.get(i));
	else
		if (Chromo.ServicePool.get(i).getSeviceClass().equals("D"))
			Class4.add(Chromo.ServicePool.get(i));
	}
//	}
			 
			//Solution 	BasicChomosome = new Solution();
	
	

	
/*///}

}  // NSGAII_main
