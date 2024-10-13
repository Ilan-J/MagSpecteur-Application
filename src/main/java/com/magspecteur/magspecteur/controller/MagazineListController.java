package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Magazine;
import com.magspecteur.magspecteur.repository.MagazineRepository;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MagazineListController implements Initializable {

	@FXML
	public AnchorPane magazineListRoot;
	@FXML
	public TextField searchField;
	@FXML
	public Button searchButton;
	@FXML
	public FlowPane magazinesFlowPane;

	private final MagazineRepository magazineRepository;

	private static final ObjectProperty<Magazine> magazineProperty = new SimpleObjectProperty<>();

	public MagazineListController() {
		this.magazineRepository = new MagazineRepository();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		magazinesFlowPane.prefWidthProperty().bind(magazineListRoot.widthProperty().multiply(0.7));
		magazinesFlowPane.prefHeightProperty().bind(magazineListRoot.heightProperty());

		magazineProperty.addListener((observable, oldValue, newValue) -> {
			magazineListRoot.setVisible(newValue == null);
		});
	}

	@FXML
	public void onSearchFieldKeyPress(KeyEvent event) {
//		if (event.getCode() == KeyCode.ENTER)
//			searchMagazines();
	}

	@FXML
	public void onSearchButtonClick(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY)
			searchMagazines();
	}

	@FXML
	public void onMagazinePaneClick(MouseEvent event) {
		Pane magazinePane = (Pane) event.getSource();
		magazineProperty.setValue((Magazine) magazinePane.getUserData());
	}

	private void searchMagazines() {
		String search = searchField.getText();
		reloadMagazines(search);
	}

	private void reloadMagazines(String search) {
		ProgressIndicator indicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
		indicator.setPrefWidth(64);
		indicator.setPrefHeight(64);
		magazinesFlowPane.getChildren().setAll(indicator);
		magazinesFlowPane.setAlignment(Pos.CENTER);

		List<Magazine> magazines = magazineRepository.getAll(search);
		replaceMagazinePanes(magazines);
	}

	private void replaceMagazinePanes(List<Magazine> magazines) {
		magazinesFlowPane.getChildren().clear();
		magazinesFlowPane.setAlignment(Pos.TOP_LEFT);

		for (Magazine magazine : magazines) {
			magazinesFlowPane.getChildren().add(createMagazinePane(magazine));
		}
	}

	private Pane createMagazinePane(Magazine magazine) {
		Pane pane = new Pane();
		pane.prefWidthProperty().bind(magazinesFlowPane.widthProperty().multiply(0.12));
		pane.prefHeightProperty().bind(pane.widthProperty().multiply(1.6));
		pane.setStyle("-fx-border-color: Black");

		pane.setUserData(magazine);
		pane.setOnMouseClicked(this::onMagazinePaneClick);

		Label name = new Label(magazine.getName());
		name.maxWidthProperty().bind(pane.widthProperty().multiply(0.95));
		name.layoutXProperty().bind(
				pane.widthProperty().divide(2)
						.subtract(name.widthProperty().divide(2)));
		name.layoutYProperty().bind(pane.heightProperty().multiply(0.1));

		name.setFont(Font.font("System Bold", 16));
		name.setTextAlignment(TextAlignment.CENTER);
		name.setWrapText(true);

		Label author = new Label(magazine.getNumber().toString());
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

	public static ObjectProperty<Magazine> magazineProperty() {
		return magazineProperty;
	}
}
