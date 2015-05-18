package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Class Skill.
 */
@Entity
@Table(name = "SKILLS")
public class Skill {

	@Id
	@Column(name = "SKILL_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
	private Set<Human> humans;

	/**
	 * Constructor
	 */
	
	public Skill() {
		super();
	}
	
	public Skill(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Methods
	 */
	
	public void addHuman(Human human){
		human.addSkill(this);
	}
	
	public void removeHuman(Human human){
		human.removeSkill(this);
	}
	
	/**
	 * Getter and Setter
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Human> getHumans() {
		return humans;
	}

	public void setHumans(Set<Human> humans) {
		this.humans = humans;
	}

	public long getId() {
		return id;
	}

}
