package org.kerwyn.game.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
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

	/** The pseudo. */
	@Column(nullable = false)
	private Boolean enabled;
	
	/** The pseudo. */
	@Column(nullable = false)
	private String pseudo;
	
	/** The user. */
	@OneToOne(optional = false, cascade = CascadeType.ALL, targetEntity = Crew.class)
    private Crew crew;
	
	
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param username            the new login
	 * @param password            the new password
	 * @param enabled            if user is active or not
	 */
	public User(final String username, final String password, final Boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets enabled status.
	 *
	 * @return the active state
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Gets the pseudo.
	 *
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Sets the pseudo.
	 *
	 * @param pseudo the new pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Gets the crew.
	 *
	 * @return the crew
	 */
	public Crew getCrew() {
		return crew;
	}

	/**
	 * Sets the crew.
	 *
	 * @param crew the new crew
	 */
	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}
	


}
