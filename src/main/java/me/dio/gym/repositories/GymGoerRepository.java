package me.dio.gym.repositories;

import me.dio.gym.models.GymGoer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymGoerRepository extends JpaRepository<GymGoer, Long> {
}
