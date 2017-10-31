package Helper;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import jmetal.core.Solution;

public class inputdataSOA {
	//public static Integer NoOfClass = 4;
	public static ArrayList<Service> ServicePool= new ArrayList<Service>();
	public static ArrayList<ServiceClass> ClassPool = new ArrayList<ServiceClass>();

	/*
	 * String ServiceId1=null; String ServiceClass1=null; String
	 * ServiceFunctionality1=null; String ServiceProvide1=null; Double
	 * ServiceCost1=(double) 0; Double SPRate1=(double) 0; int
	 * packetfiltiring1=false; int redendency1=false; Double
	 * failoverHistory1=(double) 0; int backup1=false; int
	 * encryptionStorage1=false; int encryptionTransaction1=false; int
	 * tested1=false; int LogingEnabled1=false;
	 * 
	 * Double ConfidentialitySeverity1= (double) 0; Double IntegritySeverity1
	 * =(double) 0; Double AvailabilitySeverity1 =(double) 0;
	 */
	public inputdataSOA() {
		ReadServiceClass();
		ReadServices();
	
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		/* reading */

		ReadServiceClass();
		ReadServices();
	}

	/*
	 * this function Read the Services classes (Tasks) and the user preferences
	 * for CIA
	 */
	public static void ReadServiceClass() {
		ClassPool =new ArrayList<ServiceClass>();
		String f = "inputs/serviceClass2.txt";

		File file = new File(f);
		ServiceClass Ctemp = new ServiceClass();
		String line;
		StringTokenizer st;
	
		String token;
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader content = new BufferedReader(isr);
			int j = 0;
			while (content.ready()) {
				line = content.readLine();
				st = new StringTokenizer(line);
				token = st.nextToken();
				Ctemp = new ServiceClass();
				Ctemp.setClassid(token);
				token = st.nextToken();
				Ctemp.setC_Importance(Integer.parseInt(token));

				token = st.nextToken();
				Ctemp.setI_Importance(Integer.parseInt(token));
				token = st.nextToken();
				Ctemp.setA_Importance(Integer.parseInt(token));

				ClassPool.clone();
				ClassPool.add(Ctemp);
				/* print for testing */
				System.out.println();
				System.out.println();
				System.out.println(ClassPool.get(j).getClassid() + "   " + ClassPool.get(j).getC_Importance()

						+ "   " + ClassPool.get(j).getI_Importance() + "   " + ClassPool.get(j).getA_Importance());
				j++;
				ServicePool.clone();
			}

		} catch (Exception e) {
			System.out.println("error545454");

		}
	}

	public static void ReadServices() {
		ServicePool = new ArrayList<Service>();
		//String f = "C:\\Users\\wafaa.radwan\\Desktop\\run exp input\\BP1.txt";
		String f = "inputs/BP2.txt";// aadded 3-3-2016
		//System.out.println(f);
		File file = new File(f);
		Service Stemp = new Service();
		String line;
		StringTokenizer st;
		String token;
		
		try {//System.out.println("1");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			System.out.println("2");
			BufferedReader content = new BufferedReader(isr);
			//System.out.println("3");
			int j = 0;
			while (content.ready()) {
				//System.out.println("4" +j);
				line = content.readLine();
				st = new StringTokenizer(line);
				token = st.nextToken();
				Stemp = new Service();
				Stemp.setServiceId(token);
				token = st.nextToken();
				Stemp.SetServiceClass(token);
				token = st.nextToken();
				Stemp.setServiceFunctionality(token);
				token = st.nextToken();
				Stemp.setServiceProvide(token);
				token = st.nextToken();
				Stemp.SetServiceCost(Double.parseDouble(token));
				token = st.nextToken();
				Stemp.setSPRate(Double.parseDouble(token));
				token = st.nextToken();
				Stemp.setPacketfiltiring(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setRedendency(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setFailoverHistory(Double.parseDouble(token));
				token = st.nextToken();
				Stemp.setBackup(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setEncryptionStorage(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setEncryptionTransaction(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setTested(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setLogingEnabled(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setTLS(Integer.parseInt(token));
				token = st.nextToken();
				Stemp.setDigitalSigniture(Integer.parseInt(token));
				
				token = st.nextToken();
				Stemp.setAuthenitcation(Integer.parseInt(token));
				//System.out.println("5");

				Dread.CalculateDREAD(Stemp,ClassPool);
				ServicePool.add(Stemp);
				
				//System.out.println("6");
				/* print for testing */
				
				System.out.println(ServicePool.get(j).getServiceId() + "   " + ServicePool.get(j).getSeviceClass()
						+ "  " + ServicePool.get(j).getServiceFunctionality() + "  "
						+ ServicePool.get(j).getServiceProvide() + "  " + ServicePool.get(j).getServiceCost() + " "
						+ ServicePool.get(j).getSPRate() + "  " + ServicePool.get(j).getPacketfiltiring() + "  "
						+ ServicePool.get(j).getRedendency() + "  " + ServicePool.get(j).getFailoverHistory() + "  "
						+ ServicePool.get(j).getBackup() + "  " + ServicePool.get(j).getEncryptionStorage() + "  "
						+ ServicePool.get(j).getEncryptionTransaction() + "  " + ServicePool.get(j).getTested() + "  "
						+ ServicePool.get(j).getLogingEnabled() + "  " + ServicePool.get(j).getTLS() + "  "
						+ ServicePool.get(j).getDigitalSigniture() + "  " + ServicePool.get(j).getAuthenitcation()+
						"  dread  "+  ServicePool.get(j).getConfidentialitySeverity() + "  integ" +
						ServicePool.get(j).getIntegritySeverity()+"  avail" +
								ServicePool.get(j).getAvailabilitySeverity());
				j++;
				ServicePool.clone();
				//System.out.println("7");
			}

		} 
		catch (Exception e) {
			System.out.println("error2");
			   e.printStackTrace();

		}
	}
}