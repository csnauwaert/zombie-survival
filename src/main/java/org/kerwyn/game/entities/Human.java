package org.kerwyn.game.entities;

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
 * The Class Human.
 */
@Entity
@Table(name = "HUMANS")
public class Human implements Being {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The name. */
	@Column(nullable = false)
	private String name;

	/** The crew. */
	@ManyToOne(targetEntity = Crew.class)
	private Crew crew;

	/** The infected. */
	@Column
	private Boolean infected;

	/** The dead. */
	@Column
	private Boolean dead;

	/** The time before turning. */
	@Column
	private Float timeBeforeTurning;

	/** The away. */
	@Column
	private Boolean away;

	/** The time before return. */
	@Column
	private Float timeBeforeReturn;

	/** The away job. */
	@ManyToOne(targetEntity = Job.class)
	private Job awayJob;

	/** The current location. */
	@ManyToOne(targetEntity = Location.class)
	private Location currentLocation;

	/** The capacity. */
	@Column
	private Float capacity;

	/** The skills. */
	@OneToMany(targetEntity = Skill.class)
	private Collection<Skill> skills;

	/** The loots. */
	@OneToMany(targetEntity = Loot.class)
	private Collection<Loot> loots;

	/** The location times. */
	@ManyToMany(targetEntity = LocationTime.class)
	private Collection<LocationTime> locationTimes;

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
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the crew.
	 *
	 * @return the crew
	 */
	public Crew getCrew() {
		return crew;
	}

	/**
	 * Sets the crew.
	 *
	 * @param crew
	 *            the new crew
	 */
	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	/**
	 * Gets the infected.
	 *
	 * @return the infected
	 */
	public Boolean getInfected() {
		return infected;
	}

	/**
	 * Sets the infected.
	 *
	 * @param infected
	 *            the new infected
	 */
	public void setInfected(Boolean infected) {
		this.infected = infected;
	}

	/**
	 * Gets the dead.
	 *
	 * @return the dead
	 */
	public Boolean getDead() {
		return dead;
	}

	/**
	 * Sets the dead.
	 *
	 * @param dead
	 *            the new dead
	 */
	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	/**
	 * Gets the time before turning.
	 *
	 * @return the time before turning
	 */
	public Float getTimeBeforeTurning() {
		return timeBeforeTurning;
	}

	/**
	 * Sets the time before turning.
	 *
	 * @param timeBeforeTurning
	 *            the new time before turning
	 */
	public void setTimeBeforeTurning(Float timeBeforeTurning) {
		this.timeBeforeTurning = timeBeforeTurning;
	}

	/**
	 * Gets the away.
	 *
	 * @return the away
	 */
	public Boolean getAway() {
		return away;
	}

	/**
	 * Sets the away.
	 *
	 * @param away
	 *            the new away
	 */
	public void setAway(Boolean away) {
		this.away = away;
	}

	/**
	 * Gets the time before return.
	 *
	 * @return the time before return
	 */
	public Float getTimeBeforeReturn() {
		return timeBeforeReturn;
	}

	/**
	 * Sets the time before return.
	 *
	 * @param timeBeforeReturn
	 *            the new time before return
	 */
	public void setTimeBeforeReturn(Float timeBeforeReturn) {
		this.timeBeforeReturn = timeBeforeReturn;
	}

	/**
	 * Gets the away job.
	 *
	 * @return the away job
	 */
	public Job getAwayJob() {
		return awayJob;
	}

	/**
	 * Sets the away job.
	 *
	 * @param awayJob
	 *            the new away job
	 */
	public void setAwayJob(Job awayJob) {
		this.awayJob = awayJob;
	}

	/**
	 * Gets the current location.
	 *
	 * @return the current location
	 */
	public Location getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * Sets the current location.
	 *
	 * @param currentLocation
	 *            the new current location
	 */
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public Float getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity
	 *            the new capacity
	 */
	public void setCapacity(Float capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public Collection<Skill> getSkills() {
		return skills;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills
	 *            the new skills
	 */
	public void setSkills(Collection<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * Gets the loots.
	 *
	 * @return the loots
	 */
	public Collection<Loot> getLoots() {
		return loots;
	}

	/**
	 * Sets the loots.
	 *
	 * @param loots
	 *            the new loots
	 */
	public void setLoots(Collection<Loot> loots) {
		this.loots = loots;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * Sets the location times.
	 *
	 * @param locationTimes the new location times
	 */
	public void setLocationTimes(Collection<LocationTime> locationTimes) {
		this.locationTimes = locationTimes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Human [id=" + id + ", name=" + name + ", crew=" + crew
				+ ", infected=" + infected + ", dead=" + dead
				+ ", timeBeforeTurning=" + timeBeforeTurning + ", away=" + away
				+ ", timeBeforeReturn=" + timeBeforeReturn + ", awayJob="
				+ awayJob + ", currentLocation=" + currentLocation
				+ ", capacity=" + capacity + ", skills=" + skills + ", loots="
				+ loots + ", locationTimes=" + locationTimes + "]";
	}



}