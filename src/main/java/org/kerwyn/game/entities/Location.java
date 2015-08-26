package org.kerwyn.game.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kerwyn.game.map.Map;

/**
 * The Class Location.
 */
@Entity
@Table(name = "LOCATIONS")
public class Location extends AbstractEntity {

	/** The coordinate in a format x:y. */
	@Column(nullable = false)
	private String coordinate;
	
	@Column(nullable = false)
	private Integer x;
	
	@Column(nullable = false)
	private Integer y;
	
	/** The type of the tile example: 1:grass, 2:sand, ...*/
	@Column(nullable = false)
	private Integer tile_type;

	/** The loots. */
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, targetEntity = Loot.class, fetch = FetchType.LAZY)
	private Set<Loot> loots;

	/** The building. */
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, targetEntity = Building.class, fetch = FetchType.LAZY)
	private Set<Building> buildings;

	/** The humans. */
	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Human> humans;
	
	@Column()
	private Integer countHuman;

	/** The zombies. */
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, targetEntity = Zombie.class, fetch = FetchType.LAZY)
	private Set<Zombie> zombies;

	public Location(){
		
	}
	
	public Location(String coordinate, int tile_type){
		this.coordinate = coordinate;
		this.tile_type = tile_type;
		int[] tempcoord = Map.convertCoord(coordinate);
		this.x = tempcoord[0];
		this.y = tempcoord[1];
		this.countHuman = 0;
		this.humans = new HashSet<Human>();
	}
	
	public Location(int x, int y, int tile_type) {
		this.tile_type = tile_type;
		this.x = x;
		this.y = y;
		this.coordinate = Map.convertCoord(x, y);
		this.countHuman = 0;
		this.humans = new HashSet<Human>();
	}

	/**
	 * Getter and Setter
	 */

	public String getCoordinate() {
		return coordinate;
	}
	
	public int getTile_Type() {
		return tile_type;
	}

	public Set<Loot> getLoots() {
		return loots;
	}

	public Set<Building> getBuildings() {
		return buildings;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public Set<Human> getHumans() {
		return humans;
	}

	public Set<Zombie> getZombies() {
		return zombies;
	}
	
	/**
	 * Methods
	 */
	
	public void addHuman(Human human) {
		if (!this.destroy && !this.humans.contains(human)) {
			if (human.getLocation() != this){
				human.setLocation(this);
			}
			this.humans.add(human);
			this.countHuman += 1;
			
		}
	}
	
	public void removeHuman(Human human) {
		if (!this.destroy && this.humans.contains(human)) {
			this.humans.remove(human);
			this.countHuman -= 1;
		}
	}
	
	public Integer getCountHuman(){
		return this.countHuman;
	}
}
