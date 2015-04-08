package org.kerwyn.game.map;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class Map {
	
	public static final String convertCoord(int x, int y){
		return x+":"+y;
	}
	
	public static final int[] convertCoord(String coord){
		String[] split = coord.split(":");
		int[] raw_coord = new int[2];
		raw_coord[0] = Integer.parseInt(split[0]);
		raw_coord[1] = Integer.parseInt(split[1]);
		return raw_coord;
	}

}
