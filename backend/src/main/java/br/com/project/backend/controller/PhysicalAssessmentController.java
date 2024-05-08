package br.com.project.backend.controller;

import br.com.project.backend.DTO.entities.PhysicalAssessmentDTO;
import br.com.project.backend.model.PhysicalAssessment;
import br.com.project.backend.service.PhysicalAssessmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/physical-assessments")
public class PhysicalAssessmentController {

    @Autowired
    private PhysicalAssessmentService paService;

    @GetMapping
    public ResponseEntity<List<PhysicalAssessmentDTO>> getPhysicalAssesments(){
        return ResponseEntity.ok(this.paService.physicalAssessmentsList());
    }

    @PostMapping
    public ResponseEntity<PhysicalAssessmentDTO> createPhysicalAssesment(@RequestBody @Valid PhysicalAssessment pa){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.paService.createPhysicalAssessment(pa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhysicalAssesment(@PathVariable("id") int id){

        Optional<PhysicalAssessment> tempPA = paService.findPhysicalAssessmentById(id);

        if (tempPA.isPresent()) {
            paService.deletePhysicalAssessment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
