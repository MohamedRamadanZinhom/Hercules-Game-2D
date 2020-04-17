/** @author Z. Mohamed Osama */

package com.engine.loader;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.engine.exception.NotDirectoryException;
import com.engine.exception.NotExistFileException;

class Map {
	
	/**
	 * Attributes
	 * -----------
	 * tiledMap : TiledMap {@link #tiledMap}
	 * map : OrthogonalTiledMapRenderer {@link #map}
	 * -----------
	 * Methods
	 * -----------
	 * render : {@link #render(OrthographicCamera)}
	 * 	Render TiledMap.
	 * 
	 * dispose : {@link #dispose()}
	 */
	
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer map;
	
	public Map(String path,
			String mapFile) throws NotDirectoryException, NotExistFileException {
		
		String errorMsg_1 = "The path :" + path + " is not a directory";
		
		String errorMsg_2 = "The file :" + mapFile
				+ " doesn't exist in the dir :" + path;
		
		FileHandler.isDir(path, errorMsg_1); // Check if path is a directory
		FileHandler.isFileExist(path, mapFile, errorMsg_2); // Check if file exist.
		
		this.tiledMap = new TmxMapLoader().load(path + "/" + mapFile);
		this.map = new OrthogonalTiledMapRenderer(this.tiledMap);
	}
	
	public void render(OrthographicCamera envCam) {
		map.setView(envCam);
		map.render();
	}
	
	public void dispose() {
		tiledMap.dispose();
		map.dispose();
	}
}
