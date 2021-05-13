package de.mayogames.mett4life.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t00_mett", uniqueConstraints = @UniqueConstraint(columnNames = { "mettwoch_id", "person_id" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mett {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private int anzahl;

	@NotNull
	private boolean isBezahlt;

	@NotNull
	private boolean isOhneMett;

	@ManyToOne
	@JoinColumn(name = "mettwoch_id")
	private Mettwoch mettwoch;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
}
