package POJO;

import java.util.List;

public class Courses {
	private List<webAutomation> webAutomation;
	private List<API> API;
	private List<mobile>  mobile;
	
	public List<webAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<webAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<API> getAPI() {
		return API;
	}
	public void setAPI(List<API> API) {
		this.API = API;
	}
	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<mobile> mobile) {
		this.mobile = mobile;
	}
	
}