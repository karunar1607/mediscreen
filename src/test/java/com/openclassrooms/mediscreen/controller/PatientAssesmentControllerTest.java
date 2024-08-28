package com.openclassrooms.mediscreen.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mediscreen.service.PatientAssesmentService;

@ExtendWith(MockitoExtension.class)

class PatientAssesmentControllerTest {
	@Mock
	PatientAssesmentService service;
	@InjectMocks
	PatientAssesmentController controller;

	@Test
	void testGetPatientAssesmentById() throws JsonMappingException, JsonProcessingException {
		// Arrange
		long id = 1l;
		String returnString="Anitha karuna (age 20) diabetes assessment is: In danger";
		//Act
		doReturn(returnString).when(service).getPatientAssesmentById(anyLong());
		String response = controller.getPatientAssesmentById(id);
		//Assert
		assertEquals(returnString,response);
		
		}

	@Test
	void testGetPatientAssesmentByFamilyName() throws JsonMappingException, JsonProcessingException {
		// Arrange
				String name = "test";
				String returnString="Anitha karuna (age 20) diabetes assessment is: In danger";
				//Act
				doReturn(returnString).when(service).getPatientAssesmentByName(name);
				String response = controller.getPatientAssesmentByFamilyName(name);
				//Assert
				assertEquals(returnString,response);
				
				}

	}

