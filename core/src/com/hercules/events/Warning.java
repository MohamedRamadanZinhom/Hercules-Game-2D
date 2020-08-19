package com.hercules.events;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Warning {

	/**
	 * Log - warnings
	 * 
	 * @param className
	 * @param warningMessage
	 */
	public static void setWarning(String entityName, String warningMessage) {

		Logger logger = Logger.getLogger(entityName);

		logger.setLevel(Level.WARNING);

		logger.warning(warningMessage);
	}
}
