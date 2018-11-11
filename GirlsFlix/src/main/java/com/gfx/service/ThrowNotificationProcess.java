package com.gfx.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map.Entry;

import com.gfx.Config;
import com.gfx.domain.series.Serie;

public class ThrowNotificationProcess implements Runnable {
	
	private Serie serie;
	
	public ThrowNotificationProcess(Serie s) {
		this.serie = s;
	}
	
	/**
	 * If a new episode of the serie will be on air soon (3 days or less currently, specified in Config file) and has not been notified,
	 * sends a notification to all enjoyers following this serie. Browses the list of enjoyers to notify and
	 * starts one thread per enjoyer. Each thread will take care of processing the notification process for the enjoyer.
	 * 
	 * @param serie 	Series to notify
	 */
	public void run() {
		if (serie.getDateNextEpisodeOnAir() != null) {
			Period period = Period.between(LocalDate.now(), serie.getDateNextEpisodeOnAir());
			if (period.getDays() <= Config.notifyXDaysBefore && period.getDays() >= 0) {
				if (serie.getEnjoyersToNotify() != null) {
					for (Entry<String, Boolean> enjoyerToNotify : serie.getEnjoyersToNotify().entrySet()){
						if (enjoyerToNotify.getValue() == false) {
							Thread throwNotif = new Thread(new ThrowNotificationToEnjoyer(enjoyerToNotify.getKey(), serie));
							throwNotif.start();
						}
					}
				}
			}
		}
	}

}
