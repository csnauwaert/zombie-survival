package org.kerwyn.game;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SKILLS")
public class Skill {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@ManyToMany(targetEntity = Human.class)
	private Collection<Human> humans;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Human> getHumans() {
		return humans;
	}

	public void setHumans(Collection<Human> humans) {
		this.humans = humans;
	}

	public long getId() {
		return id;
	}
	
	

}
