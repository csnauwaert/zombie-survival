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

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String authority;
	
	/***
	 * Constructor
	 */
	protected Authority() {
	}
	
	public Authority(final String username, final String authority) {
		this.username = username;
		this.authority = authority;
	}
	
	/***
	 * Getter and Setter
	 */
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
	/***
	 * Method
	 */
	public Boolean canChangeAuthLevel(){
		if (this.authority.equals("ROLE_ADMIN"))
			return true;
		else
			return false;
	}
}