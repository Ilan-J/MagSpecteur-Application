package com.magspecteur.magspecteur.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magspecteur.magspecteur.model.Theme;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ThemeRepository {
	private final String BASE_URI = "http://192.168.1.120:80/api/themes";

	private final Logger _logger;
	private final ObjectMapper objectMapper;

	public ThemeRepository() {
		this._logger = Logger.getLogger(ThemeRepository.class.getName());

		this.objectMapper = new ObjectMapper();
	}

	public List<Theme> getAll() {
		// Create an HttpClient instance
		HttpClient httpClient = HttpClient.newHttpClient();

		// Prepare the request
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URI))
				.header("Content-Type", "application/json")
				.GET()
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200)
				return null;

			Theme[] themes = objectMapper.readValue(response.body(), Theme[].class);
			return Arrays.stream(themes).toList();

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}
}
