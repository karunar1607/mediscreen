package com.openclassrooms.mediscreen.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mediscreen.entity.PatientHistory;
import com.openclassrooms.mediscreen.service.PatientHistoryService;

@RestController
public class PatientHistoryController {
	private static final Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	PatientHistoryService service;

	@PostMapping("/patHistory/add")
	public String createPatientHistory(@RequestBody PatientHistory patientHistory) {
		log.info("POST /patHistory/add is called with :" + patientHistory.toString());
		return service.savePatientHistory(patientHistory);
	}

	@GetMapping("/patHistory/patient")
	public List<PatientHistory> getPatientHistoryById(@RequestParam Long patientId) {
		log.info("GET /patHistory/patient is called id " + patientId);

		return service.getPatientHistoryById(patientId);
	}

	@GetMapping("/patHistory/patients")
	public List<PatientHistory> getAllPatientHistories() {
		log.info("GET /patHistory/patients is called");
		return service.getAllPatientHistories();
	}

}
