package com.openclassrooms.mediscreen.controller;

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
import com.openclassrooms.mediscreen.service.PatientHistoryService;

@ExtendWith(MockitoExtension.class)

class PatientHistoryControllerTest {
	@Mock
	PatientHistoryService service;
	@InjectMocks
	PatientHistoryController controller;

	@Test
	void testCreatePatientHistory() {
		// Arrange
		PatientHistory patientHistory = new PatientHistory();
		patientHistory.setPatientId(1l);
		patientHistory.setNotes("Test Notes");
		String returnString = "abcd";
		// Act
		doReturn(returnString).when(service).savePatientHistory(patientHistory);
		String response = controller.createPatientHistory(patientHistory);
		// Assert
		assertEquals(returnString, response);
	}

	@Test
	void testGetPatientHistoryById() {
		// Arrange
		long id = 1l;
		PatientHistory patientHistory = new PatientHistory();
		patientHistory.setPatientId(1l);
		patientHistory.setNotes("Test Notes");
		List<PatientHistory> pHistoryList = new ArrayList<PatientHistory>();
		pHistoryList.add(patientHistory);
		// Act
		doReturn(pHistoryList).when(service).getPatientHistoryById(id);
		List<PatientHistory> responseList = controller.getPatientHistoryById(id);
		// Assert
		assertEquals(responseList.get(0).getNotes(), pHistoryList.get(0).getNotes());
	}

	@Test
	void testGetAllPatientHistories() {
		// Arrange
		PatientHistory patientHistory = new PatientHistory();
		patientHistory.setPatientId(1l);
		patientHistory.setNotes("Test Notes");
		List<PatientHistory> pHistoryList = new ArrayList<PatientHistory>();
		pHistoryList.add(patientHistory);
		// Act
		doReturn(pHistoryList).when(service).getAllPatientHistories();
		List<PatientHistory> responseList = controller.getAllPatientHistories();
		// Assert
		assertEquals(responseList.get(0).getNotes(), pHistoryList.get(0).getNotes());
	}

}
