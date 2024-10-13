package com.magspecteur.magspecteur;

import com.magspecteur.magspecteur.util.IconLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MagspecteurApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MagspecteurApplication.class.getResource("view/main-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("MagSpecteur");
		IconLoader.loadIcons(stage);
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
