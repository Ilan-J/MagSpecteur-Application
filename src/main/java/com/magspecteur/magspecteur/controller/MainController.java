package com.magspecteur.magspecteur.controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MainController {

	@FXML
	public AnchorPane rootPane;
	@FXML
	public ToolBar toolBar;
	@FXML
	public Label articlesLabel;
	@FXML
	public Label magazinesLabel;
	@FXML
	public Label publishersLabel;

	@FXML
	public AnchorPane articlesPane;
	@FXML
	public AnchorPane magazinesPane;
	@FXML
	public AnchorPane publishersPane;

	@FXML
	public void initialize(){
		ObservableValue<? extends Number> rootWidth = rootPane.widthProperty();
		ObservableValue<? extends Number> rootHeight = rootPane.heightProperty()
				.subtract(toolBar.widthProperty());

		toolBar.prefWidthProperty().bind(rootWidth);

		articlesPane.prefWidthProperty().bind(rootWidth);
		articlesPane.prefHeightProperty().bind(rootHeight);

		magazinesPane.prefWidthProperty().bind(rootWidth);
		magazinesPane.prefHeightProperty().bind(rootHeight);

		publishersPane.prefWidthProperty().bind(rootWidth);
		publishersPane.prefHeightProperty().bind(rootHeight);

		setSubject(articlesLabel.getText());
	}

	@FXML
	protected void onSubjectLabelClick(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			Label label = ((Label) event.getSource());
			setSubject(label.getText());

		}
	}

	private void setSubject(String subject) {
		if (Objects.equals(subject, articlesLabel.getText())) {
			articlesPane.setVisible(true);
			articlesLabel.setTextFill(Color.rgb(237, 53, 48));
		} else {
			articlesPane.setVisible(false);
			articlesLabel.setTextFill(Color.BLACK);
		}
		if (Objects.equals(subject, magazinesLabel.getText())) {
			magazinesPane.setVisible(true);
			magazinesLabel.setTextFill(Color.rgb(237, 53, 48));
		} else {
			magazinesPane.setVisible(false);
			magazinesLabel.setTextFill(Color.BLACK);
		}
		if (Objects.equals(subject, publishersLabel.getText())) {
			publishersPane.setVisible(true);
			publishersLabel.setTextFill(Color.rgb(237, 53, 48));
		} else {
			publishersPane.setVisible(false);
			publishersLabel.setTextFill(Color.BLACK);
		}
	}
}
