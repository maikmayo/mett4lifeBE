package de.mayogames.mett4life.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.mayogames.mett4life.entity.Mettwoch;
import de.mayogames.mett4life.entity.bestellungen.Dates;

public interface MettwochRepository extends JpaRepository<Mettwoch, Integer> {

	@Query(value = "select id mettwochID, datum datum from t00_mettwoch where datum >= ?1 order by datum limit ?2", nativeQuery = true)
	List<Dates> getMettwochs(Date startigDate, int anzahl);

	@Query(value = "SELECT person.id         personID,\r\n" + "		 person.kuerzel    kuerzel,\r\n"
			+ "		 mettwoch.id       mettwochID,\r\n" + "		 mettwoch.datum    datum,\r\n"
			+ "		 mett.id       mettID,\r\n" + "		 mett.anzahl       anzahl,\r\n"
			+ "		 mett.is_bezahlt   bezahlt,\r\n" + "		 mett.is_ohne_Mett ohneMett\r\n"
			+ "FROM   t00_person        person\r\n" + "JOIN   t00_mettwoch      mettwoch\r\n"
			+ "LEFT JOIN t00_mett  mett     \r\n" + "ON     person.id   = mett.person_id\r\n"
			+ "AND    mettwoch.id = mett.mettwoch_id\r\n"
			+ "WHERE  DATUM BETWEEN  STR_TO_DATE(?1, \"%Y-%m-%d\") AND STR_TO_DATE(?2, \"%Y-%m-%d\")\r\n"
			+ "ORDER BY kuerzel, datum;", nativeQuery = true)
	List<BestellungDto> getAllBestellungen(String firstDate, String secondDate);

	public static interface BestellungDto {
		Integer getPersonID();

		String getKuerzel();

		Integer getMettwochID();

		Date getDatum();

		Integer getMettID();

		Integer getAnzahl();

		Boolean getBezahlt();

		Boolean getOhneMett();

	}

}
