package com.jadlokin.tool.wallpaper.entity;

public class TimerTask extends java.util.TimerTask {

	private Runnable task;

	TimerTask(Runnable task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.run();
	}
}
