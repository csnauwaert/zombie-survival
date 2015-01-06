package org.kerwyn.game.entities;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class Crew.
 */
@Entity
@Table(name = "CREWS")
public class Crew {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The user. */
	@OneToOne(optional = false, cascade = CascadeType.ALL, targetEntity = User.class, orphanRemoval = true)
	private User user;

	/** The humans. */
	@OneToMany(targetEntity = Human.class, fetch = FetchType.EAGER)
	private Set<Human> humans;

	/** The location times. */
	@OneToMany(targetEntity = LocationTime.class, fetch = FetchType.LAZY)
	private Set<LocationTime> locationTimes;

	@OneToMany(targetEntity = Loot.class)
	private Set<Loot> loots;

	/**
	 * Gets the humans.
	 *
	 * @return the humans
	 */
	public Collection<Human> getHumans() {
		return humans;
	}

	/**
	 * Sets the humans.
	 *
	 * @param humans
	 *            the new humans
	 */
	public void setHumans(Set<Human> humans) {
		this.humans = humans;
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
	 * Sets the location times.
	 *
	 * @param locationTimes
	 *            the new location times
	 */
	public void setLocationTimes(Set<LocationTime> locationTimes) {
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

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Crew [id=" + id + ", user=" + user + ", humans=" + humans
				+ ", locationTimes=" + locationTimes + "]";
	}

}
