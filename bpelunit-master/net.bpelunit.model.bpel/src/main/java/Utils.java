

import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;

import net.bpelunit.model.bpel.IInvoke;
import net.bpelunit.model.bpel.IVariable;
import net.bpelunit.model.bpel._2_0.Invoke;
import net.bpelunit.model.bpel._2_0.Variable;



public class Utils {

    public static Properties configProperties;


    /**
     * reads values from config property file
     * @throws Exception throws, if fails
     */
    public static void loadConfigProperties() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            File file = new File((new File(".")).getCanonicalPath() + File.separator +
                            File.separator +"config.properties");
            System.out.println(file.toString());
            
            if(file.exists()){
               inputStream = new FileInputStream(file);
            } else {
                String msg = "File does not exist : " + "config.properties";
                System.out.println(msg);
            }
        } catch (FileNotFoundException e) {
            String msg = "File can not be found : " + "config.properties";
            System.out.println(msg);
            throw new Exception(msg, e);
        } catch (IOException e) {
            String msg = "Can not create the canonical file path for given file : " + "config.properties";
            System.out.println(msg);
            throw new Exception(msg, e);
        }

        try {
            if(inputStream != null){
                properties.load(inputStream);
            }
        } catch (IOException e) {
            String msg = "Error loading properties from config.properties file";
            System.out.println(msg);
            throw new Exception(msg, e);
        } finally {
            try {
                if(inputStream!= null){
                    inputStream.close();
                }
            } catch (IOException ignored) {
                System.out.println("Error while closing input stream");
            }
        }
        configProperties = properties;
    }

    
    public static Set<IInvoke> getOperationAttributes(){

        Set<IInvoke> set = new HashSet<IInvoke>();
       // String partnerLinkValues = configProperties.getProperty("partnerLinkValues");
        String operationValues = configProperties.getProperty("operationValues");
              
        if(operationValues != null){
            String[] values = operationValues.split(",");
            for(String value : values){
            		
          //  	IInvoke invoke = new Invoke();
            		//invoke.setPartnerLink(value);
               // invoke.setOperation(value);
               // invoke.setVariables(value.trim());
              //  set.add(invoke);
            }
        }

        return set;
    }


	public static Set<IVariable> getVariableAttributes() {
		Set<IVariable> set = new HashSet<IVariable>();
		 String variableValues = configProperties.getProperty("variableValues");
		 
		 if(variableValues != null){
	            String[] values = variableValues.split(",");
	            for(String value : values){
	            		IVariable var = new Variable(null);
	            		var.setName("request");
	            		//var.setMessageType(new QName(wsdlNamespace, "Request"));
	            		set.add(var); 
	            }
		 }
		return set;
	}


	public static int getNoOfProcesses() {
		// TODO Auto-generated method stub
		return 0;
	}

    
}
