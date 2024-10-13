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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.*;

public class MagazineDetailsController implements Initializable {

	@FXML
	public AnchorPane magazineDetailsRoot;

	@FXML
	public Label magazineNameAndNumber;
	@FXML
	public Label magazineReleaseDate;
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
			magazineDetailsRoot.setMaxHeight(
					newValue != null ? Region.USE_COMPUTED_SIZE : 0);
			if (newValue != null)
				fillMagazineDetails(
						newValue.getName(),
						newValue.getNumber(),
						newValue.getRelease(),
						newValue.getPublisher().getName(),
						fetchArticles(newValue.getId())
				);
		});
	}

	public void onBackButtonClick(MouseEvent event) {
		magazineProperty.set(null);
	}

	private List<Article> fetchArticles(Integer magazineId) {
		return articleRepository.getMagazineArticles(magazineId);
	}

	private void fillMagazineDetails(String name, Integer number, Date release, String publisherName, List<Article> articles) {
		magazineNameAndNumber.setText(String.format("%s - N°%s", name, number));
		Calendar calendar = new Calendar.Builder().setInstant(release).build();
		magazineReleaseDate.setText(String.format(
				"%s %s %s",
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE),
				calendar.get(Calendar.YEAR)
		));
		magazinePublisher.setText(publisherName);
		magazineArticles.getItems().setAll(articles);
	}

	public static ObjectProperty<Magazine> magazineProperty() {
		return magazineProperty;
	}
}
