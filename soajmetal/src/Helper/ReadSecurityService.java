package Helper;
import java.io.*; 
import java.lang.reflect.Array;
import java.util.*;
import jmetal.core.Solution;

public class ReadSecurityService {

	
	
	
	

	public static ArrayList<SecurityService> SServicePool;//;=new ArrayList<SecurityService>();
	
	
	public static void main(String[] args){
		
		ReadSService();
		

		System.out.println("SServicePool "+ SServicePool.size());
		
		/*  print for testing 
		for (int i=0; i<SServicePool.size();i++){
			System.out.print(i+"    "+ SServicePool.get(i).getSServiceId()+"  "+SServicePool.get(i).getSServiceName()+"  ");
			System.out.print("    "+ SServicePool.get(i).getRelatedThreat()+"  "+SServicePool.get(i).getServiceProvide()+"  ");
			System.out.print("    "+ SServicePool.get(i).getConfidentialityeffect()+"  "+SServicePool.get(i).getAvailabilityeffect()+"  ");
			System.out.print("  "+SServicePool.get(i).getConfidentialityeffect()+"  ");
		System.out.println();
		}*/
	}
	public static void ReadSService() {
		SServicePool=new ArrayList<SecurityService>();
		
			String f ="inputs/SecurityService.txt";

		File file = new File(f);
		SecurityService SStemp = new SecurityService();	 
		String line;
		StringTokenizer st;
		String token;
		try{
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader content = new BufferedReader(isr);
			int j=0;
			while(content.ready())
			{

				line = content.readLine();
				st = new StringTokenizer(line);
				token = st.nextToken();
				SStemp=new SecurityService();
				SStemp.setSServiceId(token);
				token = st.nextToken();
				SStemp.setSServiceName(token);
				token = st.nextToken();
				SStemp.setRelatedThreat(token);
				token = st.nextToken();
				//Ttemp.setIeffect(Double.parseDouble(token));
				SStemp.setConfidentialityeffect(Double.parseDouble(token));
				token = st.nextToken();
				SStemp.setIntegrityeffect(Double.parseDouble(token));
				token = st.nextToken();
				SStemp.setAvailabilityeffect(Double.parseDouble(token));
				token = st.nextToken();
				SStemp.setServiceCost(Double.parseDouble(token));
				token = st.nextToken();
				SStemp.setServiceProvide(token);
				
				SServicePool.add(SStemp);  //add the threat to the SServicePool
				
				j++; //counter for printing 			
				SServicePool.clone();
						
						
			}
		}
		catch(Exception e){
			System.out.println("error");

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public ReadSecurityService() {
		//ReadSService();
		// TODO Auto-generated constructor stub
	}

}
