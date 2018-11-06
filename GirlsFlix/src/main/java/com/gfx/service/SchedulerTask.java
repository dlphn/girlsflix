package com.gfx.service;

import java.util.Date;
import java.util.TimerTask;

public class SchedulerTask extends TimerTask{
	
	public void run() {
		System.out.println("demoTimerTask2 running at: " + new Date(this.scheduledExecutionTime()));
	}

}
