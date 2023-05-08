package com.mgmtp.talk.virtualthreads.many;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Stopwatch;

public class VirtualThreadMain {

	public static void main(final String[] args) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		try (final ExecutorService executorService = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory())) {
			for (int i = 0; i < 50_000; i++) {
				executorService.submit(DelayTask.INSTANCE);
			}
			System.out.println("Submit finished: " + stopwatch.elapsed().toMillis() + " ms");
		}
		System.out.println("Execute finished: " + stopwatch.elapsed().toMillis() + " ms");
	}
}
