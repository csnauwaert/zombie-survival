package org.kerwyn.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USERS")
public class User {

	/** The login. */
	@Id
	@Column(nullable = false)
	private final String username;

	/** The password. */
	@Column(nullable = false)
	private final String password;

	/** The pseudo. */
	@Column(nullable = false)
	private final Boolean enabled;
	
	protected User() {
		this.username = "";
		this.password = "";
		this.enabled = false;
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

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}
	


}