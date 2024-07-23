package com.mgmtp.talk.virtualthreads.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Stopwatch;

public class PinnedVirtualThreadMain {

	public static void main(final String[] args) {
		final int cores = Runtime.getRuntime().availableProcessors();
		final CpuBoundDelay cpuBoundDelay = new CpuBoundDelay();
		final PinnedHttpRequestTask task = new PinnedHttpRequestTask(cpuBoundDelay);

		final Stopwatch stopwatch = Stopwatch.createStarted();
		try (final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
			for (int i = 0; i < 3 * cores; i++) {
				executorService.submit(task);
			}
		}

		System.out.println(stopwatch.elapsed().toMillis() + " ms");
	}
}
