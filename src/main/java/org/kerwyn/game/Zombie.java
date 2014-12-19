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

@Entity
@Table(name = "ZOMBIES")
public class Zombie {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(targetEntity = Location.class)
	private Location location;

	@Column
	private Integer number;

	@ManyToMany(targetEntity = LocationTime.class)
	private Collection<LocationTime> locationTime;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Collection<LocationTime> getLocationTime() {
		return locationTime;
	}

	public void setLocationTime(Collection<LocationTime> locationTime) {
		this.locationTime = locationTime;
	}

	public long getId() {
		return id;
	}

}
