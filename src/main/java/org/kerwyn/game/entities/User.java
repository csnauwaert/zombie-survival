package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.kerwyn.game.controllers.View;

import com.fasterxml.jackson.annotation.JsonView;

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
	@JsonView(View.UserBasicView.class)
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
	@JsonView(View.UserBasicView.class)
	@OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Crew> crew;
	
	@OneToOne(mappedBy = "user", orphanRemoval = true)
	private Authority authority;

	/**
	 * Instantiates a new user.
	 */
	protected User() {
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
	
	public Authority getAuthority() {
		return this.authority;
	}
	
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + ", pseudo=" + pseudo
				+ ", crew size=" + crew.size() + "]";
	}

	

}
