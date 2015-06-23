package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "HUMANS")
public class Human extends AbstractEntity {

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
	}
	
	protected void hookPreRemove() {
		//before deleting entity, remove all corresponding link in other entity
		this.crew.removeHuman(this);
		//Since this entity is planned to be destroyed and we can't change the reference
		//on its object, we can safely remove reference on other entity without fear of
		//concurrent update
		for (Skill s: this.skills) {
			s.removeHuman(this);
		}
	}
	
	/**
	 * Getter and Setter
	 */

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		if (!this.destroy)
			this.crew = crew;
	}
	
	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		if (!this.destroy)
			this.skills = skills;
	}

}
