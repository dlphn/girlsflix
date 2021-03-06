package com.gfx.service;


import com.gfx.domain.series.Serie;

/**
 * Runnable lauched in ThrowNotificationsProcess for a serie S.
 * Adds notification to the user enjoyerToNotify if he/she weren't already notified for the serie S.
 */
public class ThrowNotificationToEnjoyer implements Runnable {
	
	private	String enjoyerToNotify;
	private Serie serieNotified;
	
	public ThrowNotificationToEnjoyer(String enjoyerToNotify, Serie serieNotified) {
		this.enjoyerToNotify = enjoyerToNotify;
		this.serieNotified = serieNotified;
	}
	
	public void run() {
		UserService.notifyNextEpisodeOnAirSoon(enjoyerToNotify, serieNotified);
	}

}