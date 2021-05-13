package de.mayogames.mett4life.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherController {

	@RequestMapping("/")
	public String getDefaultMessage() {
		return "<meta http-equiv='refresh' content='5; URL=http://mett4.life'>"
				+ "<h1> Diese Seite ist nicht für die Öffentlichkeit gedacht... </h1>"
				+ "<h2> Sie werden nun professionell auf eie seriöse Seite weitergeleitet. </h2>";
	}
}
