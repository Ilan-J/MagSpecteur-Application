package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Magazine;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MagazineController implements Initializable {
	
	@FXML
	public AnchorPane magazineViewRoot;
	@FXML
	public AnchorPane magazineList;
//	@FXML
//	private MagazineListController magazineListController;
	@FXML
	public AnchorPane magazineDetails;
//	@FXML
//	private MagazineDetailsController magazineDetailsController;

	private final ListProperty<Magazine> magazineListProperty = new SimpleListProperty<>();
	private final ObjectProperty<Magazine> magazineObjectProperty = new SimpleObjectProperty<>();

	public MagazineController() {
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		magazineList.prefWidthProperty().bind(magazineViewRoot.widthProperty());
		magazineList.prefHeightProperty().bind(magazineViewRoot.heightProperty());

		magazineDetails.prefWidthProperty().bind(magazineViewRoot.widthProperty());
		magazineDetails.prefHeightProperty().bind(magazineViewRoot.heightProperty());

		MagazineListController.magazineProperty().bindBidirectional(magazineObjectProperty);
		MagazineDetailsController.magazineProperty().bindBidirectional(magazineObjectProperty);
		magazineObjectProperty.set(null);
	}
}
