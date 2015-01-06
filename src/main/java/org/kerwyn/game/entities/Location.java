package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
@Entity
@Table(name = "LOCATIONS")
public class Location {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The coordinate. */
	@Column
	private Integer coordinate;

	/** The loots. */
	@ManyToMany(targetEntity = Loot.class)
	private Set<Loot> loots;

	/** The building. */
	@ManyToMany(targetEntity = Building.class)
	private Set<Building> buildings;

	/** The humans. */
	@OneToMany(targetEntity = Human.class)
	private Set<Human> humans;

	/** The zombies. */
	@OneToMany(targetEntity = Zombie.class)
	private Set<Zombie> zombies;

	/**
	 * Gets the coordinate.
	 *
	 * @return the coordinate
	 */
	public Integer getCoordinate() {
		return coordinate;
	}

	/**
	 * Sets the coordinate.
	 *
	 * @param coordinate
	 *            the new coordinate
	 */
	public void setCoordinate(Integer coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Gets the loots.
	 *
	 * @return the loots
	 */
	public Set<Loot> getLoots() {
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
	 * @param building
	 *            the new building
	 */
	public void setBuildings(Set<Building> buildings) {
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
	 * Adds the human.
	 *
	 * @param human
	 *            the human
	 * @return the human
	 */
	public Human addHuman(Human human) {
		this.humans.add(human);
		return human;
	}

	/**
	 * Removes the human.
	 *
	 * @param human
	 *            the human
	 * @return the human
	 */
	public Human removeHuman(Human human) {
		this.humans.remove(human);
		return human;
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
		return "Location [id=" + id + ", coordinate=" + coordinate + ", loots="
				+ loots + ", buildings=" + buildings + ", humans=" + humans
				+ ", zombies=" + zombies + "]";
	}

}
