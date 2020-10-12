package com.android.exception;

@SuppressWarnings("serial")
public class InvalidCommandOptionException extends AbstractSootInitializationException {
	public static String helpMessage = "please input -h or -help to display command options";
	
	public InvalidCommandOptionException() {
		super("Invalid command options, " + helpMessage);
	}
	
	public InvalidCommandOptionException(String message) {
		super(message);
	}
}
