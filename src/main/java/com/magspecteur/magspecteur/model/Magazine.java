package com.magspecteur.magspecteur.model;

import java.util.Date;

public class Magazine {

	private Integer id;

	private String name;
	private Integer number;
	private Date release;

	private Publisher publisher;

	public Magazine() {}

	public Magazine(String name, Integer number, Date release, Publisher publisher) {
		this.name = name;
		this.number = number;
		this.release = release;
		this.publisher = publisher;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
