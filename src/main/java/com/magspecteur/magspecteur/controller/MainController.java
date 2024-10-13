package com.magspecteur.magspecteur.controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MainController {

	@FXML
	public AnchorPane rootPane;
	@FXML
	public ToolBar toolBar;
	@FXML
	public HBox menuBar;
	@FXML
	public Label articlesLabel;
	@FXML
	public Label magazinesLabel;
	@FXML
	public Label publishersLabel;
	@FXML
	public HBox authenticationBox;
	@FXML
	public Button connectionButton;

	@FXML
	public AnchorPane articlesPane;
	@FXML
	public AnchorPane magazinesPane;
	@FXML
	public AnchorPane publishersPane;
	@FXML
	public AnchorPane authenticationPane;

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

		authenticationPane.prefWidthProperty().bind(rootWidth);
		authenticationPane.prefHeightProperty().bind(rootHeight);

		authenticationBox.prefWidthProperty().bind(
				rootPane.widthProperty().subtract(menuBar.widthProperty()).subtract(20));

		setSubject(magazinesLabel.getText());
	}

	@FXML
	protected void onSubjectLabelClick(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			Labeled source = (Labeled) event.getSource();
			setSubject(source.getText());
		}
	}

	private void setSubject(String subject) {
		articlesLabel.setTextFill(subject.equals(articlesLabel.getText()) ? Color.rgb(237, 53, 48) : Color.BLACK);
		magazinesLabel.setTextFill(subject.equals(magazinesLabel.getText()) ? Color.rgb(237, 53, 48) : Color.BLACK);
		publishersLabel.setTextFill(subject.equals(publishersLabel.getText()) ? Color.rgb(237, 53, 48) : Color.BLACK);

		articlesPane.setVisible(subject.equals(articlesLabel.getText()));
		magazinesPane.setVisible(subject.equals(magazinesLabel.getText()));
		publishersPane.setVisible(subject.equals(publishersLabel.getText()));
		authenticationPane.setVisible(subject.equals(connectionButton.getText()));
	}
}
