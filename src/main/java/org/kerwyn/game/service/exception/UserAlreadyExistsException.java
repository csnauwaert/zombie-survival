package org.kerwyn.game.service.exception;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1201668032414490459L;

	public UserAlreadyExistsException(final String message) {
		super(message);
    }
	
}
