/** @author Z. Mohamed Osama */


package com.engine.loader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.exception.*;

public class MapGenerator {
	/**
	 * Attributes
	 * -----------
	 * mapsRepo : protected HashMap<String,Map> {@link #mapsRepo}
	 * 	Maps repository (check Map class).
	 * -----------
	 * Methods
	 * -----------
	 * setMap : {@link #setMap(String, String, String)}
	 * renderMap {@link #renderMap(String, OrthographicCamera)}
	 * */
	
	protected static HashMap<String,Map>mapsRepo;
	
	public MapGenerator() {
		MapGenerator.mapsRepo = new HashMap<String,Map>();
	}
	
	
	/**
	 * Load [.tmx] (TiledMap) then put it inside the maps repository.
	 * ----------
	 * @param path : String - The path in which the [.tmx] file located.
	 * @param mapFile : String - The [.tmx] file name.
	 * @param id : String - The id which used to access specific TiledMap.
	 * -----------
	 * @throws HashMapOverwrite if TiledMap id (key) already exists in the maps repository.
	 * */
	public void setMap(String path, String mapFile, String id) throws OverwriteException {
		
		// Check if id exist
		if(mapsRepo.containsKey(id)){
			throw new OverwriteException("Id :" + id
									    + " Aleady exist in maps repository :");
		}
		
		/* If the id doesn't exist put id inside 
		 * the repository HashMap<String,Map>.
		 */
		else {
			Map map = new Map(path, mapFile);
			mapsRepo.put(id, map);
		}
	}
	
	/**
	 * Render TiledMap with given id.
	 * -----------
	 * @param id : String - The id which used to access specific map.
	 * @param envCam : OrthographicCamera - The camera used to render the TiledMap with given id.
	 * -----------
	 * @throws KeyException if id (key) doesn't exists in the maps repository.
	 * */
	public void renderMap(String id, OrthographicCamera envCam) throws KeyException {
		
		if(mapsRepo.containsKey(id))
			mapsRepo.get(id).render(envCam);
		else
			throw new KeyException("There's no map with id :"+id);
	}
	
	/**
	 * Dispose all TiledMaps and OrthogonalTiledMapRenderer 
	 * objects located in the maps repository then clear the repository.
	 * -----------
	 * @param id : String - The id which used to access specific map.
	 * @param envCam : OrthographicCamera - The camera used to render the TiledMap with given id.
	 * -----------
	 * */
	public static void disposeAll() {
		 Iterator<Entry<String, Map>> it = mapsRepo.entrySet().iterator();
		 
		 while(it.hasNext()) {
			 Map mapRef = (Map)it.next().getValue();
			 mapRef.dispose();
			 it.remove();
		 }
		 
		 mapsRepo.clear();
	}
}
