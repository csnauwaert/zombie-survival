package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS")
public class User {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The login. */
	@Column(nullable = false)
	private String username;

	/** The password. */
	@Column(nullable = false)
	private String password;

	/** The status of user (active-inactive). */
	@Column(nullable = false)
	private Boolean enabled;

	/** The pseudo. */
	@Column(nullable = false)
	private String pseudo;

	/** The crew. */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = Crew.class, fetch = FetchType.EAGER)
	private Set<Crew> crew;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	public User(String username, String password, Boolean enabled, String pseudo) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.pseudo = pseudo;
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

	public void setCrew(Set<Crew> crew) {
		this.crew = crew;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + ", pseudo=" + pseudo
				+ ", crew size=" + crew.size() + "]";
	}

	

}
