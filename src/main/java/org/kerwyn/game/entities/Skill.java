package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Class Skill.
 */
@Entity
@Table(name = "SKILLS")
public class Skill extends AbstractEntity{

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
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
		this.humans = new HashSet<Human>();
	}
	
	/**
	 * Methods
	 */
	
	public void addHuman(Human human){
		if (!this.destroy && this.humans.add(human))
			human.addSkill(this);
	}
	
	public void removeHuman(Human human){
		// if entity is planned for destroy, ignore change to prevent concurrent update
		if (!this.destroy && this.humans.remove(human))
			human.removeSkill(this);
	}
	
	protected void hookPreRemove() {
		//before deleting entity, remove all corresponding link in other entity
		for (Human h: this.humans) {
			h.removeSkill(this);
		}
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

}
