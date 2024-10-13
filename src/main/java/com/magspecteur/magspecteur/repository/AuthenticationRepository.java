package com.magspecteur.magspecteur.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magspecteur.magspecteur.model.Login;
import com.magspecteur.magspecteur.model.Tokens;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

public class AuthenticationRepository {

	private final String BASE_URI = "http://192.168.1.120:80/auth";

	private final Logger _logger;
	private final ObjectMapper objectMapper;

	private final HttpClient httpClient;

	public AuthenticationRepository() {
		this._logger = Logger.getLogger(AuthenticationRepository.class.getName());

		this.objectMapper = new ObjectMapper();
		this.httpClient = HttpClient.newHttpClient();
	}

	public Tokens login(Login login) {
		String uri = BASE_URI + "/login";

		// Prepare the request
		String json;
		try {
			json = objectMapper.writeValueAsString(login);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
				.header("Content-Type", "application/json")
				.timeout(Duration.ofSeconds(10))
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				_logger.warning(String.valueOf(response.statusCode()));
				return null;
			}

			return objectMapper.readValue(response.body(), Tokens.class);

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}
}
