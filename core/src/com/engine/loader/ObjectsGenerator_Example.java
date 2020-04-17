/**
 * 
 */
package com.engine.loader;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.engine.exception.*;


public class ObjectsGenerator_Example {
	
	public static HashMap<String,Object>objectsRepo;
	
	public ObjectsGenerator_Example()
	{
		objectsRepo = new  HashMap<String,Object>();
	}
	
	
	@SuppressWarnings("unchecked") // The object type is a generic type
	public <Type> Type load(String loaderType,String path) throws LoaderTypeException
	{
		Object object = null;
		
		if(objectsRepo.containsKey(path))
			object = objectsRepo.get(path);
		else 
		{
			switch(loaderType)
			{
				case "image":
					object = new Texture(path);
					break;
				case "font":
					object = new BitmapFont(Gdx.files.internal(path));
					break;
				default:
					throw new LoaderTypeException("The type " 
												+ loaderType
												+ " is not loader type");
			}
			objectsRepo.put(path, object);
		}
		return (Type) object;
	}
	
	public void dispose() {
		objectsRepo.clear();
	}
}
