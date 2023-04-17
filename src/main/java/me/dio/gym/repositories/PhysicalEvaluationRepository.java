package me.dio.gym.repositories;

import me.dio.gym.models.PhysicalEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalEvaluationRepository extends JpaRepository<PhysicalEvaluation, Long> {
}
