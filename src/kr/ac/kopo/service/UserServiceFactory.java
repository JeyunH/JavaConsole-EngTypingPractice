package kr.ac.kopo.service;

public class UserServiceFactory {
	
	public static UserService getUserService() {
		return UserService.getInstance();
	}
	
}
