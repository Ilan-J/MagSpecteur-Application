module com.magspecteur.magspecteur {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.net.http;
	requires java.logging;

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;


	opens com.magspecteur.magspecteur to javafx.fxml;
	exports com.magspecteur.magspecteur;
	exports com.magspecteur.magspecteur.controller;
	exports com.magspecteur.magspecteur.model;
	opens com.magspecteur.magspecteur.controller to javafx.fxml;
}
