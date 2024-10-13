package com.magspecteur.magspecteur.util;

public class JWTUtil {

	private static String accessToken;
	private static String refreshToken;

	public static String decode(String token) {
		return "";
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		JWTUtil.accessToken = accessToken;
	}

	public static String getRefreshToken() {
		return refreshToken;
	}

	public static void setRefreshToken(String refreshToken) {
		JWTUtil.refreshToken = refreshToken;
	}
}
