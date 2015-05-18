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
import javax.persistence.Table;

/**
 * The Class Human.
 */
@Entity
@Table(name = "HUMANS")
public class Human {

	@Id
	@Column(name = "HUMAN_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne()
	@JoinColumn(name = "CREW_ID", referencedColumnName="CREW_ID")
	private Crew crew;

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

	@ManyToOne(targetEntity = Job.class)
	private Job awayJob;

	@ManyToOne(optional = false, targetEntity = Location.class)
	private Location location;

	@Column
	private Float capacity;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="SKILL_HUMAN",
            joinColumns=
            @JoinColumn(name="HUMAN_ID", referencedColumnName="HUMAN_ID"),
      inverseJoinColumns=
            @JoinColumn(name="SKILL_ID", referencedColumnName="SKILL_ID")
	)
	private Set<Skill> skills;

	@OneToMany(targetEntity = Loot.class)
	private Set<Loot> loots;

	@ManyToMany(targetEntity = LocationTime.class)
	private Set<LocationTime> locationTimes;
	
	/**
	 * Constructor
	 */
	
	public Human() {
		super();
	}

	public Human(String name, Crew crew, Location loc) {
		super();
		this.name = name;
		this.crew = crew;
		this.location = loc;
	}
	
	/**
	 * Method
	 */
	
	public void removeCrew() {
		this.crew = null;
	}
	
	public void addSkill(Skill skill) {
		if (this.skills == null){
			this.skills = new HashSet<Skill>();
		}
		this.skills.add(skill);
	}
	
	public void removeSkill(Skill skill) {
		if (this.skills == null){
			this.skills = new HashSet<Skill>();
		}
		this.skills.remove(skill);
	}
	
	/**
	 * Getter and Setter
	 */

	public Set<Skill> getSkills() {
		return skills;
	}

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

}
