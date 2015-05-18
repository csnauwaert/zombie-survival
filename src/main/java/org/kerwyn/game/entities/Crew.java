package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kerwyn.game.controllers.View;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * The Class Crew.
 */
@Entity
@Table(name = "CREWS")
public class Crew {

	/** The id. */
	@Id
	@JsonView({View.UserBasicView.class, View.CrewBasicView.class})
	@Column(name = "CREW_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The user. */
	@ManyToOne(optional = false, targetEntity = User.class)
	private User user;

	/** The humans. */
	@OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Human.class, fetch = FetchType.EAGER)
	private Set<Human> humans;

	/** The location times. */
	@OneToMany(mappedBy = "crew", targetEntity = LocationTime.class, fetch = FetchType.EAGER)
	private Set<LocationTime> locationTime;


	/**
	 * Constructor
	 */
	
	public Crew() {
	}


	public Crew(User user) {
		super();
		this.user = user;
	}
	
	/**
	 * Methods
	 */
	
	public void addHuman(Human human) {
		human.setCrew(this);
	}
	
	public void removeHuman(Human human) {
		human.removeCrew();
	}
	
	public void addLocationTime(LocationTime loc) {
		loc.setCrew(this);
	}
	
	public void removeLocationTime(LocationTime loc) {
		loc.removeCrew();
	}

	/**
	 * The Getter and Setter
	 */
	
	public long getId() {
		return id;
	}


	public User getUser() {
		return user;
	}


	public Set<Human> getHumans() {
		return humans;
	}
	

	public void setHumans(Set<Human> humans) {
		this.humans = humans;
	}


	public Set<LocationTime> getLocationTimes() {
		return locationTime;
	}

}
