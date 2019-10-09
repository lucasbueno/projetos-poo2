package com.lucasbueno.orm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class File {

	@Id
	private String name;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<User> sharedWith;
	
	private Folder folder;

	public File() {

	}

	public File(String name) {
		super();
		this.name = name;
	}

	public File(String name, List<User> sharedWith) {
		super();
		this.name = name;
		this.sharedWith = sharedWith;
	}

	public void shareWith(User user) {
		if (sharedWith == null)
			sharedWith = new ArrayList<>();
		this.sharedWith.add(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(List<User> sharedWith) {
		this.sharedWith = sharedWith;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sharedWith == null) ? 0 : sharedWith.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sharedWith == null) {
			if (other.sharedWith != null)
				return false;
		} else if (!sharedWith.equals(other.sharedWith))
			return false;
		return true;
	}

}
