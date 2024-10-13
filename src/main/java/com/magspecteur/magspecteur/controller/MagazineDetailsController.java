package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Article;
import com.magspecteur.magspecteur.model.Magazine;
import com.magspecteur.magspecteur.repository.ArticleRepository;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MagazineDetailsController implements Initializable {

	@FXML
	public AnchorPane magazineDetailsRoot;

	@FXML
	public Label magazineName;
	@FXML
	public Label magazineNumberAndDate;
	@FXML
	public Label magazinePublisher;
	@FXML
	public ListView<Article> magazineArticles;

	private static final ObjectProperty<Magazine> magazineProperty = new SimpleObjectProperty<>();

	private final ArticleRepository articleRepository;

	public MagazineDetailsController() {
		this.articleRepository = new ArticleRepository();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		magazineProperty.addListener((observable, oldValue, newValue) -> {
			magazineDetailsRoot.setVisible(newValue != null);
			if (newValue != null) {

			}
		});
	}

	private List<Article> fetchArticles(Integer magazineId) {
		return articleRepository.getMagazineArticles(magazineId);
	}

	private void fillMagazineDetails(String name, Integer number, Date release, String publisherName, List<Article> articles) {
		magazineName.setText(name);
		magazineNumberAndDate.setText(String.format("%s - %s", number, release));
		magazinePublisher.setText(publisherName);
		magazineArticles.getItems().setAll(articles);
	}

	public static ObjectProperty<Magazine> magazineProperty() {
		return magazineProperty;
	}
}
