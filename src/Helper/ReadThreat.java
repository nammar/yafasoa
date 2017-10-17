package Helper;

import java.io.*; 
import java.lang.reflect.Array;
import java.util.*;

import jmetal.core.Solution;
public class ReadThreat {
	public static ArrayList<Threat> ThreatPool=new ArrayList<Threat>();
	String ThreatId=null;
	String ThreatName=null;
	
	Double Ceffect=(double) 0;
	Double	Ieffect=(double) 0;
	Double Aeffect=(double) 0;
	public ReadThreat() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
	
		/*reading */  
		ReadThreat();
	}

	public static void ReadThreat(){
		String f ="C:\\Users\\wafaa.radwan\\Desktop\\run exp input\\Threat.txt";

		File file = new File(f);
		Threat Ttemp = new Threat();	 
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
				Ttemp=new Threat();
				Ttemp.setThreatId(token);
				token = st.nextToken();
				Ttemp.setThreatName(token);
				token = st.nextToken();
				Ttemp.setCeffect(Double.parseDouble(token));
				token = st.nextToken();
				Ttemp.setIeffect(Double.parseDouble(token));
				token = st.nextToken();
				Ttemp.setAeffect(Double.parseDouble(token));				
				Ttemp.setD1(null);// initialize values 
				Ttemp.setR(null);// initialize values
				Ttemp.setE(null);// initialize values
				Ttemp.setA(null);// initialize values
				Ttemp.setD(null);				// initialize values
				Ttemp.setDreadRate(null);// initialize values

				ThreatPool.add(Ttemp);  //add the threat to the threatpool
				/*print threat on consol for testing */
				/*System.out.println(ThreatPool.get(j).getThreatId()+"   " +
						ThreatPool.get(j).getThreatName()+"  "
						+ ThreatPool.get(j).getCeffect()+
						"  "+ThreatPool.get(j).getIeffect()+"  "
						+ThreatPool.get(j).getAeffect());*/
				j++; //counter for printing 			
				ThreatPool.clone();
						
						
			}
		}
		catch(Exception e){
			System.out.println("error");

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
