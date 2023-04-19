package me.dio.gym.configs;

import me.dio.gym.models.GymGoer;
import me.dio.gym.models.PhysicalEvaluation;
import me.dio.gym.repositories.GymGoerRepository;
import me.dio.gym.repositories.PhysicalEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private GymGoerRepository gymGoerRepository;
    @Autowired
    private PhysicalEvaluationRepository physicalEvaluationRepository;

    @Override
    public void run(String... args) throws Exception {
        GymGoer g1 = new GymGoer(null,"Hugo", "00011122233", LocalDate.of(1992,12,16),"65000000","Avenida 1","Renascença", "São Luís","MA");
        GymGoer g2 = new GymGoer(null,"Letícia", "33322211100", LocalDate.of(1993,12,28),"65000000","Avenida 1","Renascença", "São Luís","MA");
        gymGoerRepository.saveAll(Arrays.asList(g1, g2));
        PhysicalEvaluation p1 = new PhysicalEvaluation(null, g1, 75d, 1.70);
        PhysicalEvaluation p2 = new PhysicalEvaluation(null, g2, 65d, 1.65);
        physicalEvaluationRepository.saveAll(Arrays.asList(p1, p2));
    }
}
