package com.openclassrooms.mediscreen.respository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mediscreen.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient,Long> {
	Patient findByFamily(String family);


}
