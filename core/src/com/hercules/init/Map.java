/** @author Z. Mohamed Osama */

package com.hercules.init;

import com.engine.exception.OverwriteException;
import com.engine.loader.MapGenerator;

public abstract class Map {

	public Map(String mainDir, String[] mapFname, String[] mapId) {

		for (int i = 0; i < mapId.length; i++) {

			try {

				MapGenerator.setMap(mainDir, mapFname[i], mapId[i]);

			} catch (OverwriteException error) {

				error.printStackTrace();
			}
		}
	}

}
