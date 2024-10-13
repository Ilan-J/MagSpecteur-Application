package com.magspecteur.magspecteur.controller;

import com.magspecteur.magspecteur.model.Login;
import com.magspecteur.magspecteur.model.Tokens;
import com.magspecteur.magspecteur.repository.AuthenticationRepository;
import com.magspecteur.magspecteur.util.JWTUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AuthenticationController {

	@FXML
	public Label info;
	@FXML
	public TextField username;
	@FXML
	public PasswordField password;

	private final AuthenticationRepository authenticationRepository;

	public AuthenticationController() {
		this.authenticationRepository = new AuthenticationRepository();
	}

	@FXML
	public void onConnectionButtonClick(MouseEvent event) {
		Login login = new Login(username.getText(), password.getText());

		Tokens tokens = authenticationRepository.login(login);

		info.setVisible(true);
		if (tokens == null) {
			info.setText("Identifiants incorrectes");
			info.setTextFill(Color.RED);
		} else {
			info.setText("Access Token: " + tokens.getAccessToken());
			info.setTextFill(Color.BLACK);

			JWTUtil.setAccessToken(tokens.getAccessToken());
			JWTUtil.setRefreshToken(tokens.getRefreshToken());
		}
	}
}
