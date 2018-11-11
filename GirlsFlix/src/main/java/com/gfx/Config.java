package com.gfx;

/**
 * Config class with the credentials to access the API 
 * notifyDaysBefore is the number of days (here 3) before a new episode release
 * We will notify users of the episode coming soon 'notifyDaysBefore' days before its release
 */
public class Config {
	public static final String apiUrl = "https://api.themoviedb.org/3/";
	public static final String apiCategory = "tv/";
	public static final String apiFull = apiUrl + apiCategory;
	public static final String lang = "fr-FR";
	public static final int notifyXDaysBefore = 3;
}
