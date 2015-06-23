package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CREWS")
public class Crew extends AbstractEntity {

	@ManyToOne(optional = false)
	private User user;

	@OneToMany(mappedBy = "crew", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Human> humans;

	@OneToMany(mappedBy = "crew", orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<LocationTime> locationTime;


	/**
	 * Constructor
	 */
	
	protected Crew() {}

	public Crew(User user) {
		super();
		this.user = user;
		this.humans = new HashSet<Human>();
		this.locationTime = new HashSet<LocationTime>();
		user.addCrew(this);
	}

	/**
	 * The Getter and Setter
	 */

	public User getUser() {
		return user;
	}

	public Set<Human> getHumans() {
		return humans;
	}

	public void setHumans(Set<Human> humans) {
		if (!this.destroy)
			this.humans = humans;
	}

	public Set<LocationTime> getLocationTimes() {
		return locationTime;
	}
	
	/**
	 * The methods
	 */
	
	public void addHuman(Human human) {
		if (!this.destroy)
			this.humans.add(human);
	}
	
	public void removeHuman(Human human) {
		if (!this.destroy)
			this.humans.remove(human);
	}
}
