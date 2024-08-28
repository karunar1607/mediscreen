package com.openclassrooms.mediscreen.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.mediscreen.entity.PatientHistoryResponse;
import com.openclassrooms.mediscreen.entity.PatientResponse;

@Service

public class PatientAssesmentService {
	@Autowired
	RestTemplate template;

	private static final String ANTIBODIES = "Antibodies";

	private static final String REACTION = "Reaction";

	private static final String RELAPSE = "Relapse";

	private static final String DIZZINESS = "Dizziness";

	private static final String CHOLESTEROL = "Cholesterol";

	private static final String ABNORMAL = "Abnormal";

	private static final String SMOKER = "Smoker";

	private static final String BODY_WEIGHT = "Body Weight";

	private static final String BODY_HEIGHT = "Body Height";

	private static final String MICROALBUMIN = "Microalbumin";

	private static final String HEMOGLOBIN = "Hemoglobin A1C";

	static String PatientHistoryApiUrl = "http://localhost:8080/patHistory/patient?patientId=";
	static String PatientApiUrl = "http://localhost:8080/patient";
	public static List<String> triggerWords = new ArrayList<String>();

	public String getPatientAssesmentById(Long patinetId) throws JsonMappingException, JsonProcessingException {
		String returnString = "";
		int count=0;
		PatientResponse patient = getPatient(patinetId);
		if (patient!= null) {
		List<PatientHistoryResponse> patientHistory = getPatientHistory(patinetId);

		int age = calculateAge(patient.getDob());
		String sex = patient.getSex();
		returnString = patient.getGiven() + " " + patient.getFamily() + " (age " + age + ") diabetes assessment is: ";

		count = getTriggerCount(patientHistory);
		String assesment = getAssesment(count, age, sex);
		returnString = returnString + assesment;
		
		}
		else {
			returnString=" No record found for patied id: "+patinetId;
		}
		return returnString;
	}
	
	public String getPatientAssesmentByName(String familyName) throws JsonMappingException, JsonProcessingException {
		String returnString = "";
		int count=0;
		PatientResponse patient = getPatientByName(familyName);
		if (patient!= null) {
			if(patient.getId() != null) {
		List<PatientHistoryResponse> patientHistory = getPatientHistory(patient.getId());
		int age = calculateAge(patient.getDob());
		String sex = patient.getSex();
		returnString = patient.getGiven() + " " + patient.getFamily() + " (age " + age + ") diabetes assessment is: ";

		count = getTriggerCount(patientHistory);
		String assesment = getAssesment(count, age, sex);
		returnString = returnString + assesment;
			}
			else {
				returnString=" No record found for family Name: "+familyName;
		
			}
		}
		else {
			returnString=" No record found for family Name: "+familyName;
		}
		return returnString;
	}


	public static String getAssesment(int count, int age, String sex) {
		String assesment = "None";
		if (count == 0) {
			assesment = "None";
		} else if ((count >= 2 && count < 6) && (age > 30)) {
			assesment = "Borderline";
		} else if ((count >= 6 && count < 8) && (age > 30)) {
			assesment = "In danger";
		} else if ((count >= 8) && (age > 30)) {
			assesment = "Early Onset";
		}

		else if ((count >= 3 && count < 5) && (age < 30) && (sex.equalsIgnoreCase("M"))) {
			assesment = "In danger";
		} else if ((count >= 4 && count < 7) && (age < 30) && (sex.equalsIgnoreCase("F"))) {
			assesment = "In danger";
		} else if ((count >= 5) && (age < 30) && sex.equalsIgnoreCase("M")) {
			assesment = "Early Onset";
		} else if ((count >= 7) && (age < 30) && sex.equalsIgnoreCase("F")) {
			assesment = "Early Onset";
		}
		return assesment;
	}

	private static int getTriggerCount(List<PatientHistoryResponse> patientHistory) {
		int count = 0;
		if (triggerWords.size() == 0) {
		triggerWords.add(HEMOGLOBIN.toUpperCase());
		triggerWords.add(MICROALBUMIN.toUpperCase());
		triggerWords.add(BODY_WEIGHT.toUpperCase());
		triggerWords.add(BODY_HEIGHT.toUpperCase());
		triggerWords.add(SMOKER.toUpperCase());
		triggerWords.add(ABNORMAL.toUpperCase());
		triggerWords.add(CHOLESTEROL.toUpperCase());
		triggerWords.add(DIZZINESS.toUpperCase());
		triggerWords.add(RELAPSE.toUpperCase());
		triggerWords.add(REACTION.toUpperCase());
		triggerWords.add(ANTIBODIES.toUpperCase());
		}
		
		for (PatientHistoryResponse pHR : patientHistory) {

			for (String triggerWord : triggerWords) {
				if (pHR.getNotes().toUpperCase().contains(triggerWord)) {
					count++;
				}
			}
		}

		return count;
	}

	public  List<PatientHistoryResponse> getPatientHistory(Long patinetId)
			throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = template.getForEntity(PatientHistoryApiUrl + patinetId, String.class);
		String responseBody = response.getBody();
		List<PatientHistoryResponse> responseValue =new ArrayList<PatientHistoryResponse>();
		if (responseBody != null) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseBody);
		 responseValue = objectMapper.convertValue(rootNode,
				new TypeReference<List<PatientHistoryResponse>>() {
				});
		
		}
		return responseValue;
	}

	public  PatientResponse getPatient(Long patinetId) throws JsonMappingException, JsonProcessingException {

		ResponseEntity<String> patientResponse = template.getForEntity(PatientApiUrl+"/id="+ patinetId, String.class);
		String patientResponseBody = patientResponse.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(patientResponseBody);
		PatientResponse responseValue = objectMapper.convertValue(node, new TypeReference<PatientResponse>() {
		});

		return responseValue;
	}

	public  int calculateAge(String birthDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
		LocalDate formatedDate = LocalDate.parse(birthDate, formatter);
		LocalDate currentDate = LocalDate.now();
		int period = Period.between(formatedDate, currentDate).getYears();
		return period;

	}

	
	public PatientResponse getPatientByName(String familyName) throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> patientResponse = template.getForEntity(PatientApiUrl+"/familyName="+ familyName, String.class);
		String patientResponseBody = patientResponse.getBody();
		PatientResponse responseValue =new PatientResponse();
		if(patientResponseBody!=null) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(patientResponseBody);
		 responseValue = objectMapper.convertValue(node, new TypeReference<PatientResponse>() {
		});
		}
		return responseValue;
	}

	
}
