package de.mayogames.mett4life.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {
	private int id;
	private String kuerzel;
	private String pw_hash;
	private String pc;
	private Date registration;
}
