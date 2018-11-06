package com.gfx.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.gfx.Config;

public class Scheduler extends Thread{
	private static int count = 0;
	private SerieService serieService;
	private SerieFactory serieFactory;
	private boolean firstTimerIteration = true;
	
	public Scheduler() {
		this.start();
	}
	
	public void run() {
		serieService = new SerieService();
		serieFactory = new SerieFactory();
		Timer timer = new Timer();
		Date initDate = convertLocalDateTimeToDate(Config.initDateScheduler);
		//launch the timer at the date initDate and excute the method run of the ReccurentTask class every Config.internalForScheduler milliseconds
		timer.scheduleAtFixedRate(new ReccurentTask(), initDate, Config.intervalForScheduler);
	}
	
	public Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public class ReccurentTask extends TimerTask{
		
		
		public void run() {
			count++;
			System.out.println("itération du scheduler : " + count);
			if(firstTimerIteration) {
				//execution of the notification process
				SerieService.launchGlobalNotificationProcess();
				firstTimerIteration = false;
			}
			else {
				//call to the API and update of the Database, take a lot of time
				serieService.init();
				//update of the series, seasons, and episodes objects
				serieFactory.updateData();
				//execution of the notification process
				SerieService.launchGlobalNotificationProcess();
			}
		}
	}

}
