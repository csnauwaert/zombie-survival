package org.kerwyn.game.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Building.
 */
@Entity
@Table(name = "BUILDINGS")
public class Building extends AbstractEntity {

	/** The name. */
	@Column
	private String name;

	/** The owner. */
	@ManyToOne(targetEntity = Crew.class)
	private Crew owner;

	/** The defense value. */
	@Column
	private Float defenseValue;

	/** The number. */
	@Column
	private Integer number;

	/** The locations. */
	@ManyToOne(optional = false, targetEntity = Location.class)
	private Location location;

	/** The locations history. */
	@OneToMany(targetEntity = LocationTime.class)
	private Set<LocationTime> locationsHistory;

}
