package org.kerwyn.game.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game")
public class GameConfig {
	/**
	 * Constant from properties files
	 */
	
	private String adminLogin;
	private String adminPassword;
	
	private int crewStartingHuman;
	private int maxTileType;
	
	private int humanStartConsumeFoodLevel;
	private int humanStartFood;
	private int humanStartCarryCapacity;
	private int humanStartMaxNumberInjury;
	
	
	/**
	 * Getter and Setter
	 * 
	 */
	
	public String getAdminLogin() {
		return adminLogin;
	}
	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public int getCrewStartingHuman() {
		return crewStartingHuman;
	}
	public void setCrewStartingHuman(int crewStartingHuman) {
		this.crewStartingHuman = crewStartingHuman;
	}
	public int getMaxTileType() {
		return maxTileType;
	}
	public void setMaxTileType(int maxTileType) {
		this.maxTileType = maxTileType;
	}
	public int getHumanStartConsumeFoodLevel() {
		return humanStartConsumeFoodLevel;
	}
	public void setHumanStartConsumeFoodLevel(int humanStartConsumeFoodLevel) {
		this.humanStartConsumeFoodLevel = humanStartConsumeFoodLevel;
	}
	public int getHumanStartFood() {
		return humanStartFood;
	}
	public void setHumanStartFood(int humanStartFood) {
		this.humanStartFood = humanStartFood;
	}
	public int getHumanStartCarryCapacity() {
		return humanStartCarryCapacity;
	}
	public void setHumanStartCarryCapacity(int humanStartCarryCapacity) {
		this.humanStartCarryCapacity = humanStartCarryCapacity;
	}
	public int getHumanStartMaxNumberInjury() {
		return humanStartMaxNumberInjury;
	}
	public void setHumanStartMaxNumberInjury(int humanStartMaxNumberInjury) {
		this.humanStartMaxNumberInjury = humanStartMaxNumberInjury;
	}
	

}
