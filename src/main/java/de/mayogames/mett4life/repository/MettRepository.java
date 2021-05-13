package de.mayogames.mett4life.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mayogames.mett4life.entity.Mett;

public interface MettRepository extends JpaRepository<Mett, Integer> {

}
