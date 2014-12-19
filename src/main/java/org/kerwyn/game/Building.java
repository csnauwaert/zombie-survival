package org.kerwyn.game;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Building.
 */
@Entity
@Table(name="BUILDINGS")
public class Building {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/** The name. */
	@Column
	private String name;
	
	/** The owner. */
	@ManyToOne(targetEntity=Crew.class)
	private Crew owner;
	
	/** The defense value. */
	@Column
	private Float defenseValue;
	
	/** The number. */
	@Column
	private Integer number;
	
	/** The locations. */
	@ManyToMany(targetEntity=Location.class)
	private Collection<Location> locations;
	
	/** The locations history. */
	@OneToMany(targetEntity=LocationTime.class)
	private Collection<LocationTime> locationsHistory;

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
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public Crew getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(Crew owner) {
		this.owner = owner;
	}

	/**
	 * Gets the defense value.
	 *
	 * @return the defense value
	 */
	public Float getDefenseValue() {
		return defenseValue;
	}

	/**
	 * Sets the defense value.
	 *
	 * @param defenseValue the new defense value
	 */
	public void setDefenseValue(Float defenseValue) {
		this.defenseValue = defenseValue;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets the locations.
	 *
	 * @return the locations
	 */
	public Collection<Location> getLocations() {
		return locations;
	}

	/**
	 * Sets the locations.
	 *
	 * @param locations the new locations
	 */
	public void setLocations(Collection<Location> locations) {
		this.locations = locations;
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
	 * Gets the locations history.
	 *
	 * @return the locations history
	 */
	public Collection<LocationTime> getLocationsHistory() {
		return locationsHistory;
	}

	/**
	 * Sets the locations history.
	 *
	 * @param locationsHistory the new locations history
	 */
	public void setLocationsHistory(Collection<LocationTime> locationsHistory) {
		this.locationsHistory = locationsHistory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + ", owner=" + owner
				+ ", defenseValue=" + defenseValue + ", number=" + number
				+ ", locations=" + locations + ", locationsHistory="
				+ locationsHistory + "]";
	}
	
	

	
}
