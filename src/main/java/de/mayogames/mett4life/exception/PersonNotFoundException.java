package de.mayogames.mett4life.exception;

public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(int id) {
		super("Person mit der ID " + id + " konnte in der Datenbank nicht gefunden werden");
	}
}
