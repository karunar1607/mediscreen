package com.openclassrooms.mediscreen.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreen.entity.PatientHistory;
import com.openclassrooms.mediscreen.respository.PatientHistoryRepository;

@Service
public class PatientHistoryService {
	private static final Logger log = LoggerFactory.getLogger(PatientService.class);

	private PatientHistoryRepository respository;

	@Autowired
	public PatientHistoryService(PatientHistoryRepository respository) {
		super();
		this.respository = respository;
	}

	public String savePatientHistory(PatientHistory patientHistory) {
		log.debug("savePatientHistory called with Patient History:{} ", patientHistory.toString());

		respository.save(patientHistory);
		return patientHistory.getId();
	}

	public List<PatientHistory> getPatientHistoryById(Long patientId) {
		log.debug("getPatientHistoryById called with Patient id:{} ", patientId);

		return respository.findByPatientId(patientId);
	}

	public List<PatientHistory> getAllPatientHistories() {
		log.debug("getAllPatientHistories is called");

		return respository.findAll();
	}

}
