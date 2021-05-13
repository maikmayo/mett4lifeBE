package de.mayogames.mett4life.entity.bestellungen;

import java.util.List;

import lombok.Data;

@Data
public class AlleBestellungen {

	private final AlleBestellungenInfos alleBestellungenInfos;

	private final List<BestellungProPerson> bestellungProPerson;

}
