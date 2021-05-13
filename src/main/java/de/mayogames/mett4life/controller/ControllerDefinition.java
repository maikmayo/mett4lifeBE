package de.mayogames.mett4life.controller;

import java.util.List;

public interface ControllerDefinition<Entity, Dto> {

	/**
	 * GET single entry
	 * 
	 * @param id id of the entry
	 * @return dataset
	 */
	public Dto getSingleEntryWithID(int id);

	/**
	 * GET
	 * 
	 * @return all datasets of table
	 */
	public List<Dto> getAll();

	/**
	 * POST
	 * 
	 * @param newEntry new Entry to insert into table
	 * @return new Entry
	 */
	public Dto add(Entity newEntry);

	/**
	 * PUT
	 * 
	 * @param updatedEntry updated Entry
	 * @param id           ID of the entry that needs to be updated
	 * @return updated Entry
	 */
	public Dto update(Entity updatedEntry, int id);

	/**
	 * DELETE
	 * 
	 * @param id ID of the entry that needs to be deleted
	 */
	public void delete(int id);

	/**
	 * Konvertiert ein Entity zu dem dazugehörigen DTO
	 * 
	 * @param entity Datenbank-Entity
	 * @return DTO
	 */
	public Dto convertEntityToDto(Entity entity);

	/**
	 * Konvertiert ein DTO zu dem dazugehörigen Entity
	 * 
	 * @param dto
	 * @return Entity
	 */
	public Entity convertDtoToEntity(Dto entity);
}
