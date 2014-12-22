package org.kerwyn.game.tables;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The Class LocationTime.
 */
@Entity
@Table(name = "LOCATION_TIME")
public class LocationTime {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** The crew. */
	@ManyToOne(targetEntity = Crew.class)
	private Crew crew;

	/** The coordinate. */
	@Column
	private String coordinate;

	/** The view. */
	@Column
	private Date view;

	/** The loots. */
	@ManyToMany(targetEntity = Loot.class)
	private Collection<Loot> loots;

	/** The building. */
	@ManyToMany(targetEntity = Building.class)
	private Collection<Building> buildings;

	/** The humans. */
	@ManyToMany(targetEntity = Human.class)
	private Collection<Human> humans;

	/** The zombies. */
	@ManyToMany(targetEntity = Zombie.class)
	private Collection<Zombie> zombies;

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
	public void setLoots(Collection<Loot> loots) {
		this.loots = loots;
	}

	/**
	 * Gets the building.
	 *
	 * @return the building
	 */
	public Collection<Building> getBuildings() {
		return buildings;
	}

	/**
	 * Sets the building.
	 *
	 * @param building
	 *            the new building
	 */
	public void setBuilding(Collection<Building> buildings) {
		this.buildings = buildings;
	}

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
	public void setHumans(Collection<Human> humans) {
		this.humans = humans;
	}

	/**
	 * Gets the zombies.
	 *
	 * @return the zombies
	 */
	public Collection<Zombie> getZombies() {
		return zombies;
	}

	/**
	 * Sets the zombies.
	 *
	 * @param zombies
	 *            the new zombies
	 */
	public void setZombies(Collection<Zombie> zombies) {
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

}
