/** @author Z. Mohamed Osama */

package com.engine.loader;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.engine.exception.NotExistFileException;

class Map {

	/**
	 * Attributes ----------- tiledMap : TiledMap {@link #tiledMap} map :
	 * OrthogonalTiledMapRenderer {@link #map} ----------- Methods -----------
	 * render : {@link #render(OrthographicCamera)} Render TiledMap.
	 * 
	 * dispose : {@link #dispose()}
	 */

	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer map;

	public Map(String path, String mapFile) throws NotExistFileException {

		String errorMsg = "The file :" + mapFile + " doesn't exist in the dir :" + path;

		FileHandler.isFileExist(path, mapFile, errorMsg); // Check if file exist.

		this.tiledMap = new TmxMapLoader().load(path + mapFile);
		this.map = new OrthogonalTiledMapRenderer(this.tiledMap);

	}

	public void render(OrthographicCamera envCam) {
		map.setView(envCam);
		map.render();
	}
	
	/**
	 * @param layerIndex : int - The id which used to access specific TiledMap.
	 * -----------
	 * @return TiledMapTileLayer : layer at given indexof the Tiledmap.
	 */
	public TiledMapTileLayer getLayer(int layerIndex) {

		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(layerIndex);

		return layer;
	}

	public void dispose() {
		tiledMap.dispose();
		map.dispose();
	}
}
