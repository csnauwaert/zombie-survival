package org.kerwyn.game.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreRemove;
import javax.persistence.Transient;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract public class AbstractEntity {
	
	/**
	 * Set as true when entity is planned for destroy, that
	 * way we can prevent concurrent model update from other entities
	 * whom might try to remove their reference to this entity
	 */
	@Transient
	boolean destroy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Constructor
	 */
	public AbstractEntity() {
		super();
		this.destroy = false;
	}
	
	/**
	 * Getter
	 */
	
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Method called before deleting an entity
	 * use to set delete flag as true
	 * If you need to perform other action before deleting entity
	 * (such as removing reference, etc), overwrite the hookPreRemove method.
	 */
	@PreRemove
	private void preRemove() {
		this.destroy = true;
		this.hookPreRemove();
	}
	
	protected void hookPreRemove() {
		//To be overwritten
	}
	

}
