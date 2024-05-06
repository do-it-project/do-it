package br.com.project.backend.controller;

import br.com.project.backend.model.PhysicalAssessment;
import br.com.project.backend.service.PhysicalAssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PhysicalAssessmentController {

    private final PhysicalAssessmentService paService;

    public PhysicalAssessmentController(PhysicalAssessmentService pa) {
        this.paService = pa;
    }

    @GetMapping("/physical-assessments")
    public ResponseEntity<List<PhysicalAssessment>> createPhysicalAssesment(){
        return ResponseEntity.ok(this.paService.physicalAssessmentsList());
    }

    @PostMapping("/physical-assessments")
    public ResponseEntity<PhysicalAssessment> createPhysicalAssesment(@RequestBody PhysicalAssessment pa){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.paService.createPhysicalAssessment(pa));
    }

    @DeleteMapping("/physical-assessments/{id}")
    public ResponseEntity<String> createPhysicalAssesment(@PathVariable int id){
        this.paService.deletePhysicalAssessment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
