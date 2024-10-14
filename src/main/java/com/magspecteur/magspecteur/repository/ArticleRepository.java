package com.magspecteur.magspecteur.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magspecteur.magspecteur.model.Article;
import com.magspecteur.magspecteur.model.Magazine;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ArticleRepository {
	private final String BASE_URI = "http://magspecteur.com:80/api";

	private final Logger _logger;
	private final ObjectMapper objectMapper;

	private final HttpClient httpClient;

	public ArticleRepository() {
		this._logger = Logger.getLogger(ArticleRepository.class.getName());
		this.httpClient = HttpClient.newHttpClient();

		this.objectMapper = new ObjectMapper();
	}

	public List<Article> getAll() {
		String uri = String.format("%s/articles", BASE_URI);

		// Prepare the request
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("Content-Type", "application/json")
				.GET()
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200)
				return null;

			Article[] articles = objectMapper.readValue(response.body(), Article[].class);
			return Arrays.stream(articles).toList();

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}

	public List<Article> getMagazineArticles(Integer magazineId) {
		String uri = String.format("%s/magazines/%s/articles", BASE_URI, magazineId);

		// Prepare the request
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("Content-Type", "application/json")
				.GET()
				.build();

		try {
			// Send the request and handle the response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200)
				return null;

			Article[] articles = objectMapper.readValue(response.body(), Article[].class);
			return Arrays.stream(articles).toList();

		} catch (IOException | InterruptedException e) {

			_logger.severe(e.getMessage());
			return null;
		}
	}
}
