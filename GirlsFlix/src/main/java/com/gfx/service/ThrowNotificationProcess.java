package com.gfx.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map.Entry;
import java.util.Set;

import com.gfx.Config;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;

public class ThrowNotificationProcess implements Runnable{
	
	private Serie serie;
	
	public ThrowNotificationProcess(Serie s) {
		this.serie = s;
	}
	
	
	/**
	 * If a new episode of the serie will be on air soon (3 days or less currently) and has not been notified,
	 * send a notification to all enjoyers following this serie. Browse the list of enjoyer to notify and
	 * start one thread per enjoyer. Each thread will take care of processing the notification process for the enjoyer.
	 * 
	 * @param serie 	Series to notify
	 */
	public void run() {
		Period period = Period.between(LocalDate.now(), serie.getDateNextEpisodeOnAir());
		if(period.getDays() <= Config.nbDaysNotifBeforeDiff && period.getDays() >= 0) {
			System.out.println("episode incoming for the serie : " + serie.getTitle());
			for(Entry<String, Boolean> loginEnjoyer: serie.getEnjoyersToNotify().entrySet()){
				if(!loginEnjoyer.getValue()) {
					Thread throwNotif = new Thread(new ThrowNotificationToEnjoyer(loginEnjoyer.getKey(), serie));
					throwNotif.start();
					serie.setEnjoyerAsNotified(loginEnjoyer.getKey());
				}
			}
		}
	}

}
