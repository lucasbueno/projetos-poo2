package com.lucasbueno.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Folder {

	private String name;

	public Folder() {

	}

	public Folder(String nome) {
		super();
		this.name = nome;
	}

	@Id
	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}
}