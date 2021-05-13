package de.mayogames.mett4life.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t00_mettwoch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mettwoch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(unique = true)
	private Date datum;

	private boolean isAusgefallen;

	@OneToMany(mappedBy = "mettwoch")
	private Set<Mett> mett;
}
