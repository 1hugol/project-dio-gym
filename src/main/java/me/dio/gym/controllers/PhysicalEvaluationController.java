package me.dio.gym.controllers;

import lombok.AllArgsConstructor;
import me.dio.gym.models.PhysicalEvaluation;
import me.dio.gym.controllers.request.PhysicalEvaluationRequest;
import me.dio.gym.services.PhysicalEvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/physical-evaluation")
public class PhysicalEvaluationController {
    PhysicalEvaluationService physicalEvaluationService;

    @GetMapping
    public ResponseEntity<List<PhysicalEvaluation>> findAllPhysicalEvaluation() {
        return ResponseEntity.status(HttpStatus.OK).body(physicalEvaluationService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PhysicalEvaluation> findPhysicalEvaluationById(@PathVariable Long id) {
        PhysicalEvaluation physicalEvaluation = physicalEvaluationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(physicalEvaluation);
    }

    @PostMapping
    public ResponseEntity<PhysicalEvaluation> createPhysicalEvaluation(@Valid @RequestBody PhysicalEvaluationRequest physicalEvaluationRequest) {
        PhysicalEvaluation physicalEvaluation = physicalEvaluationService.create(physicalEvaluationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(physicalEvaluation);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PhysicalEvaluation> updatePhysicalEvaluation(@PathVariable Long id,
                                          @RequestBody PhysicalEvaluation physicalEvaluation) {
        physicalEvaluation = physicalEvaluationService.update(id, physicalEvaluation);
        return ResponseEntity.status(HttpStatus.OK).body(physicalEvaluation);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePhysicalEvaluation(@PathVariable Long id) {
        physicalEvaluationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
