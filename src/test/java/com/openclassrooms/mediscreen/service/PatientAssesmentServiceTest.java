package com.openclassrooms.mediscreen.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ExtendWith(MockitoExtension.class)

class PatientAssesmentServiceTest {

	@Mock
	private RestTemplate template;
	@InjectMocks
	PatientAssesmentService patientAssesmentService;

	@Test
	void testGetPatientAssesmentById_None() throws JsonMappingException, JsonProcessingException {
		// Arrange
		long id = 1;
		mockGetPatient(id);
		mockGetPatientHistory(id);
		// Act
		String output = patientAssesmentService.getPatientAssesmentById(id);
		// Assert
		assertNotNull(output);

	}

	@Test
	void testGetPatientAssesmentByName_None() throws JsonMappingException, JsonProcessingException {
		// Arrange
		String name = "user";
		long id = 1;
		mockPatientByName(name);
		mockGetPatientHistory(id);
		// Act
		String output = patientAssesmentService.getPatientAssesmentByName(name);
		// Assert
		assertNotNull(output);
	}

	@Test
	public void testGetAssesment_none() {
		// Arrange
		int count = 0;
		int age = 31;
		String sex = "M";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("None", output);
	}

	@Test
	public void testGetAssesment_boderline() {
		// Arrange
		int count = 3;
		int age = 31;
		String sex = "M";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("Borderline", output);
	}

	@Test
	public void testGetAssesment_inDanger() {
		// Arrange
		int count = 7;
		int age = 31;
		String sex = "M";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("In danger", output);
	}

	@Test
	public void testGetAssesment_earlyOnset() {
		// Arrange
		int count = 9;
		int age = 31;
		String sex = "M";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("Early Onset", output);
	}

	@Test
	public void testGetAssesment_inDanger_male_u30() {
		// Arrange
		int count = 4;
		int age = 20;
		String sex = "M";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("In danger", output);
	}

	@Test
	public void testGetAssesment_inDanger_female_u30() {
		// Arrange
		int count = 5;
		int age = 20;
		String sex = "F";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("In danger", output);
	}

	@Test
	public void testGetAssesment_earlyOnset_male_u30() {
		// Arrange
		int count = 6;
		int age = 20;
		String sex = "M";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("Early Onset", output);
	}

	@Test
	public void testGetAssesment_earlyOnset_female_u30() {
		// Arrange
		int count = 8;
		int age = 20;
		String sex = "F";
		// Act
		String output = patientAssesmentService.getAssesment(count, age, sex);
		// Assert
		assertEquals("Early Onset", output);
	}

	public void mockGetPatient(Long id) throws JsonMappingException, JsonProcessingException {
		// Arrange

		String PatientApiUrl = "http://localhost:8080/patient";

		String expectedResponseBody = "{\r\n" + "    \"id\": 1,\r\n" + "    \"family\": \"test\",\r\n"
				+ "    \"given\": \"user\",\r\n" + "    \"dob\": \"11-05-1997\",\r\n" + "    \"sex\": \"F\",\r\n"
				+ "    \"address\": \"288 Steppes mill rd ext\",\r\n" + "    \"phone\": \"8641-874-6522\"\r\n" + "}";

		ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(expectedResponseBody, HttpStatus.OK);

		when(template.getForEntity(PatientApiUrl + "/id=" + id, String.class)).thenReturn(mockResponseEntity);

	}

	public void mockGetPatientHistory(long id) throws JsonMappingException, JsonProcessingException {
		String PatientHistoryApiUrl = "http://localhost:8080/patHistory/patient?patientId=";

		String expectedResponseBody1 = "[\r\n" + "    {\r\n" + "        \"id\": \"6699282f21b29220e7c73054\",\r\n"
				+ "        \"patientId\": 1,\r\n" + "        \"notes\": \" high blood pressure\"\r\n" + "    }\r\n"
				+ "]";

		ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(expectedResponseBody1, HttpStatus.OK);

		when(template.getForEntity(PatientHistoryApiUrl + id, String.class)).thenReturn(mockResponseEntity);
	}

	public void mockPatientByName(String name) throws JsonMappingException, JsonProcessingException {

		String PatientApiUrl = "http://localhost:8080/patient";

		String expectedResponseBody2 = "{\r\n" + "    \"id\": 1,\r\n" + "    \"family\": \"test\",\r\n"
				+ "    \"given\": \"user\",\r\n" + "    \"dob\": \"11-05-1997\",\r\n" + "    \"sex\": \"F\",\r\n"
				+ "    \"address\": \"288 Steppes mill rd ext\",\r\n" + "    \"phone\": \"8641-874-6522\"\r\n" + "}";

		ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(expectedResponseBody2, HttpStatus.OK);

		when(template.getForEntity(PatientApiUrl + "/familyName=" + name, String.class)).thenReturn(mockResponseEntity);

	}

}
