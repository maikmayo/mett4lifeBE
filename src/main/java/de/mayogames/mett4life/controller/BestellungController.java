package de.mayogames.mett4life.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.mayogames.mett4life.entity.bestellungen.AlleBestellungen;
import de.mayogames.mett4life.entity.bestellungen.AlleBestellungenInfos;
import de.mayogames.mett4life.entity.bestellungen.BestellungInfo;
import de.mayogames.mett4life.entity.bestellungen.BestellungProPerson;
import de.mayogames.mett4life.entity.bestellungen.Dates;
import de.mayogames.mett4life.repository.MettwochRepository;
import de.mayogames.mett4life.repository.MettwochRepository.BestellungDto;

@RestController
public class BestellungController {

	@Autowired
	private MettwochRepository mettwochRepo;

	@CrossOrigin
	@GetMapping("/bestellung/{startingDate}/{amount}")
	public AlleBestellungen getBestellugnen(@PathVariable Date startingDate, @PathVariable int amount) {
		// Alle Mettwochs holen Date[] mettwochs = null;
		List<Dates> mettwochs = mettwochRepo.getMettwochs(startingDate, amount);

		// Infos zu den angefragten Bestellungen erstellen
		AlleBestellungenInfos alleBestellungenInfos = new AlleBestellungenInfos(mettwochs.get(0).getDatum(), amount,
				mettwochs);

		// Eigentliche Bestellungen erstellen
		List<BestellungDto> databaseObject = mettwochRepo.getAllBestellungen(mettwochs.get(0).getDatum().toString(),
				mettwochs.get(mettwochs.size() - 1).getDatum().toString());
		List<BestellungProPerson> bestellungProPerson = transformDatabaseObject(databaseObject, amount);

		// Bestellinfos und eigentliche Bestellungen zusammenfügen
		AlleBestellungen alleBestellungen = new AlleBestellungen(alleBestellungenInfos, bestellungProPerson);

		return alleBestellungen;
	}

	/**
	 * Transformiert das Datenbank-Objekt in eine Liste von "BestellungProPerson"
	 * 
	 * @param databaseObject Datenbankobjekt
	 * @param amount         Anzahl der gewünschten Daten pro Person
	 * @return Transformiertes Datenbankobjekt
	 */
	private List<BestellungProPerson> transformDatabaseObject(List<BestellungDto> databaseObject, int amount) {
		System.out.println(databaseObject.size());
		List<BestellungProPerson> bestellungProPerson = new ArrayList<BestellungProPerson>();

		// Durchlaufen aller Einträge in Schritten, je nach amount
		for (int i = 0; i < databaseObject.size(); i += amount) {
			// Info für jede Person
			List<BestellungInfo> bestellungInfo = new ArrayList<BestellungInfo>();

			// Bestellungen erstellen
			for (int e = 0; e < amount; e++) {
				BestellungDto data = databaseObject.get(i + e);

				int mettID = data.getMettID() == null ? -1 : data.getMettID();
				boolean bezahlt = data.getBezahlt() == null ? false : data.getBezahlt();
				boolean ohneMett = data.getOhneMett() == null ? false : data.getOhneMett();
				int anzahl = data.getAnzahl() == null ? 0 : data.getAnzahl();

				bestellungInfo.add(new BestellungInfo(mettID, data.getDatum(), anzahl, bezahlt, ohneMett));
			}

			// Objekt zusammenfügen und zur Liste hinzufügen
			BestellungProPerson bestellung = new BestellungProPerson(databaseObject.get(i).getPersonID(),
					databaseObject.get(i).getKuerzel(), bestellungInfo);
			bestellungProPerson.add(bestellung);
		}

		return bestellungProPerson;
	}

	@GetMapping("/debug")
	public List<BestellungDto> debug() {
		return mettwochRepo.getAllBestellungen("2020-12-01", "2020-12-31");
	}
}
