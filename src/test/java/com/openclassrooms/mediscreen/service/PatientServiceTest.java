package com.openclassrooms.mediscreen.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.respository.PatientRepository;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
	@Mock
	PatientRepository repository;
	@InjectMocks
	PatientService patientService;

	@Test
	void testSavePatient() {
		// Arrange
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");
		// Act
		// Assert
		try {
			patientService.savePatient(patient);
		} catch (Exception e) {
			fail("Exception was thrown");
		}

	}

	@Test
	void testGetPatientById() {
		// Arrange
		long id = 1;
		Patient patient = new Patient();
		patient.setGiven("new");
		patient.setFamily("user");
		patient.setDob("12-12-2010");
		patient.setSex("F");
		patient.setAddress("100 test steet");
		patient.setPhone("8045562378");

		// Act
		doReturn(Optional.of(patient)).when(repository).findById(anyLong());
		Optional<Patient> patient1 = patientService.getPatientById(id);
		// Assert
		assertNotNull(patient1);
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
		doReturn(patientList).when(repository).findAll();
		List<Patient> patient1 = patientService.getAllPatients();
		// Assert
		assertEquals(patient1.size(), patientList.size());
	}

	@Test
	void testDeletePatient() {
		// Arrange
		long id = 1;
		// Act
		// Assert
		try {
			patientService.deletePatient(id);
		} catch (Exception e) {
			fail("Exception was thrown");
		}
	}

	@Test
	void testGetPatientByName() {
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
		doReturn(patient).when(repository).findByFamily(name);
		Patient patient1 = patientService.getPatientByName(name);
		// Assert
		assertEquals(patient1.getFamily(), patient.getFamily());

	}

}
