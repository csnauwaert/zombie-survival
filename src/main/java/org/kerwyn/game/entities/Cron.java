package org.kerwyn.game.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CRON")
public class Cron extends AbstractEntity {

	@Column(nullable = false)
	private String name;
	
	@Column
	private Timestamp last_execution;
	
	protected Cron() {
		super();
	}
	
	public Cron(String name, Timestamp last_execution) {
		this.name = name;
		this.last_execution = last_execution;
	}

	public Timestamp getLast_execution() {
		return last_execution;
	}

	public void setLast_execution(Timestamp last_execution) {
		this.last_execution = last_execution;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
