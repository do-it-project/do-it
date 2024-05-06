package br.com.project.backend.repository;

import br.com.project.backend.model.PhysicalAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IPhysicalAssessment extends JpaRepository<PhysicalAssessment, Integer> {
    Optional<PhysicalAssessment> findByName(String name);
}
