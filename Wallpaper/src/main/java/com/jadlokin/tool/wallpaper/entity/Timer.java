package com.jadlokin.tool.wallpaper.entity;

class Timer extends java.util.Timer{

	void schedule(Runnable task, long period) {
//		task.run();
		super.schedule(new TimerTask(task), (long) 0, period);
	}

}
