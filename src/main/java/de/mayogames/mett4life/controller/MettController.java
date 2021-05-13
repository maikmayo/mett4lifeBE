package de.mayogames.mett4life.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.mayogames.mett4life.dto.MettDto;
import de.mayogames.mett4life.entity.Mett;
import de.mayogames.mett4life.entity.Mettwoch;
import de.mayogames.mett4life.entity.Person;
import de.mayogames.mett4life.exception.MettNotFoundException;
import de.mayogames.mett4life.repository.MettRepository;
import de.mayogames.mett4life.repository.MettwochRepository;
import de.mayogames.mett4life.repository.PersonRepository;

@RestController
public class MettController implements ControllerDefinition<Mett, MettDto> {

	@Autowired
	private MettRepository mettRepo;

	@Autowired
	private MettwochRepository mettwochRepo;

	@Autowired
	private PersonRepository personRepo;

	@Override
	@GetMapping("/mett/{mettID}")
	@ResponseBody
	public MettDto getSingleEntryWithID(@PathVariable int mettID) {
		return convertEntityToDto(mettRepo.findById(mettID).orElseThrow(() -> new MettNotFoundException(mettID)));
	}

	@Override
	@GetMapping("/mett")
	public List<MettDto> getAll() {
		List<MettDto> mettDtos = new ArrayList<MettDto>();
		for (Mett mett : mettRepo.findAll()) {
			mettDtos.add(convertEntityToDto(mett));
		}
		return mettDtos;
	}

	@Override
	@CrossOrigin
	@PostMapping("/mett1")
	public MettDto add(@RequestBody Mett newEntry) {
		System.out.println("mett add");
		return convertEntityToDto(mettRepo.save(newEntry));
	}

	@CrossOrigin
	@PostMapping("/mett")
	public MettDto add1(@RequestBody MettDto newEntry) {
		System.out.println("mett add");
		return convertEntityToDto(mettRepo.save(convertDtoToEntity(newEntry)));
	}

	@Override
	@CrossOrigin
	@PutMapping("/mett/{mettID}")
	public MettDto update(@RequestBody Mett updatedMett, @PathVariable int mettID) {
		return convertEntityToDto(mettRepo.findById(mettID).map(mett -> {
			mett.setAnzahl(updatedMett.getAnzahl());
			mett.setBezahlt(updatedMett.isBezahlt());
			mett.setOhneMett(updatedMett.isOhneMett());
			return mettRepo.save(mett);
		}).orElseGet(() -> {
			updatedMett.setId(mettID);
			return mettRepo.save(updatedMett);
		}));
	}

	@Override
	@DeleteMapping("/mett/{mettID}")
	public void delete(@PathVariable int mettID) {
		mettRepo.deleteById(mettID);
	}

	/*
	 * DTO Conversion
	 */
	@Override
	public MettDto convertEntityToDto(Mett entity) {
		MettDto dto = new MettDto(entity.getId(), entity.getAnzahl(), entity.isBezahlt(), entity.isOhneMett(),
				entity.getMettwoch().getId(), entity.getPerson().getId());
		return dto;
	}

	@Override
	public Mett convertDtoToEntity(MettDto dto) {
		System.out.println("personID: " + dto.getPersonID());
		Person person = personRepo.findById(dto.getPersonID()).orElseThrow();
		Mettwoch mettwoch = mettwochRepo.findById(dto.getMettwochID()).orElseThrow();

		Mett mett = new Mett(dto.getMettID(), dto.getAnzahl(), dto.isBezahlt(), dto.isOhneMett(), mettwoch, person);

		return mett;
	}
}
