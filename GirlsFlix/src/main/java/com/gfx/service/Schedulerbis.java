package com.gfx.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.gfx.Config;
import com.gfx.domain.series.Data;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;

public class Schedulerbis extends Thread{
	private static int count = 0;
	private SerieService serieService;
	private SerieFactory serieFactory;
	private boolean firstTimerIteration = true;
	
	public Schedulerbis(SerieFactory serieFactory) {
		this.start();
	}
	
	public void run() {
		serieService = new SerieService();
		
		/*Enjoyer e1 = UserDB.getUser("Sophie");
		Enjoyer e2 = UserDB.getUser("Test1");
		Enjoyer e3 = UserDB.getUser("Test2");

		Serie s1 = Data.getById(1416);
		Serie s2 = Data.getById(456);
		Serie s3 = Data.getById(60735);
		Serie s4 = Data.getById(1423);
		
		Map<String, Boolean> enjoyerS1 = new HashMap<String, Boolean>();
		Map<String, Boolean> enjoyerS2 = new HashMap<String, Boolean>();
		Map<String, Boolean> enjoyerS3 = new HashMap<String, Boolean>();
		Map<String, Boolean> enjoyerS4 = new HashMap<String, Boolean>();
		enjoyerS1.put(e1.getLogin(), false);
		enjoyerS1.put(e2.getLogin(), false);
		enjoyerS2.put(e1.getLogin(), false);
		enjoyerS3.put(e2.getLogin(), false);
		enjoyerS4.put(e3.getLogin(), false);
		
		s1.setEnjoyersToNotify(enjoyerS1);
		s2.setEnjoyersToNotify(enjoyerS2);
		s3.setEnjoyersToNotify(enjoyerS3);
		s4.setEnjoyersToNotify(enjoyerS4);*/
		
		Timer timer = new Timer();
		Date initDate = convertLocalDateTimeToDate(Config.initDateScheduler);
		//launch the timer at the date initDate and execute the method run of the ReccurentTask class every Config.internalForScheduler milliseconds
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
				serieFactory.initData();
				//execution of the notification process
				SerieService.launchGlobalNotificationProcess();
			}
			System.out.println("fin de l'itération " + count);
		}
	}

}
