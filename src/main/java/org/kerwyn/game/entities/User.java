package org.kerwyn.game.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kerwyn.game.controllers.View;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

	/** The login. */
	@JsonView(View.UserBasicView.class)
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	/** The status of user (active-inactive). */
	/** Other player can not harm inactive player (that are on holidays for example) **/
	@Column(nullable = false)
	private Boolean enabled;

	@Column()
	private Timestamp last_connection;
	
	@Column(nullable = false)
	private String pseudo;

	@JsonView(View.UserBasicView.class)
	@OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Crew> crew;

	/**
	 * Instantiates a new user.
	 */
	protected User() {}

	public User(String username, String password, Boolean enabled, String pseudo) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.pseudo = pseudo;
		this.crew = new HashSet<Crew>();
	}
	
	/**
	 * Getter and Setter
	 */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Set<Crew> getCrew() {
		return crew;
	}
	
	public Timestamp getLastConnection() {
		return this.last_connection;
	}

	public void setLastConnection(Calendar lastConnection) {
		this.last_connection = new Timestamp(lastConnection.getTimeInMillis());
	}
	
	public void setLastConnection(Timestamp lastConnection) {
		this.last_connection = lastConnection;
	}

	/***
	 * Methods
	 */

	public void addCrew(Crew crew) {
		if (!this.destroy)
			this.crew.add(crew);
	}
	
	public void removeCrew(Crew crew) {
		if (!this.destroy)
			if (this.crew.remove(crew))
				crew.deleteCrew();
	}
}
