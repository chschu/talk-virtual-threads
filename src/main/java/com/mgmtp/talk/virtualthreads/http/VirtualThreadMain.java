package com.mgmtp.talk.virtualthreads.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Stopwatch;

public class VirtualThreadMain {

	public static void main(final String[] args) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		try (final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
			for (int i = 0; i < 24; i++) {
				executorService.submit(HttpRequestTask.DEFAULT);
			}
		}
		System.out.println(stopwatch.elapsed().toMillis() + " ms");
	}
}
