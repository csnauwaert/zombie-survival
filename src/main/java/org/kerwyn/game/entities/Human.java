package org.kerwyn.game.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.kerwyn.game.config.Profession;


@Entity
@DynamicUpdate
@Table(name = "HUMANS",
		indexes = {@Index(name = "time_turning_index", columnList = "timeOfTurning", unique = false),
					@Index(name = "time_job_index", columnList = "timeOfReturn", unique = false)})
@BatchSize(size=200)
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

	/***
	 * Use fetchmode subselect or batchsize to prevent slow performance.
	 * Batchsize: if we need to select the inventory of 1000 different humans, it will only 
	 * 			execute 1000/batchsize_size select queries
	 * Fetchmode subselect: if we need to select inventory of 1000 differents humans, it will
	 * 			only execute 1 select query but with a subquery containing all the humans id.
	 * http://www.mkyong.com/hibernate/hibernate-fetching-strategies-examples/ 
	 */
	@OneToMany(mappedBy = "human", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 200)
	private Set<Item> inventory;

	@OneToMany()
	private Set<Item> equippedItem;

	@Column
	private Integer currentFoodConsume;

	@Column
	private Integer lastFoodConsume;
	
	@Column
	private Integer nbrDaysWithoutFood;

	@Column
	private Boolean infected;

	@Column
	private Boolean dead;

	@Column
	private Timestamp timeOfTurning;

	@Column
	private Boolean away;

	@Column
	private Timestamp timeOfReturn;

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
		this.timeOfReturn = null;
		this.timeOfTurning = null;
		this.away = false;
		this.numberInjury = 0;
		this.infected = infected;
		this.capacity = capacity;
		this.currentFoodConsume = currentfoodconsume;
		this.nbrDaysWithoutFood = 0;
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
	
	public void removeItem(Item item) {
		if (!this.destroy && this.inventory.contains(item)) {
			inventory.remove(item);
		}
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

	public Set<Item> getInventory() {
		return inventory;
	}

	//TODO o2m to write correctly
	public void setInventory(Set<Item> inventory) {
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

	public Integer getNbrDaysWithoutFood() {
		return nbrDaysWithoutFood;
	}

	public void setNbrDaysWithoutFood(Integer nbrDaysWithoutFood) {
		this.nbrDaysWithoutFood = nbrDaysWithoutFood;
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

	public Timestamp getTimeOfTurning() {
		return timeOfTurning;
	}
	
	public void setTimeOfTurning(Calendar timeOfTurning) {
		this.timeOfTurning = new Timestamp(timeOfTurning.getTimeInMillis());
	}

	public void setTimeOfTurning(Timestamp timeOfTurning) {
		this.timeOfTurning = timeOfTurning;
	}

	public Boolean getAway() {
		return away;
	}

	public void setAway(Boolean away) {
		this.away = away;
	}

	public Timestamp getTimeOfReturn() {
		return timeOfReturn;
	}
	
	public void setTimeOfReturn(Calendar timeOfReturn) {
		this.timeOfReturn = new Timestamp(timeOfReturn.getTimeInMillis());
	}

	public void setTimeOfReturn(Timestamp timeOfReturn) {
		this.timeOfReturn = timeOfReturn;
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

	public Long getExp(Profession profession) {
		switch(profession) {
		case HEALER:
			return this.getHealerExp(); 
		case GUNNER:
			return this.getGunnerExp();
		case FIGHTER:
			return this.getFighterExp();
		case RECOLTER:
			return this.getRecolterExp();
		case EXPLORER:
			return this.getExplorerExp();
		case BUILDER:
			return this.getBuilderExp();
		}
		return 0L;
	}
	
	public void setExp(Long exp, Profession profession) {
		switch(profession) {
		case HEALER:
			this.setHealerExp(exp);
			break;
		case GUNNER:
			this.setGunnerExp(exp);
			break;
		case FIGHTER:
			this.setFighterExp(exp);
			break;
		case RECOLTER:
			this.setRecolterExp(exp);
			break;
		case EXPLORER:
			this.setExplorerExp(exp);
			break;
		case BUILDER:
			this.setBuilderExp(exp);
			break;
		}
	}

}
