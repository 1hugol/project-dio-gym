package me.dio.gym.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PHYSICAL_EVALUATION")
public class PhysicalEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gym_goer_id")
    private GymGoer gymGoerId;

    private LocalDateTime physicalEvaluationDate = LocalDateTime.now();

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    public PhysicalEvaluation(Long id, GymGoer gymGoerId, Double weight, Double height) {
        this.id = id;
        this.gymGoerId = gymGoerId;
        this.weight = weight;
        this.height = height;
    }
}
