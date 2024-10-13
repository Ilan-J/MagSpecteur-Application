package com.magspecteur.magspecteur.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class SearchBarController {

	@FXML
	public HBox searchBar;
	@FXML
	public TextField searchField;

	@FXML
	protected void onSearchFieldKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {

		}
	}

	@FXML
	protected void onSearchButtonClick() {
		String search = searchField.getText();
		System.out.println(search);
	}
}
