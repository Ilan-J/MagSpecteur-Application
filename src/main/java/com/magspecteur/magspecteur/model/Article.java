package com.magspecteur.magspecteur.model;

public class Article {

	private Integer id;

	private String name;
	private String author;

	private Integer magazineId;

	private Theme theme;

	public Article() {}

	public Article(String name, String author, Integer magazineId, Theme theme) {
		this.name = name;
		this.author = author;
		this.magazineId = magazineId;
		this.theme = theme;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("%s By %s", name, author);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getMagazineId() {
		return magazineId;
	}

	public void setMagazineId(Integer magazineId) {
		this.magazineId = magazineId;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
