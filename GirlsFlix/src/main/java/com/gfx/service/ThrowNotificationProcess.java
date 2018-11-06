package com.gfx.service;

import java.time.LocalDate;
import java.time.Period;

import com.gfx.Config;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;

public class ThrowNotificationProcess implements Runnable{
	
	private Serie serie;
	
	public ThrowNotificationProcess(Serie s) {
		this.serie = s;
	}
	
	
	/**
	 * If a new episode of the series will be on air soon (3 days or less currently) and has not been notified,
	 * send a notification to all enjoyers following this series. Browse the list of enjoyer to notify and
	 * start one thread per enjoyer. Each thread will take care of processing the notification process for the enjoyer.
	 * Called on a regular basis.
	 * 
	 * @param serie 	Series to notify
	 */
	public void run() {
		Period period = Period.between(LocalDate.now(), serie.getDateNextEpisodeOnAir());
		if (period.getDays() <= Config.nbDaysNotifBeforeDiff && !serie.isNextEpisodeHasBeenNotified()) {
			System.out.println("episode incoming for the serie " + serie.getTitle());
			for (Enjoyer enjoyer: serie.getEnjoyersToNotify()) {
				Thread throwNotif = new Thread(new ThrowNotificationToEnjoyer(enjoyer, serie));
				throwNotif.start();
			}
			serie.setNextEpisodeHasBeenNotified(true);
		}
	}

}
