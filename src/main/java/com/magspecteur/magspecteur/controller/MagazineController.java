package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Magazine;
import com.magspecteur.magspecteur.repository.MagazineRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class MagazineController {

	private MagazineRepository magazineRepository;

	@FXML
	public AnchorPane magazineViewRoot;

	@FXML
	public TextField filterField;
	@FXML
	public Button filterButton;

	@FXML
	public FlowPane magazineFlowPane;

	@FXML
	public void initialize() {

		magazineFlowPane.prefWidthProperty().bind(magazineViewRoot.widthProperty().multiply(0.7));
		magazineFlowPane.prefHeightProperty().bind(magazineViewRoot.heightProperty());
	}

	@FXML
	public void onSearchFieldKeyPress(KeyEvent event) {
		
	}

	@FXML
	public void onSearchButtonClick(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY)
			searchMagazines();
	}

	private void searchMagazines() {
		String search = filterField.getText();
		List<Magazine> magazines = magazineRepository.getSearch(search);

		replaceMagazinePanes(magazines);
	}

	private void replaceMagazinePanes(List<Magazine> magazines) {
		List<Pane> magazinePanes = new ArrayList<>();
		magazines.forEach(magazine -> magazinePanes.add(createMagazinePane(magazine)));

		magazineFlowPane.getChildren().setAll(magazinePanes);
	}

	private Pane createMagazinePane(Magazine magazine) {
		Pane pane = new Pane();
		pane.prefWidthProperty().bind(magazineFlowPane.widthProperty().multiply(0.12));
		pane.prefHeightProperty().bind(pane.widthProperty().multiply(2.7));
		pane.setStyle("-fx-border-color: Black");

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
}
