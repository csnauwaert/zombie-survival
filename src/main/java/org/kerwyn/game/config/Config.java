package org.kerwyn.game.config;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.kerwyn.game.service.exception.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class Config {
	
	@Autowired
	private Environment env;

	private HashMap<String, String> properties_hashmap;
	
	private Logger log = Logger.getLogger(Config.class);
	
	public Config(){
		properties_hashmap = new HashMap<String, String>();
	}

	@PostConstruct
	public void init(){
		properties_hashmap.put("crew.start_human", env.getProperty("crew.start_human"));
		properties_hashmap.put("tile.max_tile", env.getProperty("tile.max_tile"));
		properties_hashmap.put("admin.login", env.getProperty("admin.login"));
		properties_hashmap.put("admin.password", env.getProperty("admin.password"));
	}
	
	public String get(String properties) {
		String ret = properties_hashmap.get(properties);
		if (ret == null){
			String message = String.format("Could not find a configuration " +
					"property with name: %s",properties);
			log.error(message);
			throw new ConfigurationException(message);
		}
		return ret;
	}
	
}
