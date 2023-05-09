package com.mgmtp.talk.virtualthreads.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.security.crypto.bcrypt.BCrypt;

public enum HttpRequestTask implements Callable<Void> {
	DEFAULT(false), PINNED(true);

	private final boolean pinned;

	HttpRequestTask(final boolean pinned) {
		this.pinned = pinned;
	}

	@Override
	public Void call() throws IOException {
		// ~9 seconds of I/O-bound operation
		// not using java.net.http.HttpClient here, because it has its own executor
		final URL url = URI.create("https://deelay.dev.dnup.de/9000/https://example.org/").toURL();
		final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		if (pinned) {
			synchronized (new Object()) {
				readResponse(connection);
			}
		} else {
			readResponse(connection);
		}

		// ~1 second of CPU-bound operation (on my machine - adjust as needed)
		BCrypt.hashpw("123456", BCrypt.gensalt(13));

		return null;
	}

	private static void readResponse(HttpsURLConnection connection) throws IOException {
		try (final InputStream inputStream = connection.getInputStream()) {
			inputStream.readAllBytes();
		}
	}
}
