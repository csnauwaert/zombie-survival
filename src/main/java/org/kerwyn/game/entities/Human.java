package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

//import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "HUMANS")
public class Human {

	/**
	 * Set as true when entity is planned for destroy, that
	 * way we can prevent concurrent model update from other entities
	 * which might also be marked as deleted and trying to remove their
	 * reference to this entity (especially the case with orphanremoval = true)
	 */
	boolean destroy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false)
	private Crew crew;
	
	@ManyToOne(optional = false)
	private Location location;
	
	@Column
	private Float currentFeedLevel;
	
	@Column
	private Float lastFeedLevel;

	@Column
	private Boolean infected;

	@Column
	private Boolean dead;

	@Column
	private Float timeBeforeTurning;

	@Column
	private Boolean away;

	@Column
	private Float timeBeforeReturn;

	@ManyToOne()
	private Job awayJob;

	@Column
	private Float capacity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SKILL_HUMAN_REL",
            joinColumns=
            @JoinColumn(name="HUMAN_ID", referencedColumnName="ID"),
      inverseJoinColumns=
            @JoinColumn(name="SKILL_ID", referencedColumnName="ID")
	)
	private Set<Skill> skills;

	@OneToMany()
	private Set<Loot> loots;
	
	/**
	 * Constructor
	 */
	
	protected Human() {
		super();
	}

	public Human(String name, Crew crew, Location loc) {
		super();
		this.destroy = false;
		this.name = name;
		this.crew = crew;
		this.location = loc;
		this.skills = new HashSet<Skill>();
		//bidirectional link between crew and human
		crew.addHuman(this);
	}
	
	/**
	 * Method
	 */
	
	public void addSkill(Skill skill) {
		if (!this.destroy && this.skills.add(skill))
			skill.addHuman(this);
	}
	
	public void removeSkill(Skill skill) {
		//prevent modifying skill set if we are deleting entity
		if (!this.destroy && this.skills.remove(skill))
			skill.removeHuman(this);
		//If we are destroying entity, ensure that link on skill has been removed
		if (this.destroy)
			skill.removeHuman(this);
	}
	
	@PreRemove
	private void preRemove() {
		this.destroy = true;
		//before deleting entity, remove all corresponding link in other entity
		this.crew.removeHuman(this);
		//copy list of humans to prevent concurrent update while iterating on the list
		Set<Skill> copy_list = new HashSet<Skill>();
		for (Skill s: this.skills) {
			copy_list.add(s);
		}
		for (Skill s: copy_list) {
			removeSkill(s);
		}
	}
	
	/**
	 * Getter and Setter
	 */

	public Crew getCrew() {
		return crew;
	}

	public Long getId() {
		return id;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setCrew(Crew crew) {
		if (!this.destroy)
			this.crew = crew;
	}

	public void setSkills(Set<Skill> skills) {
		if (!this.destroy)
			this.skills = skills;
	}

}
