package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Job.
 */
@Entity
@Table(name = "JOBS")
public class Job extends AbstractEntity {

	/** The name. */
	@Column
	private String name;

	/** The humans. */
	@OneToMany(targetEntity = Human.class)
	private Set<Human> humans;

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
	@OneToMany(targetEntity = Loot.class)
	private Set<Loot> loot;

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
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the humans.
	 *
	 * @return the humans
	 */
	public Set<Human> getHumans() {
		return humans;
	}

	/**
	 * Sets the humans.
	 *
	 * @param humans
	 *            the new humans
	 */
	public void setHumans(Set<Human> humans) {
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
	 * @param baseTime
	 *            the new base time
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
	 * @param baseChanceSuccess
	 *            the new base chance success
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
	 * @param baseChanceEncounter
	 *            the new base chance encounter
	 */
	public void setBaseChanceEncounter(Float baseChanceEncounter) {
		this.baseChanceEncounter = baseChanceEncounter;
	}

	/**
	 * Gets the loot.
	 *
	 * @return the loot
	 */
	public Set<Loot> getLoot() {
		return loot;
	}

	/**
	 * Sets the loot.
	 *
	 * @param loot
	 *            the new loot
	 */
	public void setLoot(Set<Loot> loot) {
		this.loot = loot;
	}
}
