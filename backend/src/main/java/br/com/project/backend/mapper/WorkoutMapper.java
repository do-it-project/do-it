package br.com.project.backend.mapper;

import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.DTO.entities.WorkoutDTO;
import br.com.project.backend.DTO.request.EditWorkoutRequestDTO;
import br.com.project.backend.model.User;
import br.com.project.backend.model.Workout;
import org.hibernate.jdbc.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    @Mapping(source = "student.id", target = "id_student")
    WorkoutDTO toDTO(Workout workout);

    Workout toWorkout(WorkoutDTO workoutDTO);

    List<WorkoutDTO> toDTOList(List<Workout> workoutList);

    Workout editToWorkout(EditWorkoutRequestDTO w);
}
