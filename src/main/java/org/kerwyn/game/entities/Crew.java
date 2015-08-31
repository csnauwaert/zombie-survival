package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "CREWS")
public class Crew extends AbstractEntity {

	
	@ManyToOne()
	private User user;

	@OneToMany(mappedBy = "crew", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Human> humans;

	@OneToMany(mappedBy = "crew", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
	
	public void deleteCrew() {
		if (!this.destroy) {
			this.user.removeCrew(this);
			this.user = null;
		}
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
		if (!this.destroy && !this.humans.contains(human)) {
			this.humans.add(human);
			if (human.getCrew() != this)
				human.setCrew(this);
		}
			
	}
	
	/*
	 * Important!
	 * If we call this method from the code, always ensure that we have another
	 * call to set the human on another crew or null.
	 */
	public void removeHuman(Human human) {
		if (!this.destroy && this.humans.contains(human)) {
			this.humans.remove(human);
			
		}
	}
	
	protected void hookPreRemove() {
		this.user.removeCrew(this);
	}
}
