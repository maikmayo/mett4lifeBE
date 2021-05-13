package de.mayogames.mett4life.entity.bestellungen;

import java.util.List;

import lombok.Data;

@Data
public class BestellungProPerson {

	private final int personID;

	private final String kuerzel;

	private final List<BestellungInfo> bestellungen;
}
