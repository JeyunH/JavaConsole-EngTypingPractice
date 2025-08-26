package kr.ac.kopo.service;

public class LogServiceFactory {
	
	public static LogService getLogService() {
		return LogService.getInstance();
	}
}
