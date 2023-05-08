package com.mgmtp.talk.virtualthreads.many;

import java.util.concurrent.Callable;

public enum DelayTask implements Callable<Void> {
	INSTANCE;

	@Override
	public Void call() throws InterruptedException {
		Thread.sleep(10000);
		return null;
	}
}
