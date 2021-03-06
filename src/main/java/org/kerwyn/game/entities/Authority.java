package org.kerwyn.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
public class Authority extends AbstractEntity {

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String authority;
	
	@OneToOne(optional = false)
	private User user;
	
	/***
	 * Constructor
	 */
	protected Authority() {}
	
	public Authority(User user, String authority) {
		super();
		this.username = user.getUsername();
		this.authority = authority;
		this.user = user;
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

	public String getUsername() {
		return username;
	}
	
	public User getUser() {
		return user;
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