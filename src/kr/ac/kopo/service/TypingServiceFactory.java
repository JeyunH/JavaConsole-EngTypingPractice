package kr.ac.kopo.service;

public class TypingServiceFactory {
	
	public static TypingService getUserService() {
		return TypingService.getInstance();
	}

}
