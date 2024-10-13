package com.magspecteur.magspecteur.view;

import com.magspecteur.magspecteur.model.Magazine;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.AnchorPane;

public class MagazineDetails extends AnchorPane {
	private static ObjectProperty<Magazine> magazineProperty;

	public static ObjectProperty<Magazine> magazineProperty() {
		return magazineProperty;
	}

	public static Magazine getMagazine() {
		return magazineProperty.get();
	}

	public static void setMagazine(Magazine magazine) {
		magazineProperty.setValue(magazine);
	}
}
