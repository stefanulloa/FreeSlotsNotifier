package upf.dda.project.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User {

	private String phoneNumber;
	private List<String> listSubscriptions;
	private String token;
	
	public User(){
		this.phoneNumber = "";
		this.listSubscriptions = new ArrayList<String>();
		this.token = "";
	}
	
	public User(String phoneNumber, List<String> listSubscriptions, String token) {
		super();
		this.phoneNumber = phoneNumber;
		this.listSubscriptions = listSubscriptions;
		this.token = token;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getListSubscriptions() {
		return listSubscriptions;
	}

	public void setListSubscriptions(List<String> list) {
		this.listSubscriptions = list;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String toString() {
		return "Client [phoneNumber=" + phoneNumber + ", listSubscriptions=" + listSubscriptions + ", token=" + token
				+ "]";
	}
	
	public void subscribe(String IDs) {
		
		//split the paramter by ", " and then add it on our list.
		
		String[] ids = IDs.split(", ");

		
		for(String id : ids){
			
			listSubscriptions.add(id);
			
		}
		
	}

}



