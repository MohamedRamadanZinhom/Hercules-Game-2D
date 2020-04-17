/**@author Z. Mohamed Osama*/

package com.engine.loader;

import com.badlogic.gdx.Gdx;
import com.engine.exception.NotDirectoryException;
import com.engine.exception.NotExistFileException;

public class FileHandler {
	

	/**
	 * Check if the path is a directory
	 * ----------
	 * @param path : String - The directory's path.
	 * -----------
	 * @return boolean : true if the given path is a directory and false otherwise.
	 * */
	public static boolean isDir(String path) {
		
		boolean dir = Gdx.files
				.external(path).isDirectory();
		if(dir)
			return true;
		else
			return false;
	}
	
	/**
	 * Check if the path is a directory
	 * ----------
	 * @param path : String - The directory's path.
	 * -----------
	 * @throws NotDirectoryException : if the given path is not a directory.
	 * */
	public static void isDir(String path
			, String errorMsg) throws NotDirectoryException {
		
		// isDir(String) is an overloaded (function) method.
		boolean dir = FileHandler.isDir(path); 
		
		if(dir == false)
			throw new NotDirectoryException(errorMsg);
	}
	
	/**
	 * Check if the file exist
	 * ----------
	 * @param path : String - The path in which the file located.
	 * @param fileName : String 
	 * -----------
	 * @return boolean : true if the file exist and false otherwise.
	 * */
	public static boolean isFileExist(String path, String fileName) {
		
		boolean exist = Gdx.files.external(path + "/" + fileName).exists();
		
		if(exist)
			return true;
		else
			return false;
	}
	
	/**
	 * Check if the file exist
	 * ----------
	 * @param path : String - The path in which the file located.
	 * @param fileName : String 
	 * -----------
	 * @throws NotExistFileException : if the file doesn't exist.
	 * */
	public static void isFileExist(String path, String fileName
			, String errorMsg) throws NotExistFileException {
		
		// isFileExist(String, String) is an overloaded (function) method.
		boolean exist = FileHandler.isFileExist(path, fileName);
		
		if(exist == false)
			throw new NotExistFileException(errorMsg);
		
	}
}
