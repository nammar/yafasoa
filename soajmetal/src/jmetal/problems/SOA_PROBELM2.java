package jmetal.problems;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.BinarySolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.encodings.solutionType.SOA_SOLUTION_TYPE;
import jmetal.util.JMException;
import Helper.Service;
import Helper.inputdataSOA;

public class SOA_PROBELM2 extends Problem {
	public double globalConf = 0;
	public double globalIntegrity = 0;
	public double globalAvailability = 0;
	public double globalCost = 0;
	public double p1 = 0.5;
	public double p2 = 0.5;

	public SOA_PROBELM2(String solutionType) {
		numberOfVariables_ = 4;// edited 6-2-2016 by Wafaa
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

	public void evaluate(Solution solution) throws JMException {
		// TODO Auto-generated method stub
		for (int i = 0; i < solution.getCompositionService().size(); i++) {
			// edited 6-2-2016
			// Calculate Cost = the Sum of the cost of all individual services
			// aggregated in the composition
			// wafsaa 6-2-2016
			globalCost = (p1 * solution.getCompositionService().get(0).getServiceCost())
					+ p2 * (solution.getCompositionService().get(2).getServiceCost()
							+ solution.getCompositionService().get(3).getServiceCost())
					+ solution.getCompositionService().get(1).getServiceCost();

			/* Calculate Confidentiality 6-2-2016*/
			globalConf=Math.min((p1*solution.getCompositionService().get(0).getConfidentialitySeverity()),
				Math.min(p2*(Math.min(solution.getCompositionService().get(2).getConfidentialitySeverity(),
							solution.getCompositionService().get(3).getConfidentialitySeverity())),
							solution.getCompositionService().get(1).getConfidentialitySeverity())) ;
			/* Calculate integrity 6-2-2016*/
			globalIntegrity=Math.min((p1*solution.getCompositionService().get(0).getIntegritySeverity()),
				Math.min(p2*(Math.min(solution.getCompositionService().get(2).getIntegritySeverity(),
							solution.getCompositionService().get(3).getIntegritySeverity())),
							solution.getCompositionService().get(1).getIntegritySeverity())) ;
			//globalIntegrity = Math.min(globalIntegrity, solution.getCompositionService().get(i).getIntegritySeverity());
			/* Calculate availability  6-2-2016*/
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

		// end of Fitness Function

	}
/**/

}
