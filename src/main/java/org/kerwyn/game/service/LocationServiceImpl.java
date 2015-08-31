package org.kerwyn.game.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.kerwyn.game.config.GameConfig;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.entities.Loot;
import org.kerwyn.game.map.Map;
import org.kerwyn.game.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private GameConfig config;

	private Logger log = Logger.getLogger(LocationService.class);

	@Override
	public Set<Loot> getLocationLoot(Location tile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Human> getLocationHuman(Location tile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findEmptyLocation() {
		for (int i = 0; i<2; i++){
			List<Location> empty_locs = locationRepository.findBycountHuman(i);
			if (empty_locs.size() > 0){
				Random rand = new Random();
				int r_index = rand.nextInt(empty_locs.size());
				return empty_locs.get(r_index);
			}
		}
		//TODO throw Exception here since we did not find any tile with less than 2
		//humans, which means that server is probably overpopulated.
		return null;
	}

	@Override
	@Transactional
	public boolean createMap(int width, int height) {
		//if we already has a record in location, that means that map exists
		//so do nothing.
		log.info("Creating map");
		if (locationRepository.exists(1L)){
			log.info("Map already exists");
			return false;
		}
		for (int i=0; i<width;i++){
			for (int j=0; j<height; j++){
				String coord = Map.convertCoord(i, j);
				//save tile in database
				Location loc = new Location(coord, 1);
				locationRepository.save(loc);
			}
		}
		return true;

	}

	@Override
	@Transactional
	public boolean loadMap(String file) {
		log.info("Loading map from file: "+file);
		LinkedList<Location> locs = new LinkedList<Location>();
		if (locationRepository.count() > 0){
			log.info("Map already exists");
			return false;
		}
		try {
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("cell");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					int x = Integer.parseInt(eElement.getAttribute("x"));
					int y = Integer.parseInt(eElement.getAttribute("y"));
					int type = Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent());

					if (x < 0 || y <0){
						log.error("Wrong value for 'x, y' coordinate in xml file: coordinate should not be negative");
						log.error("Cell x,y coord given by xml are ("+x+","+y+")");
						System.exit(0);
					}
					if (type < 0 || type > config.getMaxTileType()) {
						log.error("Wrong value for 'type' in xml file, 'type' can't be negative or greater than "+config.getMaxTileType());
						log.error("Check node whose x,y value are ("+x+","+y+")");
						System.exit(0);
					}

					locs.add(new Location(x, y, type));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		locationRepository.save(locs);
		log.info("Map has been successfully loaded");
		return true;
	}

}
