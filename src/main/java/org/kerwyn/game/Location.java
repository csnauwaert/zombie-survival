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
 * The Class Location.
 */
@Entity
@Table(name="LOCATIONS")
public class Location {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	/** The coordinate. */
	@Column
	private String coordinate;
	
	/** The loots. */
	@ManyToMany(targetEntity = Loot.class)
	private Collection<Loot> loots;

	/** The building. */
	@ManyToOne(targetEntity = Building.class)
	private Building building;

	/** The humans. */
	@OneToMany(targetEntity = Human.class)
	private Collection<Human> humans;

	/** The zombies. */
	@OneToMany(targetEntity = Zombie.class)
	private Collection<Zombie> zombies;

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
	 * @param coordinate the new coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
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
	 * @param loots the new loots
	 */
	public void setLoots(Collection<Loot> loots) {
		this.loots = loots;
	}

	/**
	 * Gets the building.
	 *
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * Sets the building.
	 *
	 * @param building the new building
	 */
	public void setBuilding(Building building) {
		this.building = building;
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
	 * @param humans the new humans
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
	 * @param zombies the new zombies
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [id=" + id + ", coordinate=" + coordinate + ", loots="
				+ loots + ", building=" + building + ", humans=" + humans
				+ ", zombies=" + zombies + "]";
	}
	
	
	
	

}
