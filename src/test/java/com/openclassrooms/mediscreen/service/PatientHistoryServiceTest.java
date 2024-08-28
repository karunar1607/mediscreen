package com.openclassrooms.mediscreen.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.mediscreen.entity.PatientHistory;
import com.openclassrooms.mediscreen.respository.PatientHistoryRepository;

@ExtendWith(MockitoExtension.class)
class PatientHistoryServiceTest {
	@Mock
	PatientHistoryRepository respository;
	@InjectMocks
	PatientHistoryService service;

	@Test
	void testSavePatientHistory() {
		// Arrange
		PatientHistory patientHistory = new PatientHistory();
		patientHistory.setPatientId(1l);
		patientHistory.setNotes("Test notes");
		// Act
		service.savePatientHistory(patientHistory);

	}

	@Test
	void testGetPatientHistoryById() {
		// Arrange
		long id = 1;
		List<PatientHistory> patHistoryList = new ArrayList<>();
		PatientHistory patientHistory = new PatientHistory();
		patientHistory.setPatientId(1l);
		patientHistory.setNotes("Test notes");
		patHistoryList.add(patientHistory);
		// Act
		doReturn(patHistoryList).when(respository).findByPatientId(anyLong());
		List<PatientHistory> pHistory = service.getPatientHistoryById(id);
		// Assert
		assertEquals(pHistory.size(), patHistoryList.size());
	}

	@Test
	void testGetAllPatientHistories() {
		// Arrange

		List<PatientHistory> patHistoryList = new ArrayList<>();
		PatientHistory patientHistory = new PatientHistory();
		patientHistory.setPatientId(1l);
		patientHistory.setNotes("Test notes");
		patHistoryList.add(patientHistory);
		// Act
		doReturn(patHistoryList).when(respository).findAll();
		List<PatientHistory> pHistory = service.getAllPatientHistories();
		// Assert
		assertEquals(pHistory.size(), patHistoryList.size());
	}

}
