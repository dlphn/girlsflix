package com.gfx.service;

import java.util.List;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Serie;

public class NotificationService {
		/**
		 * this method is called by the Scheduler on a regular basis.
		 * for each Serie, launch a Thread if it has a date for the next episode on air not null
		 */
		
		public synchronized static void launchGlobalNotificationProcess() {
			List<Serie> listSerie = Data.getListSeries();
			for (Serie s : listSerie) {
				if (s.getDateNextEpisodeOnAir() != null) {
					Thread thread = new Thread(new ThrowNotificationProcess(s));
					thread.start();
				}
			}
		}
}
