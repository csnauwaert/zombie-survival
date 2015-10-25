package org.kerwyn.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The Class Loot.
 */
@Entity
@DynamicUpdate
@Table(name="ITEMS")
public class Item extends AbstractEntity {
	
	@Column
	private String name;
	
	@Column
	private Integer quantity;
	
	@ManyToOne()
	private Crew ownership;
	
	@ManyToOne()
	private Human human;
	
	@ManyToOne(optional = false)
	private Location location;
	
	@Column
	private Boolean isEquipped;

	protected Item() {
		super();
	}
	
	public Item(String name, int quantity, Location loc) {
		this.name = name;
		this.quantity = quantity;
		this.isEquipped = false;
		this.location = loc;
	}
	
	protected void hookPreRemove() {
		if (this.human != null) {
			this.human.removeItem(this);
		}
	}
	
	public Human getHuman() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getIsEquipped() {
		return isEquipped;
	}

	public void setIsEquipped(Boolean isEquipped) {
		this.isEquipped = isEquipped;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Crew getOwnership() {
		return ownership;
	}

	public void setOwnership(Crew ownership) {
		this.ownership = ownership;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
