package upf.dda.project.data;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class Users {

	private List<User> users;

	public Users() {

		users = new ArrayList<User>();

	}

	public String toString() {
		String aux = "";
		for (User user : users) {

			aux += user.toString();

		}
		return aux;
	}

	public List<User> getUsers() {
		return users;
	}

	public boolean exist(User u) {

		for (User user : users) {
			if (user.getPhoneNumber().equalsIgnoreCase(u.getPhoneNumber())) {
				return true;
			}

		}

		return false;
	}

	public boolean exist(String u) {

		for (User user : users) {
			if (user.getPhoneNumber().equalsIgnoreCase(u)) {
				return true;
			}

		}

		return false;
	}

	public User getUser(String name)  {
		

		for (User user : users) {
			if (user.getPhoneNumber().equalsIgnoreCase(name)) {
				return user;
			}
		}
		
		throw new NotFoundException(); 
	
	}

	public void add(User u) {

			users.add(u);


	}

}
