package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.kerwyn.game.controllers.View;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "CREWS")
public class Crew {
	
	/**
	 * Set as true when entity is planned for destroy, that
	 * way we can prevent concurrent model update from other entities
	 * which might also be marked as deleted and trying to remove their
	 * reference to this entity (especially the case with orphanremoval = true)
	 */
	boolean destroy;

	@Id
	@JsonView({View.UserBasicView.class, View.CrewBasicView.class})
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
		this.destroy = false;
		this.user = user;
		this.humans = new HashSet<Human>();
		this.locationTime = new HashSet<LocationTime>();
		user.addCrew(this);
	}

	/**
	 * The Getter and Setter
	 */
	
	public Long getId() {
		return id;
	}


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
	
	@PreRemove
	private void preRemove() {
		this.destroy = true;
	}

}
