package com.gfx.service;


import com.gfx.domain.series.Serie;

public class ThrowNotificationToEnjoyer implements Runnable {
	
	private	String enjoyerToNotify;
	private Serie serieNotified;
	
	public ThrowNotificationToEnjoyer (String enjoyerToNotify, Serie serieNotified) {
		this.enjoyerToNotify = enjoyerToNotify;
		this.serieNotified = serieNotified;
	}
	
	public void run() {
		UserService.notifyNextEpisodeOnAirSoon(enjoyerToNotify, serieNotified);
	}

}