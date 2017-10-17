package jmetal.encodings.solutionType;

import Helper.Service;
import jmetal.core.Problem;
import jmetal.core.SolutionType;
import jmetal.core.Variable;
import jmetal.encodings.variable.Real;

public class SOA_SOLUTION_TYPE extends SolutionType {

	public SOA_SOLUTION_TYPE(Problem problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Variable[] createVariables() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		
	//added by wafaa 1-3-15	
		
		//Variable[] variables = new Variable[problem_.getNumberOfVariables()];

	//	for (int var = 0; var < problem_.getNumberOfVariables(); var++)
		//	variables[var] = new Service(problem_.getLowerLimit(var),
			//		problem_.getUpperLimit(var)); 

//		return /variables ;

		//end add 
		
		
		return null;
	}

}
