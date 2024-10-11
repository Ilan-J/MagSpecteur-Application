module com.magspecteur.magspecteur {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.magspecteur.magspecteur to javafx.fxml;
	exports com.magspecteur.magspecteur;
}
