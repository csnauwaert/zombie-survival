package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.CascadeType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The user. */
	@ManyToOne(optional = false, targetEntity = User.class)
	private User user;

	/** The humans. */
	@OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, targetEntity = Human.class, fetch = FetchType.EAGER)
	private Set<Human> humans;

	/** The location times. */
	@ManyToOne(targetEntity = LocationTime.class)
	private LocationTime locationTime;


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


	public LocationTime getLocationTimes() {
		return locationTime;
	}


	@Override
	public String toString() {
		return "Crew [id=" + id + ", user=" + user.getId() + ", humans_size=" + humans.size()
				+ "]";
	}

	

}
