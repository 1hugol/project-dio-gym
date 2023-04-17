package me.dio.gym.services;

import lombok.AllArgsConstructor;
import me.dio.gym.controllers.request.PhysicalEvaluationRequest;
import me.dio.gym.models.GymGoer;
import me.dio.gym.models.PhysicalEvaluation;
import me.dio.gym.repositories.PhysicalEvaluationRepository;
import me.dio.gym.services.exceptions.DatabaseException;
import me.dio.gym.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PhysicalEvaluationService {

    private final PhysicalEvaluationRepository physicalEvaluationRepository;
    private final GymGoerService gymGoerService;

    public List<PhysicalEvaluation> findAll() {
        return physicalEvaluationRepository.findAll();
    }

    public PhysicalEvaluation findById(Long id) {
        Optional<PhysicalEvaluation> obj = physicalEvaluationRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public PhysicalEvaluation create(PhysicalEvaluationRequest physicalEvaluationRequest) {
        return physicalEvaluationRepository
                .save(createAssociationBetweenGymGoerAndPhysicalEvaluation(physicalEvaluationRequest));
    }

    public PhysicalEvaluation update(Long id, PhysicalEvaluation obj) {
        try {
            PhysicalEvaluation physicalEvaluation = physicalEvaluationRepository.getReferenceById(id);
            updateData(physicalEvaluation, obj);
            return physicalEvaluationRepository.save(physicalEvaluation);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            physicalEvaluationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private PhysicalEvaluation createAssociationBetweenGymGoerAndPhysicalEvaluation(PhysicalEvaluationRequest physicalEvaluationRequest) {
        GymGoer gymGoer = gymGoerService.findById(physicalEvaluationRequest.getGymGoerId());
        PhysicalEvaluation physicalEvaluation = new PhysicalEvaluation();
        physicalEvaluation.setGymGoerId(gymGoer);
        physicalEvaluation.setWeight(physicalEvaluationRequest.getWeight());
        physicalEvaluation.setHeight(physicalEvaluationRequest.getHeight());
        return physicalEvaluation;
    }
    private void updateData(PhysicalEvaluation physicalEvaluation, PhysicalEvaluation obj) {
        physicalEvaluation.setGymGoerId(obj.getGymGoerId());
        physicalEvaluation.setWeight(obj.getWeight());
        physicalEvaluation.setHeight(obj.getHeight());
    }
}
