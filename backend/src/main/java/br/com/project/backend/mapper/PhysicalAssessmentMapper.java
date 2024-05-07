package br.com.project.backend.mapper;

import br.com.project.backend.DTO.entities.PhysicalAssessmentDTO;
import br.com.project.backend.model.PhysicalAssessment;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PhysicalAssessmentMapper {

    PhysicalAssessmentDTO toDTO(PhysicalAssessment pa);

    List<PhysicalAssessmentDTO> toDTOList(List<PhysicalAssessment> paList);
}
