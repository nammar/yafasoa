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
import java.util.Random;

import Helper.Service;
import Helper.inputdataSOA;
import jmetal.core.Solution;
import jmetal.core.Solution;
import Helper.ReadSecurityService;// added by wafaa 28-2-2016 
import Helper.SecurityService;
/**
 * This class implements a bit flip mutation operator.
 * NOTE: the operator is applied to binary or integer solutions, considering the
 * whole solution as a single encodings.variable.
 */
public class SOA_BitFlipMutation extends Mutation {
  /**
	 * 
	 */
	ArrayList <SecurityService> ssp;
	//private static final long serialVersionUID = 1L;
/**
   * Valid solution types to apply this operator 
   */

	//private static final List VALID_TYPES = Arrays.asList(BinarySolutionType.class,
      //BinaryRealSolutionType.class,
      //IntSolutionType.class) ;
	 protected static Random random = new Random();
	private static final List VALID_TYPES = Arrays.asList(SOA_SOLUTION_TYPE.class,
			  SOA_SOLUTION_TYPE.class,
			  SOA_SOLUTION_TYPE.class) ;
public static inputdataSOA z=new inputdataSOA();
public static ReadSecurityService SS=new ReadSecurityService();// added by wafaa 28-2-2016 

//public ArrayList<SecurityService> SSList=new ArrayList<SecurityService>();// edited by wafaa 28-2-2016
  
private Double mutationProbability_ = null ;
  
	/**
	 * Constructor
	 * Creates a new instance of the Bit Flip mutation operator
	 */
	public SOA_BitFlipMutation(HashMap<String, Object> parameters) {
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
		//for (int i=0; i<offsbring.getCompositionService().size();i++)
		ssp=new ArrayList <SecurityService>();// array list of security services from same SP 
		System.out.println(offsbring.getCompositionService().size()+"  7ajm el CS");
		System.out.println("Mutation 1 ");
		    try {
		    SS.ReadSService();
		    System.out.println(SS.SServicePool.size()+ "SSS SIZE #33333333333");
		    
		  //  System.out.println("Read ssssssssss Security Servicess  ");
			//System.out.println(  offsbring.getCompositionService().size()+ "      WAFAAAAAA ");

		   // int chrmosomesize= offsbring.getCompositionService().size();
	    	 // System.out.println( offsbring.getCompositionService().size()+  "sizzzzzzzze");
  
		  //int random = PseudoRandom.randInt()%chrmosomesize;
		 
		    int mutationpoint =random.nextInt(offsbring.getCompositionService().size());
		   // int mutationpoint =3;
		    System.out.println("mutationpoint   "+ mutationpoint);

		     if (PseudoRandom.randDouble() < probability) 
	    	  {
		 String SP=offsbring.getCompositionService().get(mutationpoint).getServiceProvide();
		System.out.println("SP:  "+ SP);
			
			
			 /*add al SS services from Same provider to the ssp*/
			 for (int s=0 ; s<SS.SServicePool.size();s++){
				// System.out.println(ssp.get(s).getSServiceId()  ); 
				 if (SS.SServicePool.get(s).getServiceProvide().equals(SP))
				 { ssp.add(SS.SServicePool.get(s));
				 
				 //System.out.println("ADDED      "+SS.SServicePool.get(s).getSServiceId());
				 }
			 	}
			 System.out.println("t3bayaaaaa"  + "Size " +ssp.size());
			 for (int k=0; k<ssp.size();k++)
			 {
			 System.out.println(ssp.get(k).getSServiceId());
				 
			 }
			// System.out.println(ssp.size()+"SSP SIZE))))))))))");
		int random2=random.nextInt(ssp.size());//Math.abs((PseudoRandom.randInt())%ssp.size());
		 Service addedSS= convert(ssp.get(random2));
		 //System.out.println(addedSS.getServiceId()+  "  selected SS"+ addedSS.getServiceCost()+ addedSS.getServiceFunctionality()+addedSS.getSeviceClass());
		 ArrayList<Service> TempSC = new ArrayList<Service>();
		 for (int d=0;d<=mutationpoint;d++){
			 TempSC.add(offsbring.getCompositionService().get(d));
		 }
		 for (int w=0;w<offsbring.getCompositionService().size();w++)
			 System.out.println("ALLL ASLIIII   "+offsbring.getCompositionService().get(w).getServiceId());
		 

		 
		 TempSC.add(addedSS);
		
		 for (int m=mutationpoint+1; m<offsbring.getCompositionService().size();m++){
			 TempSC.add(offsbring.getCompositionService().get(m));
			 // kjkjaskdjsdlk 
		 }
		 

		 for (int d=0;d<TempSC.size();d++){
			 System.out.println("after mutation "  +TempSC.get(d).getServiceId());
		 }
		 
		 offsbring.setCompositionService(TempSC);
			 //  System.out.println("    classs   "+offsbring.getCompositionService().get(random).getServiceClass());//getSeviceClass();
			      
		 // String Sclass=(String) offsbring.getCompositionService().get(random).getServiceClass();//getSeviceClass();
		      
		    //  for (int i=0; i<z.ServicePool.size();i++){
	/* if (z.ServicePool.get(i).getServiceFunctionality().equalsIgnoreCase(Sclass))
	    	  if (z.ServicePool.get(i).getServiceClass().equalsIgnoreCase(Sclass))
			RequiredClass.add(z.ServicePool.get(i));*/
		    	 
		    	//for (int j=0;j<)  
		    	  

		  /*if (z.ServicePool.get(i).getServiceClass().equalsIgnoreCase(Sclass))
		    		    	  if (z.ServicePool.get(i).getServiceClass().equalsIgnoreCase(Sclass))
		    				RequiredClass.add(z.ServicePool.get(i));
		    */  }
		  //  z.ServicePool.get(9).
		//int random2 = (PseudoRandom.randInt())%(RequiredClass.size());// edited 
		  //PseudoRandom.n
		   //  int random2 = (random.nextInt(RequiredClass.size())+1;
		
		//offsbring.getCompositionService().set(random,RequiredClass.get(random2));   // lazm trj3
		//System.out.println("2 wallah 2 ");  		
		
	//offsbring.getCompositionService().set(random,RequiredClass.get(random2));	//	    	  lazm trj3
		    
		//      }
		    } catch (ClassCastException e1) {
		      Configuration.logger_.severe("SinglePointCrossover.doCrossover: Cannot perfom " +
		              "SinglePointCrossover");
		      Class cls = java.lang.String.class;
		      String name = cls.getName();
		      throw new JMException("Exception in " + name + ".doCrossover()");
		    }
		    
		    
		  } 
		
 private Service convert(SecurityService securityService) {
	Service S=new Service();
	S.setServiceClass("Security");
	S.SetServiceCost(securityService.getServiceCost());
	S.setServiceFunctionality("Security");
	 //(securityService.getConfidentialityeffect());

	S.setConfidentialitySeverity(findeffect(securityService.getConfidentialityeffect()));
	S.setIntegritySeverity(findeffect(securityService.getIntegrityeffect()));
	S.setAvailabilitySeverity(findeffect(securityService.getAvailabilityeffect()));
	S.setServiceId(securityService.getSServiceId());
	
	 return S;
		// TODO Auto-generated method stub
		
	}

private double findeffect(Double c) {
	double x=0; 
if (c==-1)
	x= 0.2;
	if(c== -0.5)
	x=0.1;
	if (c== 0) 
	x=0;
	if (c== 0.5) 
	x=0.1; 
	if (c==1) 
	x=0.2; 
	
	
	
	return x;
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
