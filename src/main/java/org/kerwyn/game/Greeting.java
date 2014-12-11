package org.kerwyn.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "greeting")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	
	public Greeting() {
		// TODO Auto-generated constructor stub
	}

	public Greeting(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", name=" + name + "]";
	}

}
