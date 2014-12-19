package org.kerwyn.game;

import java.util.Collection;

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
public class Zombie {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** The location. */
	@ManyToOne(targetEntity = Location.class)
	private Location location;

	/** The number. */
	@Column
	private Integer number;

	/** The location times. */
	@ManyToMany(targetEntity = LocationTime.class)
	private Collection<LocationTime> locationTimes;

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
	 * @param location the new location
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
	 * @param number the new number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets the location times.
	 *
	 * @return the location times
	 */
	public Collection<LocationTime> getLocationTimes() {
		return locationTimes;
	}

	/**
	 * Sets the location time.
	 *
	 * @param locationTimes the new location time
	 */
	public void setLocationTime(Collection<LocationTime> locationTimes) {
		this.locationTimes = locationTimes;
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
		return "Zombie [id=" + id + ", location=" + location + ", number="
				+ number + ", locationTimes=" + locationTimes + "]";
	}
	
	

}