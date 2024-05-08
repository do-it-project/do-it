package br.com.project.backend.service;

import br.com.project.backend.DTO.entities.PhysicalAssessmentDTO;
import br.com.project.backend.exception.PyshicalAssessmentAlreadyExistsException;
import br.com.project.backend.exception.UserAlreadyExistsException;
import br.com.project.backend.mapper.PhysicalAssessmentMapper;
import br.com.project.backend.mapper.UserMapper;
import br.com.project.backend.model.PhysicalAssessment;
import br.com.project.backend.model.User;
import br.com.project.backend.repository.IPhysicalAssessment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalAssessmentService {

    @Autowired
    private IPhysicalAssessment repository;

    @Autowired
    private PhysicalAssessmentMapper paMapper;

    public List<PhysicalAssessmentDTO> physicalAssessmentsList(){
        List<PhysicalAssessment> paList = this.repository.findAll();

        return paMapper.toDTOList(paList);
    }

    @Transactional
    public PhysicalAssessmentDTO createPhysicalAssessment(PhysicalAssessment pa){
        Optional<PhysicalAssessment> tempPA = this.findPhysicalAssessmentByName(pa.getName());

        if(tempPA.isPresent()){
            throw new PyshicalAssessmentAlreadyExistsException();
        }

        return paMapper.toDTO(this.repository.save(pa));
    }

    @Transactional
    public void deletePhysicalAssessment(int id) {
        this.repository.deleteById(id);
    }

    public Optional<PhysicalAssessment> findPhysicalAssessmentByName(String name){
        return this.repository.findByName(name);
    }

    public Optional<PhysicalAssessment> findPhysicalAssessmentById(int id) {
        return this.repository.findById(id);
    }
}
