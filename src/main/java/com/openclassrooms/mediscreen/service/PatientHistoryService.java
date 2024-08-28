package com.openclassrooms.mediscreen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreen.entity.PatientHistory;
import com.openclassrooms.mediscreen.respository.PatientHistoryRepository;
@Service
public class PatientHistoryService {
	
	private PatientHistoryRepository respository;
	
	
	@Autowired
	public PatientHistoryService(PatientHistoryRepository respository) {
		super();
		this.respository = respository;
	}
	public String savePatientHistory(PatientHistory patientHistory) {

		respository.save(patientHistory);
		return patientHistory.getId();
	}
	public List<PatientHistory> getPatientHistoryById(Long patientId) {
		return respository.findByPatientId(patientId);
	}
	public List<PatientHistory> getAllPatientHistories() {
		return respository.findAll();
	}


}
