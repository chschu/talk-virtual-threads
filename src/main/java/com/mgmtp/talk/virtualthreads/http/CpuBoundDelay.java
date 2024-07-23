package com.mgmtp.talk.virtualthreads.http;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CpuBoundDelay {

	private final int roundsPerSecond;

	public CpuBoundDelay() {
		// warm up
		delayRounds(10);

		// calibrate ~1 second of CPU-bound operation
		final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		int rounds = 0;
		final long startTime = threadMXBean.getCurrentThreadCpuTime();
		while (threadMXBean.getCurrentThreadCpuTime() - startTime < 1_000_000_000) {
			delayRounds(1);
			rounds++;
		}

		roundsPerSecond = rounds;
	}

	public void delaySeconds(final int seconds) {
		delayRounds(seconds * roundsPerSecond);
	}

	private void delayRounds(final int rounds) {
		for (int i = 0; i < rounds; i++) {
			BCrypt.hashpw("123456", BCrypt.gensalt(4));
		}
	}
}
