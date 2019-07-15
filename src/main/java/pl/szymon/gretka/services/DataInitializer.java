package pl.szymon.gretka.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import pl.szymon.gretka.entity.User;
import pl.szymon.gretka.enums.ROLES;
import pl.szymon.gretka.services.qualifiers.Users;

@Startup
@Singleton
public class DataInitializer {

	List<User> listOfUsers = new ArrayList<>();
	
	@PostConstruct
	public void initializeUsers() {

		listOfUsers.add(new User("Big", "Lebowski", ROLES.ADMIN, "PL", "1", "192", "pass1", "user1"));
		listOfUsers.add(new User("Drugi", "Manager", ROLES.STATISTIC_MANAGER, "PL", "2", "193", "pass2", "user2"));
		listOfUsers.add(new User("Abd", "xxx", ROLES.STATISTIC_VIEWER, "EN", "3", "195", "pass3", "user3"));
		listOfUsers.add(new User("Rr", "y", null, "EN", "3", "195", "pass4", "user4"));

	}
	
	public List<User> getListOfUsers() {
		return listOfUsers;
	}
	
}
