package com.magspecteur.magspecteur.util;

import com.magspecteur.magspecteur.MagspecteurApplication;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class IconLoader {

	private static final String[] ICONS_PATH = {
			"img/icon-magspecteur-16x16.png",
			"img/icon-magspecteur-32x32.png",
			"img/icon-magspecteur-64x64.png",
			"img/icon-magspecteur-128x128.png",
			"img/icon-magspecteur-256x256.png",
	};

	public static void loadIcons(Stage stage) {
		ObservableList<Image> icons = stage.getIcons();
		for (String path : ICONS_PATH) {
			Image icon = new Image(Objects.requireNonNull(
					MagspecteurApplication.class.getResourceAsStream(path)
			));
			icons.add(icon);
		}
	}
}
