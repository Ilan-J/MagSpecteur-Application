package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Article;
import com.magspecteur.magspecteur.model.Theme;
import com.magspecteur.magspecteur.repository.ArticleRepository;
import com.magspecteur.magspecteur.repository.ThemeRepository;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {

	private final ArticleRepository articleRepository;

	private final ThemeRepository themeRepository;

	@FXML
	public AnchorPane articleViewRoot;
	@FXML
	public TextField searchField;
	@FXML
	public Button searchButton;
	@FXML
	public ChoiceBox<Theme> themeChoiceBox;
	@FXML
	public FlowPane articleList;

	public ArticleController() {
		this.articleRepository = new ArticleRepository();
		this.themeRepository = new ThemeRepository();
	}

	@FXML
	public void initialize() {

		articleList.prefWidthProperty().bind(articleViewRoot.widthProperty().multiply(0.7));
		articleList.prefHeightProperty().bind(articleViewRoot.heightProperty());


//		Article articlePatate = new Article("Potato", "PotatoMan", 1, themeCuisine);
//		Article articleSalon = new Article("Un salon de rêve", "Valérie damido", 9999, themeDecoration);

//		refresh();
	}

	@FXML
	public void onSearchFieldKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			searchArticle();
	}

	@FXML
	public void onSearchButtonClick(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY)
			searchArticle();
	}

	private void searchArticle() {
		String search = searchField.getText();
	}

	private void refresh() {
		List<Theme> themes = themeRepository.getAll();
		themeChoiceBox.getItems().setAll(themes);

		List<Pane> articlePanes = new ArrayList<>();
		List<Article> articles = articleRepository.getAll();
		articles.forEach(article -> articlePanes.add(createArticlePane(article)));

		articleList.getChildren().setAll(articlePanes);
	}

	private Pane createArticlePane(Article article) {
		Pane pane = new Pane();
		pane.prefWidthProperty().bind(articleList.widthProperty().multiply(0.12));
		pane.prefHeightProperty().bind(pane.widthProperty().multiply(2.7));
		pane.setStyle("-fx-border-color: Black");

		Label name = new Label(article.getName());
		name.maxWidthProperty().bind(pane.widthProperty().multiply(0.95));
		name.layoutXProperty().bind(
				pane.widthProperty().divide(2)
						.subtract(name.widthProperty().divide(2)));
		name.layoutYProperty().bind(pane.heightProperty().multiply(0.1));

		name.setFont(Font.font("System Bold", 16));
		name.setTextAlignment(TextAlignment.CENTER);
		name.setWrapText(true);

		Label author = new Label(article.getAuthor());
		author.maxWidthProperty().bind(pane.widthProperty().multiply(0.95));
		author.layoutXProperty().bind(
				pane.widthProperty().divide(2)
						.subtract(author.widthProperty().divide(2)));
		author.layoutYProperty().bind(pane.heightProperty().multiply(0.9));

		author.setFont(Font.font("System Italic", 12));
		name.setTextAlignment(TextAlignment.CENTER);

		pane.getChildren().addAll(name, author);
		return pane;
	}
}
