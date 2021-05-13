package de.mayogames.mett4life.exception;

public class MettNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MettNotFoundException(int id) {
		super("Mettbestellung mit der ID " + id + " konnte in der Datenbank nicht gefunden werden");
	}
}
