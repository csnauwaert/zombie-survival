package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Crew.
 */
@Entity
@Table(name = "CREWS")
public class Crew {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The user. */
	@ManyToOne(optional = false, targetEntity = User.class)
	private User user;

	/** The humans. */
	@OneToMany(targetEntity = Human.class, fetch = FetchType.EAGER)
	private Set<Human> humans;

	/** The location times. */
	@OneToMany(targetEntity = LocationTime.class, fetch = FetchType.LAZY)
	private Set<LocationTime> locationTimes;

	/** The loots. */
	@OneToMany(targetEntity = Loot.class)
	private Set<Loot> loots;


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


	public Set<LocationTime> getLocationTimes() {
		return locationTimes;
	}


	public Set<Loot> getLoots() {
		return loots;
	}


	@Override
	public String toString() {
		return "Crew [id=" + id + ", user=" + user.getId() + ", humans_size=" + humans.size()
				+ ", locationTimes_size=" + locationTimes.size() + ", loots_size=" + loots.size() + "]";
	}

	

}
