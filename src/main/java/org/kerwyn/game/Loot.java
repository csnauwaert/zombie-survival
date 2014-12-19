package org.kerwyn.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOOTS")
public class Loot {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private Long number;
	
	@ManyToOne(targetEntity=Crew.class)
	private Crew ownership;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Crew getOwnership() {
		return ownership;
	}

	public void setOwnership(Crew ownership) {
		this.ownership = ownership;
	}

	public long getId() {
		return id;
	}
	
	

}
