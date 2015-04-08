package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Zombie.
 */
@Entity
@Table(name = "ZOMBIES")
public class Zombie implements Being {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The location. */
	@ManyToOne(optional = false, targetEntity = Location.class)
	private Location location;

	/** The number. */
	@Column
	private Integer number;

	/** The location times. */
	@ManyToMany(targetEntity = LocationTime.class)
	private Set<LocationTime> locationTimes;

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location
	 *            the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
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
	 * @param number
	 *            the new number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets the location times.
	 *
	 * @return the location times
	 */
	public Set<LocationTime> getLocationTimes() {
		return locationTimes;
	}

	/**
	 * Sets the location time.
	 *
	 * @param locationTimes
	 *            the new location time
	 */
	public void setLocationTime(Set<LocationTime> locationTimes) {
		this.locationTimes = locationTimes;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Zombie [id=" + id + ", location=" + location + ", number="
				+ number + ", locationTimes=" + locationTimes + "]";
	}

}
