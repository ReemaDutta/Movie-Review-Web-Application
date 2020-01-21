package com.my.moviereview1.exception;

public class UserException extends Exception {
	
	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable clause) {
		super(message, clause);
	}
}
