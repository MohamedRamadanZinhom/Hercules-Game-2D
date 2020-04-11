package com.engine.exception;

public abstract class BaseEngineError extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8318485166912390640L;

	public BaseEngineError(String message)
	{
		super(message);
	}
}
