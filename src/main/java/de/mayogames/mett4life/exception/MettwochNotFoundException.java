package de.mayogames.mett4life.exception;

public class MettwochNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MettwochNotFoundException(int id) {
		super("Mettwoch mit der ID " + id + " konnte in der Datenbank nicht gefunden werden");
	}
}
