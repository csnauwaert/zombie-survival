package org.kerwyn.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Loot.
 */
@Entity
@Table(name="LOOTS")
public class Loot {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/** The name. */
	@Column
	private String name;
	
	/** The number. */
	@Column
	private Long number;
	
	/** The ownership. */
	@ManyToOne(targetEntity=Crew.class)
	private Crew ownership;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the number.
	 *
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * Gets the ownership.
	 *
	 * @return the ownership
	 */
	public Crew getOwnership() {
		return ownership;
	}

	/**
	 * Sets the ownership.
	 *
	 * @param ownership the new ownership
	 */
	public void setOwnership(Crew ownership) {
		this.ownership = ownership;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Loot [id=" + id + ", name=" + name + ", number=" + number
				+ ", ownership=" + ownership + "]";
	}
	
}
