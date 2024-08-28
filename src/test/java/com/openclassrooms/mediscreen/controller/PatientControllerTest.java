package com.openclassrooms.mediscreen.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.entity.PatientHistory;
import com.openclassrooms.mediscreen.service.PatientService;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {
	@Mock
	PatientService patientService;
	@InjectMocks
	PatientController controller;

	@Test
	void testGetPatientByIdLong() {
		long id = 1;
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");

		// Act
		doReturn(Optional.of(patient)).when(patientService).getPatientById(id);
		Optional<Patient> patient1 = controller.getPatientById(id);
		// Assert
		assertNotNull(patient1);
	}

	@Test
	void testGetPatientByIdString() {
		// Arrange
		String name = "user";
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");
		// Act
		doReturn(patient).when(patientService).getPatientByName(name);
		Patient patient1 = controller.getPatientById(name);
		// Assert
		assertEquals(patient1.getFamily(), patient.getFamily());
	}

	@Test
	void testGetAllPatients() {
		// Arrange
		long id = 1;
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patient);
		// Act
		doReturn(patientList).when(patientService).getAllPatients();
		List<Patient> patient1 = controller.getAllPatients();
		// Assert
		assertEquals(patient1.size(), patientList.size());
	}

	@Test
	void testCreatePatient() {
		// Arrange
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");
		Long returnId = 1l;
		// Act
		doReturn(returnId).when(patientService).savePatient(patient);
		Long id = controller.createPatient(patient);
		// Assert
		assertEquals(returnId, id);
	}

	@Test
	void testUpdatePatient() {
		// Arrange
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");
		Long returnId = 1l;
		// Act
		doReturn(returnId).when(patientService).savePatient(patient);
		Long id = controller.updatePatient(patient);
		// Assert
		assertEquals(returnId, id);
	}

	@Test
	void testDeletePatient() {
		// Arrange
		long id = 1l;
		// Act
		// Assert
		try {
			controller.deletePatient(id);
		} catch (Exception e) {
			fail("Exception was thrown");
		}

	}

}
