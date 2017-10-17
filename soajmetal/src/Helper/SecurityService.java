package Helper;

public class SecurityService {

	String SServiceId;
	public String getSServiceId() {
		return SServiceId;
	}
	public void setSServiceId(String sServiceId) {
		SServiceId = sServiceId;
	}
	public String getSServiceName() {
		return SServiceName;
	}
	public void setSServiceName(String sServiceName) {
		SServiceName = sServiceName;
	}
	public String getRelatedThreat() {
		return RelatedThreat;
	}
	public void setRelatedThreat(String relatedThreat) {
		RelatedThreat = relatedThreat;
	}
	public Double getConfidentialityeffect() {
		return Confidentialityeffect;
	}
	public void setConfidentialityeffect(Double confidentialityeffect) {
		Confidentialityeffect = confidentialityeffect;
	}
	public Double getIntegrityeffect() {
		return Integrityeffect;
	}
	public void setIntegrityeffect(Double integrityeffect) {
		Integrityeffect = integrityeffect;
	}
	public Double getAvailabilityeffect() {
		return Availabilityeffect;
	}
	public void setAvailabilityeffect(Double availabilityeffect) {
		Availabilityeffect = availabilityeffect;
	}
	public Double getServiceCost() {
		return ServiceCost;
	}
	public void setServiceCost(Double serviceCost) {
		ServiceCost = serviceCost;
	}
	public String getServiceProvide() {
		return ServiceProvide;
	}
	public void setServiceProvide(String serviceProvide) {
		ServiceProvide = serviceProvide;
	}
	String SServiceName;
	String RelatedThreat;
	
	
	Double Confidentialityeffect ;
	Double Integrityeffect ;
	Double Availabilityeffect ;
	Double ServiceCost;
	String ServiceProvide;
	public SecurityService() {
		// TODO Auto-generated constructor stub
	}

}
