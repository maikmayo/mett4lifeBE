package de.mayogames.mett4life.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MettwochDto {
	private int id;
	private Date datum;
	private boolean isAusgefallen;
}
