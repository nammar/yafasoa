package Helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Dread {
	// temp container

	public static Double tempD1C=0.0;
	public static Double tempD1I=0.0;
	public static Double tempD1A=0.0;

	public static Double tempRC=0.0;
	public static Double tempRI=0.0;
	public static Double tempRA=0.0;

	public static Double tempEC=0.0;
	public static Double tempEI=0.0;
	public static Double tempEA=0.0;

	public static Double tempAC=0.0;
	public static Double tempAI=0.0;
	public static Double tempAA=0.0;

	public static Double tempDC=0.0;
	public static Double tempDI=0.0;
	public static Double tempDA=0.0;
	public static Double General=0.0;

	public static Service Stest = new Service();
	//static Random random = new Random();// used to select threats
	public static Random r = new Random();

	public static Integer tempclassC = 0;// temp value for user C requirment
	public static Integer tempclassI = 0;// temp value for user I requirment
	public static Integer tempclassA = 0;// temp value for user A requirment

	public static double tempthreatC = 0;// temp value for threat effect
	public static double tempthreatI = 0;// temp value for threat effect
	public static double tempthreatA = 0;// temp value for threat effect

	/*
	 * each run the Threat will be changed and this group of threats are defined
	 * by end user
	 */

	public static ArrayList<Threat> SelectedList = new ArrayList<Threat>();
	public static ReadThreat ThreatList = new ReadThreat();// define threat list
															// by wafaa
															// 28-1-2016

	//public static inputdataSOA S = new inputdataSOA();// we can remove this if
														// we call
														// DreadCalculate
														// function by the
														// experiment

	public Dread() {}// constructor 
	public static void main(String[] args) {
	
		//	ReadThreat.ReadThreat();// call Read Threat function
		//SelectThreat(); // Enable user to select threats from the Threat List
		/* Print the selected list - just for testing */
		/*
		 * for (int j = 0; j < SelectedList.size(); j++) { System.out.println(
		 * "  Selected Threat number :  " + j);
		 * System.out.println(SelectedList.get(j).getThreatId() + "   " +
		 * SelectedList.get(j).getThreatName() + "  " +
		 * SelectedList.get(j).getCeffect() + "  " +
		 * SelectedList.get(j).getIeffect() + "  " +
		 * SelectedList.get(j).getAeffect()); }
		 */
		/*
		 * here just for test, we need a services to test the CalculateDREAD
		 * function
		 */

	//	S.ReadServices(); // Read services also for testing
		//Stest = S.ServicePool.get(17);// for testing only get a service randomly
		//S.ReadServiceClass();
		/*
		 * Call Function that Calculate Dread Values for the selected Threats
		 * for each service here the service is any service just for test
		 */
		//CalculateDREAD( Stest);//

	}

	public static void CalculateDREAD( Service stest2, ArrayList<ServiceClass> classPool) {
		ReadThreat.ReadThreat();// call Read Threat function
		SelectThreat(); // Enable user to select threats from the Threat List
		//S.ReadServices(); // Read services also for testing
		//Stest = S.ServicePool.get(17);// for testing only get a service randomly
	//	S.Classpool;
		findpreferences(stest2,classPool);// find user prefrences regarding to this
								// service class
		 General = findServiceGeneralSpecs(stest2); // find service
															// general specs
															// from 30
		for (int k = 0; k < SelectedList.size(); k++) 
		{ // for all threat
			 tempD1C=0.0;
			 tempD1I=0.0;
			tempD1A=0.0;

			tempRC=0.0;
			tempRI=0.0;
			tempRA=0.0;

			 tempEC=0.0;
			 tempEI=0.0;
			tempEA=0.0;

			tempAC=0.0;
			tempAI=0.0;
			tempAA=0.0;

			tempDC=0.0;
			tempDI=0.0;
			tempDA=0.0;
			General=0.0;

			
			//tempclassC = 0;// temp value for user C requirment
			//tempclassI = 0;// temp value for user I requirment
			//tempclassA = 0;// temp value for user A requirment

			tempthreatC = 0.0;// temp value for threat effect
			tempthreatI = 0.0;// temp value for threat effect
			tempthreatA = 0.0;// temp value for threat effect
			
			/*
			 * la kol threat bstad3i el 3 functions ele bf7aso 5asa2es el
			 * service 3shan n3raf el threat rate la hay el service
			 */
			
			
			
			if (SelectedList.get(k).getIeffect() != 0)
			{	findI(stest2, SelectedList.get(k));}
			//else
			else			if (SelectedList.get(k).getIeffect() == 0)
			{

				//System.out.println(SelectedList.get(k).getThreatId() + "has zero I");
				tempD1I = 0.0;
				tempRI = 0.0;
				tempEI = 0.0;
				tempAI = 0.0;
				tempDI = 0.0;
			}
			
				if (SelectedList.get(k).getAeffect() != 0){
				findA(stest2, SelectedList.get(k));}
				else		if (SelectedList.get(k).getAeffect() == 0) {
				//System.out.println( SelectedList.get(k).getThreatId() + "has zero A");
				tempD1A = 0.0;
				tempRA = 0.0;
				tempEA = 0.0;
				tempAA = 0.0;
				tempDA = 0.0;
			}
					if (SelectedList.get(k).getCeffect() != 0)
					{	findC(stest2, SelectedList.get(k));}
				//	else 
					else	if (SelectedList.get(k).getCeffect() == 0) {
					//	System.out.println( SelectedList.get(k).getThreatId() + "has zero C");
						tempD1C = 0.0;
						tempRC = 0.0;
						tempEC = 0.0;
						tempAC = 0.0;
						tempDC = 0.0;
					}
					// bdi awjed kol threat sho b3mal 3ala CIA b3dha
			// aqaren el service nafs-ha sho elha 5asa2es 3shan awjed el DREAD
					findSeperatedRate(SelectedList.get(k));// find threat serverity for each CIA for this service 
		
		}
		findServiceseverity(stest2);
		
	
	}

	private static void findServiceseverity(Service stest2) {
		// TODO Auto-generated method stub
	//temp
		
		double C=0.0,I=0.0,A=0.0;
		for (int t=0; t<SelectedList.size();t++){
				C+=SelectedList.get(t).getDreadC();
				I+=SelectedList.get(t).getDreadI();
				A+=SelectedList.get(t).getDreadA();
					
		}
		C=C/SelectedList.size();

		I=I/SelectedList.size();
		A=A/SelectedList.size();
		//System.out.println("C:  "+ C +"   threat C"+I + "A   "+A);
		//C=tempthreatC* tempclassC /10;
		
			
		//I=tempthreatI*tempclassI/10;
		//A=tempthreatA*tempclassA/10;

		//System.out.println(  tempthreatC+"     C  "+ tempthreatI+"    I     "
			//	+tempthreatA+"    A  ");
		
		//System.out.println(  "Serverites ");
		stest2.setConfidentialitySeverity(C);	
		stest2.setIntegritySeverity(I);
		stest2.setAvailabilitySeverity(A);
		
	//	System.out.println(  stest2.getServiceId()+"    "+ stest2.getConfidentialitySeverity()+"      "
		//		+stest2.getIntegritySeverity()+"   "+stest2.getAvailabilitySeverity()+"   ");
	}
	
	
	
	private static void findSeperatedRate(Threat threat) {
		// TODO Auto-generated method stub
		//general*
		//General * 30 % + temptreatC*70%
		//General * 30 % + temptreatI*70%		
		//General * 30 % + temptreatA*70%		
		
		threat.setD1((tempD1C+tempD1I+tempD1A)/30);
		threat.setR((tempRC+tempRI+tempRA)/30);
		threat.setE((tempEC+tempEI+tempEA)/30);
		threat.setA((tempAC+tempAI+tempAA)/30);
		threat.setD((tempDC+tempDI+tempDA)/30);
		threat.setDreadRate((threat.getD1()+threat.getR()+threat.getE()+threat.getA()+threat.getD())/5);
		tempthreatC=  ((tempD1C+tempRC+tempEC+tempAC+tempDC)/50);	
		tempthreatI=  ((tempD1I+tempRI+tempEI+tempAI+tempDI)/50);	
		tempthreatA=  ((tempD1A+tempRA+tempEA+tempAA+tempDA)/50);	
		threat.setDreadC(tempthreatC);
		threat.setDreadI(tempthreatI);
		threat.setDreadA(tempthreatA);
		//System.out.println(threat.getThreatId()+" tempthreatC  value: "+tempthreatC);
		//System.out.println();
		//System.out.println( threat.getThreatId()+"  tempthreatI value "+tempthreatI);
		//System.out.println();
		//System.out.println( threat.getThreatId()+"    tempthreatA  value:"+tempthreatA);
		
		// connection between general specs and CIA specs by percentage .3 to .7  
		//'tempthreatC=tempthreatC*0.7+General*0.3;
		//tempthreatI=tempthreatI*0.7+General*0.3;
		//tempthreatA=tempthreatA*0.7+General*0.3;
	
		
		//System.out.println("tempthreatC  afrter general "+tempthreatC);

//		System.out.println("tempthreatI  afrter general "+tempthreatI);

	//	System.out.println("tempthreatA  afrter general "+tempthreatA);
	}

	/* check sprate , pucket filtiring and tested or not */
	private static double findServiceGeneralSpecs(Service stest2) {
		double general = 0.0;
//		System.out.println(general + " general = 0   ");

		if (stest2.getTested() == 1) {
			general += 9 + (10 - 9) * r.nextInt();// rand 9 10 
	//		System.out.println(general + " tested general    ");
		}
		if (stest2.getPacketfiltiring() == 1) {
			general +=  9 + (10 - 9) * r.nextInt(); // rand 9 10 
		//	System.out.println(general + " PF general  ");
		}
		general += (stest2.getSPRate() * 2);
//		System.out.println(general + " with sprate    ");

	//	System.out.println(general + " service general specs   ");
		return general;

	}

	private static void findC(Service stest2, Threat threat) {
		// y: number of enabled C attributes
		int y = (stest2.getAuthenitcation()) + (stest2.getEncryptionStorage()) + (stest2.getEncryptionTransaction())
				+ (stest2.getLogingEnabled());

		int x = (threat.getCeffect().intValue());
		switch (x) {

		case 5:

			//System.out.println("in 5 threat " + threat.getThreatId() + " ok");

			if (y == 0||General<=10) {// from 7 to 9
//				System.out.println("  y   " + y + " 5");

				tempD1C = 7 + (9 - 7) * r.nextDouble();
	//			System.out.println("tempD1C " + tempD1C);
				tempRC = 7 + (9 - 7) * r.nextDouble();
		//		System.out.println("tempRC " + tempRC);

				tempEC = 7 + (9 - 7) * r.nextDouble();

			//	System.out.println("tempEC " + tempEC);
				tempAC = 7 + (9 - 7) * r.nextDouble();

				//System.out.println("tempAC " + tempAC);
				tempDC = 7 + (9 - 7) * r.nextDouble();

		//		System.out.println("tempDC " + tempDC);

			}

			// =8.0;

			if (y == 1 ||General<=15)

			{ // from 7-6
				tempD1C = 6 + (7 - 6) * r.nextDouble();
				tempRC = 6 + (7 - 6) * r.nextDouble();
				tempEC = 6 + (7 - 6) * r.nextDouble();
				tempAC = 6 + (7 - 6) * r.nextDouble();
				tempDC = 6 + (7 - 6) * r.nextDouble();
			//	System.out.println("  y   " + y + " 5");
				//System.out.println("tempD1C " + tempD1C);
				//System.out.println("tempRC " + tempRC);
				//System.out.println("tempEC " + tempEC);
		//		System.out.println("tempAC " + tempAC);
			//	System.out.println("tempDC " + tempDC);
			}

			if (y == 2 ||General<=20) {// from 5-4
			//	System.out.println("  y   " + y + " 5");
				tempD1C = 4 + (5 - 4) * r.nextDouble();
				tempRC = 4 + (5 - 4) * r.nextDouble();
				tempEC = 4 + (5 - 4) * r.nextDouble();
				tempAC = 4 + (5 - 4) * r.nextDouble();
				tempDC = 4 + (5 - 4) * r.nextDouble();
		//		System.out.println("  y   " + y + " 10");
			//	System.out.println("tempD1C " + tempD1C);
			//	System.out.println("tempRC " + tempRC);
			//	System.out.println("tempEC " + tempEC);
			//	System.out.println("tempAC " + tempAC);
			//	System.out.println("tempDC " + tempDC);
			}
			if (y == 3||General<=25)

			{// from 3-2
				tempD1C = 2 + r.nextDouble();
				tempRC = 2 + r.nextDouble();
				tempEC = 2 + r.nextDouble();
				tempAC = 2 + r.nextDouble();
				tempDC = 2 + r.nextDouble();
		//		System.out.println("  y   " + y + " 5");
		//		System.out.println("tempD1C " + tempD1C);
		//		System.out.println("tempRC " + tempRC);
		//		System.out.println("tempEC " + tempEC);
		//		System.out.println("tempAC " + tempAC);
		//		System.out.println("tempDC " + tempDC);
			}

			if (y == 4|| (25 < General && General <30)) {// from 2-1
				tempD1C = 1 + (2 - 1) * r.nextDouble();
				tempRC = 1 + (2 - 1) * r.nextDouble();
				tempEC = 1 + (2 - 1) * r.nextDouble();
				tempAC = 1 + (2 - 1) * r.nextDouble();
				tempDC = 1 + (2 - 1) * r.nextDouble();
	//			System.out.println("tempD1C " + tempD1C);
//				System.out.println("tempRC " + tempRC);
	//			System.out.println("tempEC " + tempEC);
	//			System.out.println("tempAC " + tempAC);
	//			System.out.println("tempDC " + tempDC);
			}
			break;

		case 10:
			if (y == 0 ||General<=10) {// from 10 to 9
				//System.out.println("  y   " + y + " 10");

				tempD1C = 9 + (10 - 9) * r.nextDouble();
		//		System.out.println("tempD1C " + tempD1C);
				tempRC = 9 + (10 - 9) * r.nextDouble();
		//		System.out.println("tempRC " + tempRC);

				tempEC = 9 + (10 - 9) * r.nextDouble();

//				System.out.println("tempEC " + tempEC);
				tempAC = 9 + (10 - 9) * r.nextDouble();

	//			System.out.println("tempAC " + tempAC);
				tempDC = 9 + (10 - 9) * r.nextDouble();

		//		System.out.println("tempDC " + tempDC);

			}

			// =8.0;

			if (y == 1 ||General<=15)

			{ // from 8-7
				tempD1C = 7 + (8 - 7) * r.nextDouble();
				tempRC = 7 + (8 - 7) * r.nextDouble();
				tempEC = 7 + (8 - 7) * r.nextDouble();
				tempAC = 7 + (8 - 7) * r.nextDouble();
				tempDC = 7 + (8 - 7) * r.nextDouble();
		//		System.out.println("  y   " + y + " 10");
		//		System.out.println("tempD1C " + tempD1C);
		//		System.out.println("tempRC " + tempRC);
		//		System.out.println("tempEC " + tempEC);
		//		System.out.println("tempAC " + tempAC);
		//		System.out.println("tempDC " + tempDC);
			}

			if (y == 2|| General<=20) {// from 7-6
			//	System.out.println("  y   " + y + " 10");
				tempD1C = 6 + r.nextDouble();
				tempRC = 6 + r.nextDouble();
				tempEC = 6 + r.nextDouble();
				tempAC = 6 + r.nextDouble();
				tempDC = 6 + r.nextDouble();
	//			System.out.println("  y   " + y + " 10");
	//			System.out.println("tempD1C " + tempD1C);
	//			System.out.println("tempRC " + tempRC);
	//			System.out.println("tempEC " + tempEC);
	//			System.out.println("tempAC " + tempAC);
	//			System.out.println("tempDC " + tempDC);
			}
			if (y == 3||General<=25)

			{// from 5-4
				tempD1C = 4 + r.nextDouble();
				tempRC = 4 + r.nextDouble();
				tempEC = 4 + r.nextDouble();
				tempAC = 4 + r.nextDouble();
				tempDC = 4 + r.nextDouble();
	//			System.out.println("  y   " + y + " 10");
	//			System.out.println("tempD1C " + tempD1C);
	//			System.out.println("tempRC " + tempRC);
	//			System.out.println("tempEC " + tempEC);
	//			System.out.println("tempAC " + tempAC);
	//			System.out.println("tempDC " + tempDC);
			}

			if (y == 4||(25 < General && General <30)) {// from 4-2
				tempD1C = 4 + (4 - 2) * r.nextDouble();
				tempRC = 4 + (4 - 2) * r.nextDouble();
				tempEC = 4 + (4 - 2) * r.nextDouble();
				tempAC = 4 + (4 - 2) * r.nextDouble();
				tempDC = 4 + (4 - 2) * r.nextDouble();
	//			System.out.println("tempD1C " + tempD1C);
	//			System.out.println("tempRC " + tempRC);
	//			System.out.println("tempEC " + tempEC);
	//			System.out.println("tempAC " + tempAC);
	//			System.out.println("tempDC " + tempDC);
			}
			break;

		}
	}

	private static void findI(Service stest2, Threat threat) {
		int y = stest2.getEncryptionTransaction() + stest2.getLogingEnabled() + stest2.getTLS()
				+ stest2.getDigitalSigniture();
		int x = (threat.getCeffect().intValue());
		switch (x) {

		case 5:

			//System.out.println("in 5 threat " + threat.getThreatId() + " ok      I");

			if (y == 0||General<=10){// from 7 to 9
			//	System.out.println("  y   " + y + " 5");

				tempD1I = 7 + (9 - 7) * r.nextDouble();
				//System.out.println("tempD1I " + tempD1I);
				tempRI = 7 + (9 - 7) * r.nextDouble();
		//		System.out.println("tempRI " + tempRI);
				tempEI = 7 + (9 - 7) * r.nextDouble();
		//		System.out.println("tempEI " + tempEI);
				tempAI = 7 + (9 - 7) * r.nextDouble();
		//		System.out.println("tempAI " + tempAI);
				tempDI = 7 + (9 - 7) * r.nextDouble();
			//	System.out.println("tempDI " + tempDI);

			}

			// =8.0;

			if (y == 1||General<=15)

			{ // from 7-6
				tempD1I = 6 + (7 - 6) * r.nextDouble();
				tempRI = 6 + (7 - 6) * r.nextDouble();
				tempEI = 6 + (7 - 6) * r.nextDouble();
				tempAI = 6 + (7 - 6) * r.nextDouble();
				tempDI = 6 + (7 - 6) * r.nextDouble();
		//		System.out.println("  y   " + y + " 5");
		//		System.out.println("tempD1I " + tempD1I);
		//		System.out.println("tempRI " + tempRI);
		//		System.out.println("tempEI " + tempEI);
		//		System.out.println("tempAI " + tempAI);
		//		System.out.println("tempDI " + tempDI);
			}

			if (y == 2|| General<=20) {// from 5-4
//				System.out.println("  y   " + y + " 5");
				tempD1I = 4 + (5 - 4) * r.nextDouble();
				tempRI = 4 + (5 - 4) * r.nextDouble();
				tempEI = 4 + (5 - 4) * r.nextDouble();
				tempAI = 4 + (5 - 4) * r.nextDouble();
				tempDI = 4 + (5 - 4) * r.nextDouble();
	//			System.out.println("  y   " + y + " 10");
	//			System.out.println("tempD1I " + tempD1I);
	//			System.out.println("tempRI " + tempRI);
	//			System.out.println("tempEI " + tempEI);
	//			System.out.println("tempAI " + tempAI);
	//			System.out.println("tempDI " + tempDI);
			}
			if (y == 2|| General<=25)
			{// from 3-2
				tempD1I = 2 + r.nextDouble();
				tempRI = 2 + r.nextDouble();
				tempEI = 2 + r.nextDouble();
				tempAI = 2 + r.nextDouble();
				tempDI = 2 + r.nextDouble();
	//			System.out.println("  y   " + y + " 5");
	//			System.out.println("tempD1I " + tempD1I);
	//			System.out.println("tempRI " + tempRI);
	//			System.out.println("tempEI " + tempEI);
	//			System.out.println("tempAI" + tempAI);
	//			System.out.println("tempDI " + tempDI);
			}

			if  (y == 4||(25 < General && General <30)) {// from 2-1
				tempD1I = 1 + (2 - 1) * r.nextDouble();
				tempRI = 1 + (2 - 1) * r.nextDouble();
				tempEI = 1 + (2 - 1) * r.nextDouble();
				tempAI = 1 + (2 - 1) * r.nextDouble();
				tempDI = 1 + (2 - 1) * r.nextDouble();
	//			System.out.println("  y   " + y + " 5");
	//			System.out.println("tempD1I " + tempD1I);
	//			System.out.println("tempRI " + tempRI);
	//			System.out.println("tempEI " + tempEI);
	//			System.out.println("tempAI" + tempAI);
	//			System.out.println("tempDI " + tempDI);
			}
			break;

		case 10:
			if (y == 2|| General<=10) {// from 10 to 9
				//System.out.println("  y   " + y + " 10");

				tempD1I = 9 + (10 - 9) * r.nextDouble();
				tempRI = 9 + (10 - 9) * r.nextDouble();
				tempEI = 9 + (10 - 9) * r.nextDouble();
				tempAI = 9 + (10 - 9) * r.nextDouble();
				tempDI = 9 + (10 - 9) * r.nextDouble();
//				System.out.println("  y   " + y + " 10");
//				System.out.println("tempD1I " + tempD1I);
//				System.out.println("tempRI " + tempRI);
//				System.out.println("tempEI " + tempEI);
//				System.out.println("tempAI" + tempAI);
//				System.out.println("tempDI " + tempDI);

			}

			// =8.0;

			if (y == 2|| General<=15)

			{ // from 8-7
				tempD1I = 7 + (8 - 7) * r.nextDouble();
				tempRI = 7 + (8 - 7) * r.nextDouble();
				tempEI = 7 + (8 - 7) * r.nextDouble();
				tempAI = 7 + (8 - 7) * r.nextDouble();
				tempDI = 7 + (8 - 7) * r.nextDouble();
	//			System.out.println("  y   " + y + " 10");
	//			System.out.println("tempD1I " + tempD1I);
	//			System.out.println("tempRI " + tempRI);
	//			System.out.println("tempEI " + tempEI);
	//			System.out.println("tempAI" + tempAI);
	//			System.out.println("tempDI " + tempDI);

			}

			if (y == 2|| General<=20) {// from 7-6
			//	System.out.println("  y   " + y + " 10");
				tempD1I = 6 + r.nextDouble();
				tempRI = 6 + r.nextDouble();
				tempEI = 6 + r.nextDouble();
				tempAI = 6 + r.nextDouble();
				tempDI = 6 + r.nextDouble();
	//			System.out.println("  y   " + y + " 10");
	//			System.out.println("tempD1I " + tempD1I);
	//			System.out.println("tempRI " + tempRI);
	//			System.out.println("tempEI " + tempEI);
	//			System.out.println("tempAI" + tempAI);
	//			System.out.println("tempDI " + tempDI);

			}
			if (y == 2|| General<=25)

			{// from 5-4
				tempD1I = 4 + r.nextDouble();
				tempRI = 4 + r.nextDouble();
				tempEI = 4 + r.nextDouble();
				tempAI = 4 + r.nextDouble();
				tempDI = 4 + r.nextDouble();
	//			System.out.println("  y   " + y + " 10");
	//			System.out.println("tempD1I " + tempD1I);
	//			System.out.println("tempRI " + tempRI);
	//			System.out.println("tempEI " + tempEI);
	//			System.out.println("tempAI" + tempAI);
	//			System.out.println("tempDI " + tempDI);

			}

			if  (y == 4||(25 < General && General <30)) {// from 4-2
				tempD1I = 4 + (4 - 2) * r.nextDouble();
				tempRI = 4 + (4 - 2) * r.nextDouble();
				tempEI = 4 + (4 - 2) * r.nextDouble();
				tempAI = 4 + (4 - 2) * r.nextDouble();
				tempDI = 4 + (4 - 2) * r.nextDouble();

//				System.out.println("  y   " + y + " 10");
//				System.out.println("tempD1I " + tempD1I);
//				System.out.println("tempRI " + tempRI);
//				System.out.println("tempEI " + tempEI);
//				System.out.println("tempAI" + tempAI);
//				System.out.println("tempDI " + tempDI);

			}
			break;

			
		}
	}

	private static void findA(Service stest2, Threat threat) {

		int y = stest2.getRedendency()+stest2.getBackup();
		double f=stest2.getFailoverHistory();
				
		int x = (threat.getCeffect().intValue());
switch (x) {

case 5:

	//System.out.println("in 5 threat " + threat.getThreatId() + " ok      I");

	if (y == 0 || f>0.8 || General<=10 ) {// from 7 to 9
		//System.out.println("  y   " + y + " 5");
		tempD1A = 7 + (9 - 7) * r.nextDouble();
		//System.out.println("tempD1A " + tempD1A);
		tempRA = 7 + (9 - 7) * r.nextDouble();
		//System.out.println("tempRA " + tempRA);
		tempEA = 7 + (9 - 7) * r.nextDouble();
		//System.out.println("tempEA " + tempEA);
		tempAA = 7 + (9 - 7) * r.nextDouble();
		//System.out.println("tempAA " + tempAA);
		tempDA = 7 + (9 - 7) * r.nextDouble();
		//System.out.println("tempDA " + tempDA);

	}

	// =8.0;

	if (y == 1||f>=0.5|| General <=15)

	{ // from 7-6
		tempD1A = 6 + (7 - 6) * r.nextDouble();
		tempRA = 6 + (7 - 6) * r.nextDouble();
		tempEA = 6 + (7 - 6) * r.nextDouble();
		tempAA = 6 + (7 - 6) * r.nextDouble();
		tempDA = 6 + (7 - 6) * r.nextDouble();
		//System.out.println("  y   " + y + " 5");
	//	System.out.println("tempD1A " + tempD1A);
	//	System.out.println("tempRA " + tempRA);
//		System.out.println("tempEA " + tempEA);
	//	System.out.println("tempAA " + tempAA);
		//System.out.println("tempDA " + tempDA);
	}

	if (y == 2||f>=0.3|| General <=20) {// from 5-4
		//System.out.println("  y   " + y + " 5");
		tempD1A = 4 + (5 - 4) * r.nextDouble();
		tempRA = 4 + (5 - 4) * r.nextDouble();
		tempEA = 4 + (5 - 4) * r.nextDouble();
		tempAA = 4 + (5 - 4) * r.nextDouble();
		tempDA = 4 + (5 - 4) * r.nextDouble();
		//System.out.println("  y   " + y + " 10");
		//System.out.println("tempD1A " + tempD1A);
		//System.out.println("tempRA " + tempRA);
		//System.out.println("tempEA " + tempEA);
		//System.out.println("tempAA " + tempAA);
		//System.out.println("tempDA " + tempDA);
	}
	if (f<0.2 || (20<=General&& General <=30))

	{// from 3-2
		tempD1A = 2 + r.nextDouble();
		tempRA = 2 + r.nextDouble();
		tempEA = 2 + r.nextDouble();
		tempAA = 2 + r.nextDouble();
		tempDA = 2 + r.nextDouble();
//		System.out.println("  y   " + y + " 5");
	//	System.out.println("tempD1A " + tempD1A);
		//System.out.println("tempRA " + tempRA);
//		System.out.println("tempEA " + tempEA);
	//	System.out.println("tempAA" + tempAA);
		//System.out.println("tempDA " + tempDA);
	}

	
	break;

case 10:
	if (y == 0 || f>0.8||General <=10) {// from 10 to 9
		//System.out.println("  y   " + y + " 10");

		tempD1A = 9 + (10 - 9) * r.nextDouble();
		tempRA = 9 + (10 - 9) * r.nextDouble();
		tempEA = 9 + (10 - 9) * r.nextDouble();
		tempAA = 9 + (10 - 9) * r.nextDouble();
		tempDA = 9 + (10 - 9) * r.nextDouble();
		/*System.out.println("  y   " + y + " 10");
		System.out.println("tempD1A " + tempD1A);
		System.out.println("tempRA " + tempRA);
		System.out.println("tempEA " + tempEA);
		System.out.println("tempAA" + tempAA);
		System.out.println("tempDA " + tempDA);
*/
	}

	// =8.0;

	if  (y == 1||f>=0.5|| General <=15)

	{ // from 8-7
		tempD1A = 7 + (8 - 7) * r.nextDouble();
		tempRA = 7 + (8 - 7) * r.nextDouble();
		tempEA = 7 + (8 - 7) * r.nextDouble();
		tempAA = 7 + (8 - 7) * r.nextDouble();
		tempDA = 7 + (8 - 7) * r.nextDouble();
/*		System.out.println("  y   " + y + " 10");
		System.out.println("tempD1A " + tempD1A);
		System.out.println("tempRA " + tempRA);
		System.out.println("tempEA " + tempEA);
		System.out.println("tempAA" + tempAA);
		System.out.println("tempDA " + tempDA);
*/
	}

	if (y == 2||f>=0.3|| General <=20) {// from 7-6
		//System.out.println("  y   " + y + " 10");
		tempD1A = 6 + r.nextDouble();
		tempRA = 6 + r.nextDouble();
		tempEA = 6 + r.nextDouble();
		tempAA = 6 + r.nextDouble();
		tempDA = 6 + r.nextDouble();
/*		System.out.println("  y   " + y + " 10");
		System.out.println("tempD1A " + tempD1A);
		*ut.println("tempRA " + tempRA);
		System.out.println("tempEA " + tempEA);
		System.out.println("tempAA" + tempAA);
		System.out.println("tempDA " + tempDA);
*/
	}
	if (f<0.2 || (20<=General&& General <=30))

	{// from 5-4
		tempD1A = 4 + r.nextDouble();
		tempRA = 4 + r.nextDouble();
		tempEA = 4 + r.nextDouble();
		tempAA = 4 + r.nextDouble();
		tempDA = 4 + r.nextDouble();
/*		System.out.println("  y   " + y + " 10");
		System.out.println("tempD1A " + tempD1A);
		System.out.println("tempRA " + tempRA);
		System.out.println("tempEA " + tempEA);
		System.out.println("tempAA" + tempAA);
		System.out.println("tempDA " + tempDA);
*/
	}

	
	break;

	
}
		
		
		
		// TODO Auto-generated method stub

	}

	private static void findpreferences(Service stest2, ArrayList<ServiceClass> classPool) {

		/* find user preferences for each service */
		for (int c = 0; c < classPool.size(); c++) {
			if (stest2.getServiceClass().equals(classPool.get(c).getClassid())) {
				tempclassC = classPool.get(c).getC_Importance();
				tempclassI = classPool.get(c).getI_Importance();
				tempclassA = classPool.get(c).getA_Importance();
			}
		}

		//System.out.println("ClassC=  " + tempclassC + "   ClassI=  " + tempclassI + "  ClassA=  " + tempclassA);

	}

	/*
	 * this function aim to select a group of threats that will be used through
	 * heuristic Search to eliminate its risk
	 */
	public static void SelectThreat() { // Select Threat

		for (int i = 0; i < 5; i++) {
			int RandomVal = r.nextInt(ThreatList.ThreatPool.size());
			SelectedList.add(ThreatList.ThreatPool.get(RandomVal));
		}

	}

}


