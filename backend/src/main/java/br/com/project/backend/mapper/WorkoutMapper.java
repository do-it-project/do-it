package br.com.project.backend.mapper;

import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.DTO.entities.WorkoutDTO;
import br.com.project.backend.model.User;
import br.com.project.backend.model.Workout;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutDTO toDTO(Workout workout);

    List<WorkoutDTO> toDTOList(List<Workout> workoutList);
}
