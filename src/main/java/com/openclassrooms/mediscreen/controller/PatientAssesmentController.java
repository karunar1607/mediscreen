package com.openclassrooms.mediscreen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mediscreen.service.PatientAssesmentService;

@RestController
public class PatientAssesmentController {
	private static final Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	PatientAssesmentService service;

	@GetMapping("/assess/id={id}")
	public String getPatientAssesmentById(@PathVariable Long id) throws JsonMappingException, JsonProcessingException {
		log.info("GET /assess/id called with patient id " + id);
		return service.getPatientAssesmentById(id);

	}

	@GetMapping("/assess/familyName={familyName}")
	public String getPatientAssesmentByFamilyName(@PathVariable String familyName)
			throws JsonMappingException, JsonProcessingException {
		log.info("GET /assess/familyName called with family name " + familyName);
		return service.getPatientAssesmentByName(familyName);

	}

}
