package com.openclassrooms.mediscreen.entity;

public class PatientHistoryResponse {
	private String id;
	private Long patientId;
	private String notes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public PatientHistoryResponse(String id, Long patientId, String notes) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.notes = notes;
	}

	public PatientHistoryResponse() {
		super();
	}

}
