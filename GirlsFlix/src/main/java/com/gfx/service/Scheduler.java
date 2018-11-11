package com.gfx.service;

/**
 * Handle update of series information and notifications process on a regular basis
 */
public class Scheduler {
	
	private static SerieService serieService = new SerieService();
	private static int count = 0;
	private static boolean firstTimerIteration = true;
	
	/**
	 * If it is the very first iteration, Data is already built so we just launch the notification process.
	 * For other iterations, we call the tmdb api to update the information we have on series in Mongo, 
	 * then we update Data and launch the notification process on the updated series.
	 */
	public void taskToExecute() {
		count ++;
		System.out.println("Itération du scheduler : " + count);
		
		if (firstTimerIteration) {
			//execution of the notification process
			NotificationService.launchGlobalNotificationProcess();
			firstTimerIteration = false;
		} else {
			//call to the API and update of the Database, take a lot of time
			serieService.init();
			//update of the series, seasons, and episodes objects
			new SerieFactory("update");
			//execution of the notification process
			NotificationService.launchGlobalNotificationProcess();
		}
		System.out.println("Fin de l'itération " + count);
	}
}
