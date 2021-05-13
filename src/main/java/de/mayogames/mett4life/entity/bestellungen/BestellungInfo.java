package de.mayogames.mett4life.entity.bestellungen;

import java.sql.Date;

import lombok.Data;

@Data
public class BestellungInfo {

	private final int mettID;

	private final Date datum;

	private final int anzahl;

	private final boolean bezahlt;

	private final boolean ohneMett;
}
