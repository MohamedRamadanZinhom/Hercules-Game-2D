/** @author Z. Mohamed Osama */

package com.engine.loader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.bind.ValidationException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.world.BodyProperty;
import com.engine.world.World2D;
import com.engine.exception.*;

public class MapGenerator {
	/**
	 * Attributes ----------- mapsRepo : protected HashMap<String,Map>
	 * {@link #mapsRepo} Maps repository (check Map class).
	 * 
	 * 
	 * Methods ----------- setMap : {@link #setMap(String, String, String)} setMap :
	 * {@link #setMap(String, String, int))} renderMap
	 * {@link #renderMap(String, OrthographicCamera)}
	 */

	private static HashMap<String, Map> mapsRepo = new HashMap<String, Map>();

	public MapGenerator() {

	}

	/**
	 * Load [.tmx] (TiledMap) then put it inside the maps repository. ----------
	 * 
	 * @param path    : String - The path in which the [.tmx] file located.
	 * @param mapFile : String - The [.tmx] file name.
	 * @param id      : String - The id which used to access specific TiledMap.
	 * 
	 * @throws HashMapOverwrite if TiledMap id (key) already exists in the maps
	 *                          repository. {@link #setMap(String, String, int)}
	 */
	public static void setMap(String path, String mapFile, String id) throws OverwriteException {

		// Check if id exist
		if (mapsRepo.containsKey(id)) {
			throw new OverwriteException("Id :" + id + " already exist in the maps repository.");
		}

		/*
		 * If the id doesn't exist put id inside the repository HashMap<String,Map>.
		 */
		else {
			Map map = null;
			try {
				map = new Map(path, mapFile);
			} catch (NotExistFileException error) {
				error.printStackTrace();
			}
			mapsRepo.put(id, map);
		}
	}

	/**
	 * Load [.tmx] (TiledMap) then put it inside the maps repository.
	 * 
	 * @param path    : String - The path in which the [.tmx] file located.
	 * @param mapFile : String - The [.tmx] file name.
	 * @param id      : int - The id cast inside the mathod to String which used to
	 *                access specific TiledMap.
	 * 
	 *                Why cast id to String : setMap(String path, String mapFile,
	 *                int id) is an overloaded function which accepts overwrite to
	 *                value for the given id in the maps repository.
	 */
	public static void setMap(String path, String mapFile, int id) {

		// Cast id to String
		String strId = String.valueOf(id);

		/*
		 * Put the strId & map inside the repository HashMap<String,Map>.
		 */

		try {
			// add map to mapsRepo
			mapsRepo.put(strId, new Map(path, mapFile));
		} catch (NotExistFileException error) {
			error.printStackTrace();
		}

	}

	/**
	 * Render TiledMap with given id.
	 * 
	 * @param id     : String - The id which used to access specific map.
	 * @param envCam : OrthographicCamera - The camera used to render the TiledMap
	 *               with given id.
	 * @throws KeyException if id (key) doesn't exists in the maps repository.
	 */
	public static void renderMap(String id, OrthographicCamera envCam) throws KeyException {

		if (mapsRepo.containsKey(id))
			mapsRepo.get(id).render(envCam);
		else
			throw new KeyException("There's no map with id :" + id);
	}

	/**
	 * get Map with given id.
	 * 
	 * @param id : String - The id which used to access specific map.
	 * 
	 * @throws KeyException if id (key) doesn't exists in the maps repository.
	 */
	public static Map getMap(String id) throws KeyException {

		if (mapsRepo.containsKey(id))
			return mapsRepo.get(id);
		else
			throw new KeyException("There's no map with id :" + id);
	}

	/**
	 * get TiledMap of a Map with given id.
	 * 
	 * @param id : String - The id which used to access specific map.
	 * 
	 * @throws KeyException if id (key) doesn't exists in the maps repository.
	 */
	public static TiledMap getTiledMap(String id) throws KeyException {

		if (mapsRepo.containsKey(id))
			return mapsRepo.get(id).getTiledMap();
		else
			throw new KeyException("There's no map with id :" + id);
	}

	/**
	 * @param layerIndex : int - The id which used to access specific TiledMap.
	 * 
	 * @return layer's width in tiles.
	 */
	public static float getLayerWidth(String id, int layerIndex) {

		float width = 0f;

		try {
			width = getMap(id).getLayer(layerIndex).getWidth();
		} catch (KeyException error) {
			error.printStackTrace();
		}

		return width;
	}

	/**
	 * @param layerIndex : int - The id which used to access specific TiledMap.
	 * 
	 * @return TiledMapTileLayer: of given index.
	 */
	public static TiledMapTileLayer getLayer(String id, int layerIndex) {

		TiledMapTileLayer layer = null;

		try {
			layer = getMap(id).getLayer(layerIndex);
		} catch (KeyException error) {
			error.printStackTrace();
		}

		return layer;
	}

	/**
	 * @param layerIndex : int - The id which used to access specific TiledMap.
	 * 
	 * @return tiles' height in pixels.
	 */
	public static float getTileWidth(String id, int layerIndex) {

		float tileWidth = 0f;

		try {
			tileWidth = getMap(id).getLayer(layerIndex).getTileWidth();
		} catch (KeyException error) {
			error.printStackTrace();
		}

		return tileWidth;
	}

	/**
	 * @param layerIndex : int - The id which used to access specific TiledMap.
	 * 
	 * @return layer's height in tiles.
	 */
	public static float getLayerHeight(String id, int layerIndex) {

		float height = 0f;

		try {
			height = getMap(id).getLayer(layerIndex).getHeight();
		} catch (KeyException error) {
			error.printStackTrace();
		}

		return height;
	}

	/**
	 * @param layerIndex : int - The id which used to access specific TiledMap.
	 *
	 * @return tiles' height in pixels.
	 */
	public static float getTileHeight(String id, int layerIndex) {

		float tileHeight = 0f;

		try {
			tileHeight = getMap(id).getLayer(layerIndex).getTileHeight();
		} catch (KeyException error) {
			error.printStackTrace();
		}

		return tileHeight;
	}

	/**
	 * @param id                : String map to build world from
	 * 
	 * @param bodyTypeSignature : HashMap<Integer, BodyType> - HashMap specify body
	 *                          type, (static, dynamic, ..) for each map layer,
	 * 
	 * @param gravityX          : float
	 * 
	 * @param gravityY          : float
	 * 
	 * @param doSleep           : boolean
	 * 
	 * @return World2D object
	 * @throws ValidationException
	 */
	public static World2D buildWorld(String id, HashMap<Integer, BodyProperty> bodyTypeSignature, float gravityX,
			float gravityY, boolean doSleep, float GU) throws ValidationException {

		World2D world = new World2D(gravityX, gravityY, GU, doSleep);

		MapLayers layers = null;

		try {

			layers = getTiledMap(id).getLayers();

		} catch (KeyException error) {

			error.printStackTrace();

		}

		if (bodyTypeSignature.size() != layers.size()) {

			throw new ValidationException("Faild buildWorld(...): bodyTypeSignature.size() " + "!= layers.size()");
		}

		for (Entry<Integer, BodyProperty> signature : bodyTypeSignature.entrySet()) {

			int layerIndex = signature.getKey();
			BodyProperty bdProp = signature.getValue();

			if (bdProp == null) {
				continue; // for non - box2d layer
			}

			MapObjects objects = layers.get(layerIndex).getObjects();

			for (MapObject object : objects) {

				if (object instanceof TextureMapObject) {
					continue;
				}

				Shape shape;

				if (object instanceof RectangleMapObject) {

					shape = World2D.getRectangle((RectangleMapObject) object);
				}

				else if (object instanceof PolygonMapObject) {

					shape = World2D.getPolygon((PolygonMapObject) object);
				}

				else if (object instanceof PolylineMapObject) {

					shape = World2D.getPolyline((PolylineMapObject) object);
				}

				else if (object instanceof CircleMapObject) {

					shape = World2D.getCircle((CircleMapObject) object);
				}

				else {
					continue;
				}

				BodyType bodyType = bdProp.getBdtype();
				short categoryBits = bdProp.getCategoryBits();
				short[] bitsMask = bdProp.getBitsMask();

				float restitution = bdProp.getRestitution();
				float density = bdProp.getDensity();
				float friction = bdProp.getFriction();

				if (bodyType == BodyType.StaticBody) {
					world.createStaticBody(shape, categoryBits, bitsMask);
				}

				else if (bodyType == BodyType.DynamicBody) {
					world.createDynamicBody(shape, restitution, density, friction, categoryBits, bitsMask);

				}
			}
		}

		return world;
	}

	/**
	 * Dispose all TiledMaps and OrthogonalTiledMapRenderer objects located in the
	 * maps repository then clear the repository
	 * 
	 * @param id     : String - The id which used to access specific map.
	 * @param envCam : OrthographicCamera - The camera used to render the TiledMap
	 *               with given id.
	 */
	public static void disposeAll() {
		Iterator<Entry<String, Map>> it = mapsRepo.entrySet().iterator();

		while (it.hasNext()) {
			Map mapRef = (Map) it.next().getValue();
			mapRef.dispose();
			it.remove();
		}

		mapsRepo.clear();
	}
}