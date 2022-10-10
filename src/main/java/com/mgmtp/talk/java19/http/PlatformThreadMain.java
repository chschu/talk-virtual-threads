package com.mgmtp.talk.java19.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Stopwatch;

public class PlatformThreadMain {

	public static void main(final String[] args) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		try (final ExecutorService executorService = Executors.newFixedThreadPool(8)) {
			for (int i = 0; i < 24; i++) {
				executorService.submit(HttpRequestTask.INSTANCE);
			}
		}
		System.out.println(stopwatch.elapsed().toMillis() + " ms");
	}
}
