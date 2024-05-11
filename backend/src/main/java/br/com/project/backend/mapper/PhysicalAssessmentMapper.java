package br.com.project.backend.mapper;

import br.com.project.backend.DTO.entities.PhysicalAssessmentDTO;
import br.com.project.backend.DTO.request.CreatePhysicalAssessmentRequestDTO;
import br.com.project.backend.model.PhysicalAssessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhysicalAssessmentMapper {

    @Mapping(source = "student.name", target = "student_name")
    @Mapping(source = "student.email", target = "student_email")
    PhysicalAssessmentDTO toDTO(PhysicalAssessment pa);

    List<PhysicalAssessmentDTO> toDTOList(List<PhysicalAssessment> paList);

    @Mapping(source = "id_student", target = "student.id")
    PhysicalAssessment toPhysicalAssessment(CreatePhysicalAssessmentRequestDTO cpar);
}
