package org.kerwyn.game.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The name. */
	@Column
	private String name;

	/** The humans. */
	@ManyToMany(targetEntity = Human.class)
	private Collection<Human> humans;

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
		return "Skill [id=" + id + ", name=" + name + ", humans=" + humans
				+ "]";
	}
	

}
