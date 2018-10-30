package com.gfx.helper;

public class Config {
	private final String apiUrl = "https://api.themoviedb.org/3/";
	private final String apiCategory = "tv/";
	private final String lang = "en-US";
	private static final int nbDaysNotifBeforeDiff = 3;
	
	public String getApiUrl() {
		return apiUrl;
	}
	
	public String getApiCategory() {
		return apiCategory;
	}
	
	public String getApiUrlFull() {
		return apiUrl + apiCategory;
	}

	public String getLang() {
		return lang;
	}

	public static int getNbdaysnotifbeforediff() {
		return nbDaysNotifBeforeDiff;
	}
	
	
	
}
