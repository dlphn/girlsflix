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
		UserService.notifyNextEpisodeOnAirSoon(enjoyerToNotify, serieNotified);
		System.out.println("je vais déclencher la notification dans UserService pour la série " + serieNotified.getTitle() + " car l'enjoyer " + enjoyerToNotify.getLogin() + " a mis cette série dans ses favoris");
	}

}
