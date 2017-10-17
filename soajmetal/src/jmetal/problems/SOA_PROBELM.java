package jmetal.problems;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.BinarySolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.encodings.solutionType.SOA_SOLUTION_TYPE;
import jmetal.util.JMException;

import java.util.ArrayList;

import Helper.Dread;
import Helper.Service;
import Helper.inputdataSOA;

public class SOA_PROBELM extends Problem {
	public double globalConf = 0;
	public double globalIntegrity = 0;
	public double globalAvailability = 0;
	public double globalCost = 0;
	public double p1 = 0.5; // p1+p2=1
	public double p2 = 0.5;
	public double p3=0.5;// p3 + p4 = 1
	public double p4=.5;
	public double p5=.5;//p5+p6=1
	public double p6=.5;
	
	
	 //public static Dread dread=new Dread();// added 20-2 -2016
public static ArrayList<Service> TestedSC;
public static ArrayList<Service> TestedSC2;

public SOA_PROBELM(String solutionType) {
		//numberOfVariables_ = 4;// edited 6-2-2016 by Wafaa
		numberOfObjectives_ = 4;// edited 6-2-2016 by Wafaa
		numberOfConstraints_ = 0;
		problemName_ = "SOA_PROBELM";

		solutionType_ = new SOA_SOLUTION_TYPE(this);
		if (solutionType.compareTo("BinaryReal") == 0)
			solutionType_ = new BinaryRealSolutionType(this);
		else if (solutionType.compareTo("Real") == 0)
			solutionType_ = new RealSolutionType(this);
		// Added 09-03-2015
		else if (solutionType.compareTo("SOA_SOLUTION_TYPE") == 0)
			solutionType_ = new SOA_SOLUTION_TYPE(this);
		// End Added 09-03-2015

		else {
			System.out.println("Error: solution type " + solutionType + " invalid");
			System.exit(-1);
		}
		// length_ = new int[numberOfVariables_];
		// length_ [0] = numberOfBits ;
		/*
		 * if (solutionType.compareTo("Binary") == 0) solutionType_ = new
		 * SOA_SOLUTION_TYPE(this) ; else { System.out.println(
		 * "SOA: solution type " + solutionType + " invalid") ; System.exit(-1)
		 * ;
		 * 
		 * }
		 */

	}

	@Override
/*evaluate case study 1*/
	/*
	public void evaluate(Solution solution) throws JMException {
		// TODO Auto-generated method stub
	
		for (int i = 0; i < solution.getCompositionService().size(); i++) {
		Dread.CalculateDREAD(solution.getCompositionService().get(i));}
		for (int i = 0; i < solution.getCompositionService().size(); i++) {
	//if (solution.getCompositionService().get(i).getServiceId().contains(",")){
			// edited 6-2-2016
			// Calculate Cost = the Sum of the cost of all individual services
			// aggregated in the composition
			// wafsaa 6-2-2016
			globalCost = (p1 * solution.getCompositionService().get(0).getServiceCost())
					+ p2 * (solution.getCompositionService().get(2).getServiceCost()
							+ solution.getCompositionService().get(3).getServiceCost())
					+ solution.getCompositionService().get(1).getServiceCost();

			//Calculate Confidentiality 6-2-2016
			//
			///System.out.println(" Calculate Confidentiality 6-2-2016");
			//System.out.println();
			//System.out.println(solution.getCompositionService().get(2).getConfidentialitySeverity());
			double temp1=0, temp2=0, temp3=0;
			//System.out.println(" 0 C serverty "+solution.getCompositionService().get(0).getConfidentialitySeverity());
			//temp1=p1*solution.getCompositionService().get(0).getConfidentialitySeverity();

			temp2=p2*(Math.min(solution.getCompositionService().get(2).getConfidentialitySeverity(),
					solution.getCompositionService().get(3).getConfidentialitySeverity()));
			temp3=Math.min(temp2, solution.getCompositionService().get(1).getConfidentialitySeverity());
			globalConf=Math.min(temp1, temp3);
		
			//globalConf=Math.min((p1*solution.getCompositionService().get(0).getConfidentialitySeverity()),
				//Math.min(p2*(Math.min(solution.getCompositionService().get(2).getConfidentialitySeverity(),
					//		solution.getCompositionService().get(3).getConfidentialitySeverity())),
						//	solution.getCompositionService().get(1).getConfidentialitySeverity())) ;
			// Calculate integrity 6-2-2016
			globalIntegrity=Math.min((p1*solution.getCompositionService().get(0).getIntegritySeverity()),
				Math.min(p2*(Math.min(solution.getCompositionService().get(2).getIntegritySeverity(),
							solution.getCompositionService().get(3).getIntegritySeverity())),
							solution.getCompositionService().get(1).getIntegritySeverity())) ;
			//globalIntegrity = Math.min(globalIntegrity, solution.getCompositionService().get(i).getIntegritySeverity());
			// Calculate availability  6-2-2016
			globalAvailability=Math.min((p1*solution.getCompositionService().get(0).getAvailabilitySeverity()),
					Math.min(p2*(Math.min(solution.getCompositionService().get(2).getAvailabilitySeverity(),
								solution.getCompositionService().get(3).getAvailabilitySeverity())),
								solution.getCompositionService().get(1).getAvailabilitySeverity())) ;
			//globalAvailability = Math.min(globalAvailability,solution.getCompositionService().get(i).getAvailabilitySeverity());

			// globalSecurity=Math.min(globalSecurity,
			// solution.getCompositionService().get(i).getServiceSercurity());

			// Calculate ET
			// globalET+=solution.getCompositionService().get(i).getServiceExecutionTime();
	}
		
//		//calculate fitness for security services 
		for (int i = 0; i < solution.getCompositionService().size(); i++) {
	
		if (solution.getCompositionService().get(i).getServiceId().contains("SS")){
		globalConf+=solution.getCompositionService().get(i).getConfidentialitySeverity();
		globalIntegrity+=solution.getCompositionService().get(i).getIntegritySeverity();
		globalAvailability+=solution.getCompositionService().get(i).getAvailabilitySeverity();
		globalCost+=solution.getCompositionService().get(i).getServiceCost();
		}}
	
	}
		// end of Fitness Function

	*/
	//3-3-2016 BP2- Booking 
public void evaluate(Solution solution) throws JMException {
		System.out.println("Sart evaluate 1");
		// TODO Auto-generated method stub
		//removed by wafaa 3-4-2-16 and replaced by calculating Dread when Read services 
		//for (int i = 0; i < solution.getCompositionService().size(); i++) {
			//Dread.CalculateDREAD(solution.getCompositionService().get(i));}
		//System.out.println("Dread all done");
		//for (int i = 0; i < solution.getCompositionService().size(); i++) {
			// edited 6-2-2016
			// Calculate Cost = the Sum of the cost of all individual services
			// aggregated in the composition
			// wafsaa 6-2-2016
		ArrayList<Service> TestedSC=new ArrayList<Service>();
		ArrayList<Service> TestedSC2=new ArrayList<Service>();
		for (int i=0; i<solution.getCompositionService().size();i++)
		{  System.out.println("wafaa take care     i=  "+solution.getCompositionService().get(i).getServiceId() );
		}
		
		for (int i=0; i<solution.getCompositionService().size();i++)
		{ // System.out.println("wafaa take care     i=  "+solution.getCompositionService().get(i).getServiceId() );
			if (solution.getCompositionService().get(i).getServiceFunctionality().equals("Security"))
			TestedSC2.add(solution.getCompositionService().get(i));
			else
				TestedSC.add(solution.getCompositionService().get(i));

		}
		
		
		for (int o=0;o<TestedSC.size();o++)
			System.out.println(TestedSC.get(o).getServiceId()+ " el Tested lama  o=  "+ o); 	
	//	System.out.println(" sixe TestedSC2 "+TestedSC2.size()+ "   TestedSC:  " + TestedSC.size() + "evaluateeeeeeeeeed ");
	//	System.out.println(TestedSC.get(8).getServiceCost()	+"     System.out.println(");
		globalCost = ((p3 * TestedSC.get(0).getServiceCost())
					+ p4 * (TestedSC.get(1).getServiceCost())
							+ TestedSC.get(2).getServiceCost()
							+ TestedSC.get(3).getServiceCost()
							+ TestedSC.get(4).getServiceCost()
							+ TestedSC.get(5).getServiceCost()
							+ TestedSC.get(6).getServiceCost()
							+ TestedSC.get(7).getServiceCost())/
					((1-p5)*( TestedSC.get(8).getServiceCost()+
							TestedSC.get(9).getServiceCost()));

			// Calculate Confidentiality 3-3-2016
			
			globalConf=Math.max(
					
					Math.max((p3*TestedSC.get(0).getConfidentialitySeverity()
					+p4*TestedSC.get(1).getConfidentialitySeverity()),
					Math.max(TestedSC.get(2).getConfidentialitySeverity(),
							Math.max(TestedSC.get(3).getConfidentialitySeverity(),
								
								Math.max(TestedSC.get(4).getConfidentialitySeverity(),
								
								Math.max(TestedSC.get(5).getConfidentialitySeverity()
										,
								Math.max(TestedSC.get(6).getConfidentialitySeverity(),
										TestedSC.get(7).getConfidentialitySeverity())	))))),
				
								Math.max(TestedSC.get(8).getConfidentialitySeverity(), 
										TestedSC.get(9).getConfidentialitySeverity())
								
									);
				
			
			
			
			// Calculate integrity 6-2-2016

			globalIntegrity=Math.max(
					Math.max((p3*TestedSC.get(0).getIntegritySeverity()
					+p4*TestedSC.get(1).getIntegritySeverity()),
					Math.max(TestedSC.get(2).getIntegritySeverity(),
							Math.max(TestedSC.get(3).getIntegritySeverity(),
								Math.max(TestedSC.get(4).getIntegritySeverity(),
								Math.max(TestedSC.get(5).getIntegritySeverity(),
								Math.max(TestedSC.get(6).getIntegritySeverity(),
										TestedSC.get(7).getIntegritySeverity())	))))),
								Math.max(TestedSC.get(8).getIntegritySeverity(), 
										TestedSC.get(9).getIntegritySeverity()));
				
			
			/* Calculate availability  3-3-2016*/
			globalAvailability=Math.max(
					Math.max((p3*TestedSC.get(0).getAvailabilitySeverity()
					+p4*TestedSC.get(1).getAvailabilitySeverity()),
					Math.max(TestedSC.get(2).getAvailabilitySeverity(),
							Math.max(TestedSC.get(3).getAvailabilitySeverity(),
								Math.max(TestedSC.get(4).getAvailabilitySeverity(),
								Math.max(TestedSC.get(5).getAvailabilitySeverity(),
								Math.max(TestedSC.get(6).getAvailabilitySeverity(),
										TestedSC.get(7).getAvailabilitySeverity())	))))),
								Math.max(TestedSC.get(8).getAvailabilitySeverity(), 
										TestedSC.get(9).getAvailabilitySeverity()));
			
								
			
	// add  security servicessss  
		
	
				solution.setObjective(0, globalConf);
				solution.setObjective(1, globalIntegrity);
				solution.setObjective(2, globalAvailability);
				solution.setObjective(3, globalCost);

// end of Fitness Function
		}
		//}
	}
