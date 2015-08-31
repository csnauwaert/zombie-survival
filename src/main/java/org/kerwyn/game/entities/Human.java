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

	@ManyToOne()
	private Crew crew;
	
	@ManyToOne(optional = false)
	private Location location;
	
	@ManyToOne()
	private Job awayJob;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SKILL_HUMAN_REL",
            joinColumns=
            @JoinColumn(name="HUMAN_ID", referencedColumnName="ID"),
      inverseJoinColumns=
            @JoinColumn(name="SKILL_ID", referencedColumnName="ID")
	)
	private Set<Skill> skills;

	@OneToMany()
	private Set<Loot> inventory;
	
	@Column
	private Integer currentFoodConsume;
	
	@Column
	private Integer lastFoodConsume;

	@Column
	private Boolean infected;

	@Column
	private Boolean dead;

	@Column
	private Long timeBeforeTurning;

	@Column
	private Boolean away;

	@Column
	private Long timeBeforeReturn;

	@Column
	private Integer capacity;
	
	@Column
	private Long healerExp;
	
	@Column
	private Long gunnerExp;
	
	@Column
	private Long fighterExp;
	
	@Column
	private Long recolterExp;
	
	@Column
	private Long explorerExp;
	
	@Column
	private Long builderExp;
	
	@Column
	private Integer numberInjury;
	
	@Column
	private Integer numberInjuryMax;
	
	/**
	 * Constructor
	 */
	
	protected Human() {
		super();
	}

	public Human(String name, Crew crew, Location loc, Boolean infected, 
			int capacity, int currentfoodconsume, int numbermaxinjury) {
		super();
		this.name = name;
		this.crew = crew;
		this.location = loc;
		this.skills = new HashSet<Skill>();
		this.dead = false;
		this.timeBeforeReturn = -1L;
		this.timeBeforeTurning = -1L;
		this.away = false;
		this.numberInjury = 0;
		this.infected = infected;
		this.capacity = capacity;
		this.currentFoodConsume = currentfoodconsume;
		this.numberInjuryMax = numbermaxinjury;
		this.healerExp = 0L;
		this.gunnerExp = 0L;
		this.fighterExp = 0L;
		this.recolterExp = 0L;
		this.explorerExp = 0L;
		this.builderExp = 0L;
		//bidirectional link between crew and human
		crew.addHuman(this);
		//bidirectional link between human and location
		loc.addHuman(this);
	}
	
	/**
	 * Method
	 */
	
	public void setClassXP(Long healerExp, Long gunnerExp, Long fighterExp,
			Long recolterExp, Long explorerExp, Long builderExp) {
		this.healerExp = healerExp;
		this.gunnerExp = gunnerExp;
		this.fighterExp = fighterExp;
		this.recolterExp = recolterExp;
		this.explorerExp = explorerExp;
		this.builderExp = builderExp;
	}
	
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
		this.setCrew(null);
			
		//Since this entity is planned to be destroyed and we can't change the reference
		//on its object, we can safely remove reference on other entity without fear of
		//concurrent update
		for (Skill s: this.skills) {
			s.removeHuman(this);
		}
		this.setLocation(null);
	}
	
	/**
	 * Getter and Setter
	 */

	public Crew getCrew() {
		return crew;
	}
	
	public void setCrew(Crew crew) {
		if (this.crew != null && crew != this.crew)
			this.crew.removeHuman(this);
		if (!this.destroy) {
			this.crew = crew;
			if (crew != null)
				crew.addHuman(this);
		}
	}
	
	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		if (!this.destroy)
			this.skills = skills;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location.removeHuman(this);
		if (!this.destroy) {
			this.location = location;
			location.addHuman(this);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getAwayJob() {
		return awayJob;
	}

	//TODO m2o to write correctly
	public void setAwayJob(Job awayJob) {
		this.awayJob = awayJob;
	}

	public Set<Loot> getInventory() {
		return inventory;
	}

	//TODO o2m to write correctly
	public void setInventory(Set<Loot> inventory) {
		this.inventory = inventory;
	}

	public Integer getCurrentFoodConsume() {
		return currentFoodConsume;
	}

	public void setCurrentFoodConsume(Integer currentFoodConsume) {
		this.currentFoodConsume = currentFoodConsume;
	}

	public Integer getLastFoodConsume() {
		return lastFoodConsume;
	}

	public void setLastFoodConsume(Integer lastFoodConsume) {
		this.lastFoodConsume = lastFoodConsume;
	}

	public Boolean getInfected() {
		return infected;
	}

	public void setInfected(Boolean infected) {
		this.infected = infected;
	}

	public Boolean getDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	public Long getTimeBeforeTurning() {
		return timeBeforeTurning;
	}

	public void setTimeBeforeTurning(Long timeBeforeTurning) {
		this.timeBeforeTurning = timeBeforeTurning;
	}

	public Boolean getAway() {
		return away;
	}

	public void setAway(Boolean away) {
		this.away = away;
	}

	public Long getTimeBeforeReturn() {
		return timeBeforeReturn;
	}

	public void setTimeBeforeReturn(Long timeBeforeReturn) {
		this.timeBeforeReturn = timeBeforeReturn;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Long getHealerExp() {
		return healerExp;
	}

	public void setHealerExp(Long healerExp) {
		this.healerExp = healerExp;
	}

	public Long getGunnerExp() {
		return gunnerExp;
	}

	public void setGunnerExp(Long gunnerExp) {
		this.gunnerExp = gunnerExp;
	}

	public Long getFighterExp() {
		return fighterExp;
	}

	public void setFighterExp(Long fighterExp) {
		this.fighterExp = fighterExp;
	}

	public Long getRecolterExp() {
		return recolterExp;
	}

	public void setRecolterExp(Long recolterExp) {
		this.recolterExp = recolterExp;
	}

	public Long getExplorerExp() {
		return explorerExp;
	}

	public void setExplorerExp(Long explorerExp) {
		this.explorerExp = explorerExp;
	}

	public Long getBuilderExp() {
		return builderExp;
	}

	public void setBuilderExp(Long builderExp) {
		this.builderExp = builderExp;
	}

	public Integer getNumberInjury() {
		return numberInjury;
	}

	public void setNumberInjury(Integer numberInjury) {
		this.numberInjury = numberInjury;
	}

	public Integer getNumberInjuryMax() {
		return numberInjuryMax;
	}

	public void setNumberInjuryMax(Integer numberInjuryMax) {
		this.numberInjuryMax = numberInjuryMax;
	}

}
