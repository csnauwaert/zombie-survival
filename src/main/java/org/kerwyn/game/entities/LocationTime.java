package org.kerwyn.game.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationTime.
 */
@Entity
@Table(name = "LOCATION_TIME")
public class LocationTime {

	/** The id. */
	@Id
	@Column(name = "LOC_TIME_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The crew. */
	@ManyToOne(optional = false)
//	@JoinColumn(name = "CREW_ID", referencedColumnName="CREW_ID")
	private Crew crew;

	/** The coordinate. */
	@Column
	private String coordinate;

	/** The view. */
	@Column
	private Date view;

	/** The loots. */
	@ManyToMany(targetEntity = Loot.class)
	private Set<Loot> loots;

	/** The building. */
	@ManyToMany(targetEntity = Building.class)
	private Set<Building> buildings;

	/** The humans. */
	@ManyToMany(targetEntity = Human.class)
	private Set<Human> humans;

	/** The zombies. */
	@ManyToMany(targetEntity = Zombie.class)
	private Set<Zombie> zombies;
	

	/**
	 * Instantiates a new location time.
	 */
	public LocationTime() {
	}
	
	
	
	/**
	 * Instantiates a new location time.
	 *
	 * @param crew the crew
	 * @param coordinate the coordinate
	 * @param view the view
	 * @param loots the loots
	 * @param buildings the buildings
	 * @param humans the humans
	 * @param zombies the zombies
	 */
	public LocationTime(Crew crew, String coordinate, Date view,
			Set<Loot> loots, Set<Building> buildings, Set<Human> humans,
			Set<Zombie> zombies) {
		this.crew = crew;
		this.coordinate = coordinate;
		this.view = view;
		this.loots = loots;
		this.buildings = buildings;
		this.humans = humans;
		this.zombies = zombies;
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
	 * Gets the coordinate.
	 *
	 * @return the coordinate
	 */
	public String getCoordinate() {
		return coordinate;
	}

	/**
	 * Sets the coordinate.
	 *
	 * @param coordinate
	 *            the new coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public Date getView() {
		return view;
	}

	/**
	 * Sets the view.
	 *
	 * @param view
	 *            the new view
	 */
	public void setView(Date view) {
		this.view = view;
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
	public void setLoots(Set<Loot> loots) {
		this.loots = loots;
	}

	/**
	 * Gets the building.
	 *
	 * @return the building
	 */
	public Set<Building> getBuildings() {
		return buildings;
	}

	/**
	 * Sets the building.
	 *
	 * @param buildings the new building
	 */
	public void setBuilding(Set<Building> buildings) {
		this.buildings = buildings;
	}

	/**
	 * Gets the humans.
	 *
	 * @return the humans
	 */
	public Set<Human> getHumans() {
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
	 * Gets the zombies.
	 *
	 * @return the zombies
	 */
	public Set<Zombie> getZombies() {
		return zombies;
	}

	/**
	 * Sets the zombies.
	 *
	 * @param zombies
	 *            the new zombies
	 */
	public void setZombies(Set<Zombie> zombies) {
		this.zombies = zombies;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationTime [id=" + id + ", crew=" + crew + ", coordinate="
				+ coordinate + ", view=" + view + ", loots=" + loots
				+ ", building=" + buildings + ", humans=" + humans
				+ ", zombies=" + zombies + "]";
	}
	
	public void removeCrew(){
		this.crew = null;
	}

}
