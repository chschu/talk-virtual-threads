package com.mgmtp.talk.java19.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class ThreadCreationBenchmark {

	//@Benchmark
	@BenchmarkMode(Mode.Throughput)
	public void platformThread() {
		Thread.ofPlatform().start(() -> {
		});
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	public void virtualThread() {
		Thread.ofVirtual().start(() -> {
		});
	}
}
