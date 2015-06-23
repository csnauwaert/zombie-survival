package org.kerwyn.game.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION_TIME")
public class LocationTime extends AbstractEntity {

	@ManyToOne(optional = false)
	private Crew crew;

	@Column(nullable = false)
	private String coordinate;

	@Column(nullable = false)
	private Date explore_date;

	@ManyToMany()
	private Set<Loot> loots;

	@ManyToMany()
	private Set<Building> buildings;

	@ManyToMany()
	private Set<Human> humans;

	@ManyToMany()
	private Set<Zombie> zombies;
	

	/**
	 * Constructor
	 */
	
	protected LocationTime() {}
	
	public LocationTime(Crew crew, String coordinate, Date explore_date) {
		this.crew = crew;
		this.coordinate = coordinate;
		this.explore_date = explore_date;
	}
	
	/**
	 * The Getter and Setter
	 */

	public Crew getCrew() {
		return crew;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public Date getExplore_date() {
		return explore_date;
	}

	public void setExplore_date(Date explore_date) {
		this.explore_date = explore_date;
	}

	public Collection<Loot> getLoots() {
		return loots;
	}

	public void setLoots(Set<Loot> loots) {
		this.loots = loots;
	}

	public Set<Building> getBuildings() {
		return buildings;
	}

	public void setBuilding(Set<Building> buildings) {
		this.buildings = buildings;
	}

	public Set<Human> getHumans() {
		return humans;
	}

	public void setHumans(Set<Human> humans) {
		this.humans = humans;
	}

	public Set<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(Set<Zombie> zombies) {
		this.zombies = zombies;
	}

}
