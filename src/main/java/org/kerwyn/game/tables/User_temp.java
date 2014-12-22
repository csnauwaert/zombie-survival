package org.kerwyn.game.tables;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.kerwyn.game.AuthentificationLevel;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS_TEMP")
public class User_temp {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** The login. */
	@Column(nullable = false)
	private final String login;

	/** The password. */
	@Column(nullable = false)
	private final String password;

	/** The pseudo. */
	@Column(nullable = false)
	private final String pseudo;

	/** The authentification level. */
	@Column(nullable = false)
	private final AuthentificationLevel authentificationLevel;

	/** The session id. */
	@Column(nullable = false)
	private final String sessionId;
	
	/** The user. */
	@OneToOne(optional = false, cascade = CascadeType.ALL, targetEntity = Crew.class)
    private Crew crew;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param newLogin            the new login
	 * @param newPassword            the new password
	 * @param newPseudo            the new pseudo
	 * @param newAuthentificationLevel            the new authentification level
	 * @param newSessionId            the new session id
	 * @param newCrew the new crew
	 */
	public User_temp(final String newLogin, final String newPassword,
			final String newPseudo,
			final AuthentificationLevel newAuthentificationLevel,
			final String newSessionId, final Crew newCrew) {
		this.login = newLogin;
		this.password = newPassword;
		this.pseudo = newPseudo;
		this.authentificationLevel = newAuthentificationLevel;
		this.sessionId = newSessionId;
		this.crew=newCrew;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
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
	 * Gets the pseudo.
	 *
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Gets the authentification level.
	 *
	 * @return the authentification level
	 */
	public AuthentificationLevel getAuthentificationLevel() {
		return authentificationLevel;
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}
	
	

	/**
	 * Gets the crew.
	 *
	 * @return the crew
	 */
	public Crew getCrew() {
		return crew;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", pseudo=" + pseudo + ", authentificationLevel="
				+ authentificationLevel + ", sessionId=" + sessionId
				+ ", crew=" + crew + "]";
	}
	


}
