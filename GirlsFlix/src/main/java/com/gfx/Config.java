package com.gfx;

import java.time.LocalDateTime;
import java.time.Month;

public class Config {
	public static final String apiUrl = "https://api.themoviedb.org/3/";
	public static final String apiCategory = "tv/";
	public static final String apiFull = apiUrl + apiCategory;
	public static final String lang = "fr-FR";
	public static final int nbDaysNotifBeforeDiff = 3;
	public static final LocalDateTime initDateScheduler = LocalDateTime.now();
	//public static final LocalDateTime initDateScheduler = LocalDateTime.of(2018, Month.NOVEMBER, 5, 22, 36, 00);
	public static final long intervalForScheduler = 1 * 30 * 10000; // #min * #sec/min * #ms/sec
}
