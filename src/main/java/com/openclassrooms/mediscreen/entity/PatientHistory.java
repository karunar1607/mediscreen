package com.openclassrooms.mediscreen.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PatientHistory")
public class PatientHistory {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	private Long patientId;
	private String notes;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public PatientHistory() {
	}

	public PatientHistory(Long patientId, String notes) {
		this.patientId = patientId;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "PatientHistory [id=" + id + ", patientId=" + patientId + ", notes=" + notes + "]";
	}

}
