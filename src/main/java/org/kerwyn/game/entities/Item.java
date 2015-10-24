package org.kerwyn.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The Class Loot.
 */
@Entity
@Table(name="ITEMS")
public class Item extends AbstractEntity {
	
	/** The name. */
	@Column
	private String name;
	
	/** The number. */
	@Column
	private Long number;
	
	/** The ownership. */
	@ManyToOne(targetEntity=Crew.class)
	private Crew ownership;
	
	@ManyToOne(optional = false, targetEntity = Location.class)
	private Location location;
	
	@Column
	private Boolean isEquipped;

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

}
