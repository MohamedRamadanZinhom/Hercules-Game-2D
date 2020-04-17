package com.engine.exception;

public abstract class BaseEngineException extends Exception {


	private static final long serialVersionUID = 9221849881019282125L;

	public BaseEngineException(String message)
	{
		super(message);
	}
}
