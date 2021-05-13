package de.mayogames.mett4life.entity.bestellungen;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class AlleBestellungenInfos {

	private final Date startingDate;

	private final int amountOfDates;

	private final List<Dates> dates;

}
