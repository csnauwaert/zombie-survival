package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 * The Class Skill.
 */
@Entity
@Table(name = "SKILLS")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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
		if (this.humans.add(human))
			human.addSkill(this);
	}
	
	public void removeHuman(Human human){
		if (this.humans.remove(human))
			human.removeSkill(this);
	}
	
	@PreRemove
	private void preRemove() {
		//before deleting entity, remove all corresponding link in other entity
		//copy list of humans to prevent concurrent update while iterating on the list
		Set<Human> copy_list = new HashSet<Human>();
		for (Human h: this.humans) {
			copy_list.add(h);
		}
		for (Human h: copy_list) {
			removeHuman(h);
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

	public long getId() {
		return id;
	}

}
