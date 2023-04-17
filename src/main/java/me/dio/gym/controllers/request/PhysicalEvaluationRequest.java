package me.dio.gym.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalEvaluationRequest {
    @Positive
    private Long gymGoerId;

    @Positive
    private Double weight;

    @Positive
    private Double height;
}
