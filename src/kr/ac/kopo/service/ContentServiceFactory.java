package kr.ac.kopo.service;

public class ContentServiceFactory {
	
	public static ContentService getContentService() {
		return ContentService.getInstance();
	}
}
