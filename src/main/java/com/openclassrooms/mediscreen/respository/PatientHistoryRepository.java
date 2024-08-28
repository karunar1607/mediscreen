package com.openclassrooms.mediscreen.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.openclassrooms.mediscreen.entity.PatientHistory;

public interface PatientHistoryRepository extends MongoRepository<PatientHistory, String> {
	public List<PatientHistory> findByPatientId(Long patientId);

}
