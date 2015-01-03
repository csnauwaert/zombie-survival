package org.kerwyn.game.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The Class Job.
 */
@Entity
@Table(name="JOBS")
public class Job {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** The name. */
	@Column
	private String name;
	
	/** The humans. */
	@OneToMany(targetEntity=Human.class)
	private Collection<Human> humans;
	
	/** The base time. */
	@Column
	private Float baseTime;

	/** The base chance success. */
	@Column
	private Float baseChanceSuccess;
	
	/** The base chance encounter. */
	@Column
	private Float baseChanceEncounter;
	
	/** The loot. */
	@OneToMany(targetEntity=Loot.class)
	private Collection<Loot> loot;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the humans.
	 *
	 * @return the humans
	 */
	public Collection<Human> getHumans() {
		return humans;
	}

	/**
	 * Sets the humans.
	 *
	 * @param humans the new humans
	 */
	public void setHumans(Collection<Human> humans) {
		this.humans = humans;
	}

	/**
	 * Gets the base time.
	 *
	 * @return the base time
	 */
	public Float getBaseTime() {
		return baseTime;
	}

	/**
	 * Sets the base time.
	 *
	 * @param baseTime the new base time
	 */
	public void setBaseTime(Float baseTime) {
		this.baseTime = baseTime;
	}

	/**
	 * Gets the base chance success.
	 *
	 * @return the base chance success
	 */
	public Float getBaseChanceSuccess() {
		return baseChanceSuccess;
	}

	/**
	 * Sets the base chance success.
	 *
	 * @param baseChanceSuccess the new base chance success
	 */
	public void setBaseChanceSuccess(Float baseChanceSuccess) {
		this.baseChanceSuccess = baseChanceSuccess;
	}

	/**
	 * Gets the base chance encounter.
	 *
	 * @return the base chance encounter
	 */
	public Float getBaseChanceEncounter() {
		return baseChanceEncounter;
	}

	/**
	 * Sets the base chance encounter.
	 *
	 * @param baseChanceEncounter the new base chance encounter
	 */
	public void setBaseChanceEncounter(Float baseChanceEncounter) {
		this.baseChanceEncounter = baseChanceEncounter;
	}

	/**
	 * Gets the loot.
	 *
	 * @return the loot
	 */
	public Collection<Loot> getLoot() {
		return loot;
	}

	/**
	 * Sets the loot.
	 *
	 * @param loot the new loot
	 */
	public void setLoot(Collection<Loot> loot) {
		this.loot = loot;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", humans=" + humans
				+ ", baseTime=" + baseTime + ", baseChanceSuccess="
				+ baseChanceSuccess + ", baseChanceEncounter="
				+ baseChanceEncounter + ", loot=" + loot + "]";
	}
	
	
}
