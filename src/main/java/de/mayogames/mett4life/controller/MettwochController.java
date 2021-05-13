package de.mayogames.mett4life.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.mayogames.mett4life.dto.MettwochDto;
import de.mayogames.mett4life.entity.Mettwoch;
import de.mayogames.mett4life.exception.MettwochNotFoundException;
import de.mayogames.mett4life.repository.MettwochRepository;

@RestController
public class MettwochController implements ControllerDefinition<Mettwoch, MettwochDto> {

	@Autowired
	private MettwochRepository mettwochRepo;

	@Override
	@GetMapping("/mettwoch/{mettwochID}")
	public MettwochDto getSingleEntryWithID(@PathVariable int mettwochID) {
		return convertEntityToDto(
				mettwochRepo.findById(mettwochID).orElseThrow(() -> new MettwochNotFoundException(mettwochID)));
	}

	@Override
	@GetMapping("/mettwoch")
	public List<MettwochDto> getAll() {
		List<MettwochDto> dtos = new ArrayList<MettwochDto>();
		for (Mettwoch entity : mettwochRepo.findAll()) {
			dtos.add(convertEntityToDto(entity));
		}
		return dtos;
	}

	@Override
	@PostMapping("/mettwoch")
	public MettwochDto add(@RequestBody Mettwoch newMettwoch) {
		return convertEntityToDto(mettwochRepo.save(newMettwoch));
	}

	@Override
	@PutMapping("/mettwoch/{mettwochID}")
	public MettwochDto update(@RequestBody Mettwoch updatedMettwoch, @PathVariable int mettwochID) {
		return convertEntityToDto(mettwochRepo.findById(mettwochID).map(mettwoch -> {
			mettwoch.setAusgefallen(updatedMettwoch.isAusgefallen());
			mettwoch.setDatum(updatedMettwoch.getDatum());
			return mettwochRepo.save(mettwoch);
		}).orElseGet(() -> {
			updatedMettwoch.setId(mettwochID);
			return mettwochRepo.save(updatedMettwoch);
		}));
	}

	@Override
	@DeleteMapping("/mettwoch/{id}")
	public void delete(@PathVariable int mettwochID) {
		mettwochRepo.deleteById(mettwochID);
	}

	/*
	 * DTO Conversion
	 */
	@Override
	public MettwochDto convertEntityToDto(Mettwoch entity) {
		MettwochDto dto = new MettwochDto(entity.getId(), entity.getDatum(), entity.isAusgefallen());
		return dto;
	}

	@Override
	public Mettwoch convertDtoToEntity(MettwochDto dto) {
		Mettwoch mettwoch = new Mettwoch(0, null, false, null);

		return mettwoch;
	}
}
