package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Magazine;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MagazineDetailsController implements Initializable {

	@FXML
	public AnchorPane magazineDetailsRoot;
	@FXML
	public Pane magazineDetails;

	private static final ObjectProperty<Magazine> magazineProperty = new SimpleObjectProperty<>();

	public MagazineDetailsController() {}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		magazineProperty.addListener((observable, oldValue, newValue) -> {
			magazineDetailsRoot.setVisible(newValue != null);
		});
	}

	public static ObjectProperty<Magazine> magazineProperty() {
		return magazineProperty;
	}
}
