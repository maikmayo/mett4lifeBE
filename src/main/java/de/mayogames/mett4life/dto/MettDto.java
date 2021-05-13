package de.mayogames.mett4life.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MettDto {

	private int mettID;

	private int anzahl;

	private boolean bezahlt;

	private boolean ohneMett;

	private int mettwochID;

	private int personID;
}
