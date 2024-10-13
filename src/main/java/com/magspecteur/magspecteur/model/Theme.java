package com.magspecteur.magspecteur.model;

public class Theme {

	private Integer id;

	private String name;

	public Theme() {}

	public Theme(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public Integer getId() {
		return id;
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
}
