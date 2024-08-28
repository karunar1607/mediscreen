package com.openclassrooms.mediscreen.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.service.PatientService;

@RestController
public class PatientController {
	private static final Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	PatientService patientService;

	@GetMapping("/patient/id={id}")
	public Optional<Patient> getPatientById(@PathVariable Long id) {
		log.info("GET /patient called with patient id" + id);
		return patientService.getPatientById(id);
	}

	@GetMapping("/patient/familyName={familyName}")
	public Patient getPatientById(@PathVariable String familyName) {
		log.info("GET /patient called with family name " + familyName);
		return patientService.getPatientByName(familyName);
	}

	@GetMapping("/patients")
	public List<Patient> getAllPatients() {
		log.info("GET /patients called");

		return patientService.getAllPatients();
	}

	@PostMapping("/patient")
	public Long createPatient(@RequestBody Patient patient) {
		log.info("POST /patient called with :" + patient.toString());

		return patientService.savePatient(patient);
	}

	@PutMapping("/patient")
	public Long updatePatient(@RequestBody Patient patient) {
		log.info("PUT /patient called  with :\"+patient.toString()");

		return patientService.savePatient(patient);
	}

	@DeleteMapping("/patient")
	public void deletePatient(@RequestParam Long id) {
		log.info("DELETE /patient called with id " + id);

		patientService.deletePatient(id);
	}

}
