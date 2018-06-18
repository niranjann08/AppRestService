package com.app.exceptions;

public final class PasswordEmptyException extends RuntimeException {

	private static final long serialVersionUID = -5741392063910915678L;

	public PasswordEmptyException() {
		super();
	}

	public PasswordEmptyException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public PasswordEmptyException(final String message) {
		super(message);
	}

	public PasswordEmptyException(final Throwable cause) {
		super(cause);
	}

}