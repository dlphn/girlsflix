package com.gfx.service;


import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;

public class ThrowNotificationToEnjoyer implements Runnable {
	
	private Enjoyer enjoyerToNotify;
	private Serie serieNotified;
	
	public ThrowNotificationToEnjoyer (Enjoyer enjoyerToNotify, Serie serieNotified) {
		this.enjoyerToNotify = enjoyerToNotify;
		this.serieNotified = serieNotified;
	}
	
	public void run() {
		enjoyerToNotify.notifyNextEpisodeOnAirSoon(serieNotified);
	}

}
