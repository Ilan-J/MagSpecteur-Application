package com.magspecteur.magspecteur.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magspecteur.magspecteur.model.Magazine;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MagazineRepository {
	private final String BASE_URI = "http://magspecteur.com:80/api/magazines";

	private final Logger _logger;
	private final ObjectMapper objectMapper;

	private final HttpClient httpClient;

	public MagazineRepository() {
		this._logger = Logger.getLogger(MagazineRepository.class.getName());

		this.objectMapper = new ObjectMapper();
		this.httpClient = HttpClient.newHttpClient();
	}

	public List<Magazine> getAll(String search) {
		String uri = BASE_URI;
		if (search != null)
			uri += "?s=" + search;

		// Prepare the request
		HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
				.header("Content-Type", "application/json")
				.timeout(Duration.ofSeconds(10))
				.GET()
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200)
				return null;

			Magazine[] magazines = objectMapper.readValue(response.body(), Magazine[].class);
			return Arrays.stream(magazines).toList();

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}

	public Magazine get(Integer id) {
		// Prepare the request
		HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URI + "/" + id))
				.header("Content-Type", "application/json")
				.timeout(Duration.ofSeconds(10))
				.GET()
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200)
				return null;

			return objectMapper.readValue(response.body(), Magazine.class);

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}

	public Magazine post(Magazine magazine) {
		// Prepare the request
		String json;
		try {
			json = objectMapper.writeValueAsString(magazine);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URI))
				.header("Content-Type", "application/json")
				.timeout(Duration.ofSeconds(10))
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 201)
				return null;

			return objectMapper.readValue(response.body(), Magazine.class);

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}

	public Magazine put(Magazine magazine) {
		// Prepare the request
		String json;
		try {
			json = objectMapper.writeValueAsString(magazine);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URI + "/" + magazine.getId()))
				.header("Content-Type", "application/json")
				.timeout(Duration.ofSeconds(10))
				.PUT(HttpRequest.BodyPublishers.ofString(json))
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 204)
				return null;

			return magazine;

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}

	public Boolean delete(Integer id) {
		// Prepare the request
		HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URI + "/" + id))
				.header("Content-Type", "application/json")
				.timeout(Duration.ofSeconds(10))
				.DELETE()
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 204)
				return null;

			return Boolean.TRUE;

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}
}
