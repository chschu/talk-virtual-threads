package com.mgmtp.talk.virtualthreads.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestTask implements Callable<Void> {

	private final CpuBoundDelay cpuBoundDelay;

	public HttpRequestTask(final CpuBoundDelay cpuBoundDelay) {
		this.cpuBoundDelay = cpuBoundDelay;
	}

	@Override
	public Void call() throws IOException {
		// ~9 seconds of I/O-bound operation
		// not using java.net.http.HttpClient here, because it has its own executor
		final URL url = URI.create("https://deelay.dev.dnup.de/9000/https://example.org/").toURL();
		final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		readResponse(connection);

		// ~1 second of CPU-bound operation
		cpuBoundDelay.delaySeconds(1);

		return null;
	}

	private static void readResponse(final HttpsURLConnection connection) throws IOException {
		try (final InputStream inputStream = connection.getInputStream()) {
			inputStream.readAllBytes();
		}
	}
}
