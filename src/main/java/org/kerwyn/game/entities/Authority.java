package org.kerwyn.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AUTHORITIES")
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The login. */
	@Column(nullable = false)
	private String username;

	/** The password. */
	@Column(nullable = false)
	private String authority;
	
	protected Authority() {
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param username            the user
	 * @param authority            the authority level
	 */
	public Authority(final String username, final String authority) {
		this.username = username;
		this.authority = authority;
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
	 * Gets the authority.
	 *
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", authority=" + authority + "]";
	}
	


}