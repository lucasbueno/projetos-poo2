package br.com.lucasbueno.crud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity
@Access(AccessType.PROPERTY)
public class User {

	private SimpleStringProperty name;

	private SimpleIntegerProperty age;

	private SimpleStringProperty registerDate;

	public User() {
		this.name = new SimpleStringProperty();
		this.age = new SimpleIntegerProperty();
		this.registerDate = new SimpleStringProperty();
	}

	public User(String name, int age) {
		super();
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.registerDate = new SimpleStringProperty(dtf.format(LocalDateTime.now()));
	}

	@Id
	public String getName() {
		return name.get();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public int getAge() {
		return age.get();
	}

	public SimpleIntegerProperty ageProperty() {
		return age;
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public String getRegisterDate() {
		return registerDate.get();
	}

	public SimpleStringProperty registerDateProperty() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate.set(registerDate);
	}
}