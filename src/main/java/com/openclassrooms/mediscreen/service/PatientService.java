package com.openclassrooms.mediscreen.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.respository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

	private PatientRepository repository;

	@Autowired
	public PatientService(PatientRepository repository) {
		super();
		this.repository = repository;
	}

	private static final Logger log = LoggerFactory.getLogger(PatientService.class);

	public Long savePatient(Patient patient) {
		log.debug("savePatient called with Patient:{} ", patient.toString());

		repository.save(patient);
		return patient.getId();
	}

	public Optional<Patient> getPatientById(Long id) {
		log.debug("getPatientById called with id:{} ", id);

		return repository.findById(id);
	}

	public List<Patient> getAllPatients() {
		log.debug("getAllPatients called ");
		return (List<Patient>) repository.findAll();

	}

	public void deletePatient(Long id) {

		log.debug("deletePatient called with id:{} ", id);

		repository.deleteById(id);

	}

	public Patient getPatientByName(String family) {
		log.debug("getPatientByName called with Given:{} ", family);

		return repository.findByFamily(family);
	}

}
