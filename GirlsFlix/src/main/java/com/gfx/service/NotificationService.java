package com.gfx.service;

import java.util.List;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Serie;

public class NotificationService {
	
		/**
		 * Called by the Scheduler on a regular basis.
		 * For each Serie, launch a Thread if a new episode will be on air soon
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
