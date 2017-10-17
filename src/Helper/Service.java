package Helper;

public class Service {
	String ServiceId;
	String ServiceClass;
	String ServiceFunctionality;
	String ServiceProvide;
	Double ServiceCost;
	Double	SPRate;
	Integer packetfiltiring;
	Integer redendency;
	Double failoverHistory;
	Integer backup;
	Integer encryptionStorage;
	Integer  encryptionTransaction;
	Integer  tested;
	Integer LogingEnabled;
	Integer TLS;
	Integer DigitalSigniture;
	Integer Authenitcation;
	Double ConfidentialitySeverity ;
	Double IntegritySeverity ;
	Double AvailabilitySeverity ;

	
	public Integer getDigitalSigniture() {
		return DigitalSigniture;
	}



	public void setDigitalSigniture(Integer digitalSigniture) {
		DigitalSigniture = digitalSigniture;
	}



	public Integer getAuthenitcation() {
		return Authenitcation;
	}



	public void setAuthenitcation(Integer authenitcation) {
		Authenitcation = authenitcation;
	}



	public Double getConfidentialitySeverity() {
		return ConfidentialitySeverity;
	}



	public Integer getTLS() {
		return TLS;
	}



	public void setTLS(Integer tLS) {
		TLS = tLS;
	}



	public void setConfidentialitySeverity(Double confidentialitySeverity) {
		ConfidentialitySeverity = confidentialitySeverity;
	}



	public Double getIntegritySeverity() {
		return IntegritySeverity;
	}



	public void setIntegritySeverity(Double integritySeverity) {
		IntegritySeverity = integritySeverity;
	}



	public Double getAvailabilitySeverity() {
		return AvailabilitySeverity;
	}



	public void setAvailabilitySeverity(Double availabilitySeverity) {
		AvailabilitySeverity = availabilitySeverity;
	}



	public void setServiceProvide(String serviceProvide) {
		ServiceProvide = serviceProvide;
	}

	
public String getServiceId() {
	return ServiceId;
}



public void setServiceId(String serviceId) {
	ServiceId = serviceId;
}





public Double getSPRate() {
	return SPRate;
}



public void setSPRate(Double sPRate) {
	SPRate = sPRate;
}



public Integer getPacketfiltiring() {
	return packetfiltiring;
}



public void setPacketfiltiring(Integer packetfiltiring) {
	this.packetfiltiring = packetfiltiring;
}



public Integer getRedendency() {
	return redendency;
}



public void setRedendency(Integer redendency) {
	this.redendency = redendency;
}



public Double getFailoverHistory() {
	return failoverHistory;
}



public void setFailoverHistory(Double failoverHistory) {
	this.failoverHistory = failoverHistory;
}



public Integer getBackup() {
	return backup;
}



public void setBackup(Integer backup) {
	this.backup = backup;
}



public Integer getEncryptionStorage() {
	return encryptionStorage;
}



public void setEncryptionStorage(Integer encryptionStorage) {
	this.encryptionStorage = encryptionStorage;
}



public Integer getEncryptionTransaction() {
	return encryptionTransaction;
}



public void setEncryptionTransaction(Integer encryptionTransaction) {
	this.encryptionTransaction = encryptionTransaction;
}



public Integer getTested() {
	return tested;
}



public void setTested(Integer tested) {
	this.tested = tested;
}



public Integer getLogingEnabled() {
	return LogingEnabled;
}



public void setLogingEnabled(Integer logingEnabled) {
	LogingEnabled = logingEnabled;
}



public String getServiceClass() {
	return ServiceClass;
}



public void setServiceClass(String serviceClass) {
	ServiceClass = serviceClass;
}



public void setServiceCost(Double serviceCost) {
	ServiceCost = serviceCost;
}




	public Service() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public void SetServiceClass(String V_ServiceClass){
		this.ServiceClass=V_ServiceClass;
	}
	
	public String getSeviceClass(){
		return this.ServiceClass;
		
	}	
	
	
	
	
	public void setServiceFunctionality(String V_ServiceFunctionality){
		this.ServiceFunctionality=V_ServiceFunctionality;
	}
	
	public String getServiceFunctionality(){
		return this.ServiceFunctionality;
		
	}
	
	public void SetServiceProvide(String V_ServiceProvide){
		this.ServiceProvide=V_ServiceProvide;
	}
	
	public String getServiceProvide(){
		return this.ServiceProvide;
		
	}
	
	public void SetServiceCost(double V_ServiceCost){
		this.ServiceCost=V_ServiceCost;
	}
	
	public double getServiceCost(){
		return this.ServiceCost;
		
	}
	
	

}
