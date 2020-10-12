package com.android.exception;

@SuppressWarnings("serial")
public class InvalidFileConfigException extends AbstractSootInitializationException {
	
	private static String lineError = "Invalid config line";
	
	public static String getLineError() {
		return lineError;
	}
	
	public InvalidFileConfigException() {
		super(getLineError());
	}
	
	public InvalidFileConfigException(String message) {
		super(message);
	}
}
