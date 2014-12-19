package org.kerwyn.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Greeting.
 */
@Entity
@Table(name = "greeting")
public class Greeting {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	/** The name. */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * Instantiates a new greeting.
	 */
	public Greeting() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new greeting.
	 *
	 * @param name the name
	 */
	public Greeting(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Greeting [id=" + id + ", name=" + name + "]";
	}

}
