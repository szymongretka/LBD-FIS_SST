package pl.szymon.gretka.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import pl.szymon.gretka.enums.ROLES;


@RequestScoped
public class User {
	
	String firstName;
	String lastName;
	ROLES role;
	String language;
	String sessionId;
	String adressIp;
	String password;
	String username;
	
	public User() {};
	

	public User(String firstName, String lastName, ROLES role, String language, String sessionId, String adressIp, String password, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.language = language;
		this.sessionId = sessionId;
		this.adressIp = adressIp;
		this.password = password;
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ROLES getRole() {
		return role;
	}
	public void setRole(ROLES role) {
		this.role = role;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getAdressIp() {
		return adressIp;
	}
	public void setAdressIp(String adressIp) {
		this.adressIp = adressIp;
	}

	@Override
	public String toString() {
		return "Users [firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", language=" + language
				+ ", sessionId=" + sessionId + ", adressIp=" + adressIp + "]";
	}
	
	
	

}
