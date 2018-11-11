package com.gfx.service;

public class Scheduler {
	private static SerieService serieService = new SerieService();
	private static SerieFactory serieFactory = new SerieFactory();
	private static int count = 0;
	private static boolean firstTimerIteration = true;
	
	public void taskToExecute() {
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
			serieFactory = new SerieFactory("update");
			//execution of the notification process
			SerieService.launchGlobalNotificationProcess();
		}
		System.out.println("fin de l'itération " + count);
	}
}