package com.app.exceptions;

public final class UnsupportedProductException extends RuntimeException {

	private static final long serialVersionUID = -1309926898215013091L;

	public UnsupportedProductException() {
		super();
	}

	public UnsupportedProductException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UnsupportedProductException(final String message) {
		super(message);
	}

	public UnsupportedProductException(final Throwable cause) {
		super(cause);
	}

}