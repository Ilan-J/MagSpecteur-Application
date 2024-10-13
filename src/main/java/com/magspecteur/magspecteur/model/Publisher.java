package com.magspecteur.magspecteur.model;

import java.util.Collection;
import java.util.Date;

public class Publisher {

	private Integer id;

	private String name;
	private String address;
	private Date creation;

	private Collection<Theme> themes;

	public Publisher() {}

	public Publisher(String name, String address, Date creation, Collection<Theme> themes) {
		this.name = name;
		this.address = address;
		this.creation = creation;
		this.themes = themes;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Collection<Theme> getThemes() {
		return themes;
	}

	public void setThemes(Collection<Theme> themes) {
		this.themes = themes;
	}
}
