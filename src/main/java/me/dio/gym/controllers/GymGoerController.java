package me.dio.gym.controllers;

import lombok.AllArgsConstructor;
import me.dio.gym.models.GymGoer;
import me.dio.gym.controllers.request.GymGoerRequest;
import me.dio.gym.services.GymGoerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/gym-goer")
public class GymGoerController {

    private final GymGoerService gymGoerService;

    @GetMapping
    public ResponseEntity<List<GymGoer>> findAllGymGoer() {
        return ResponseEntity.status(HttpStatus.OK).body(gymGoerService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GymGoer> findGymGoerById(@PathVariable Long id) {
        GymGoer gymGoer = gymGoerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(gymGoer);
    }

    @PostMapping
    public ResponseEntity<GymGoer> createGymGoer(@Valid @RequestBody GymGoerRequest gymGoerRequest) {
        GymGoer gymGoer = gymGoerService.create(gymGoerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(gymGoer);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GymGoer> updateGymGoer(@PathVariable Long id,
                                          @RequestBody GymGoerRequest gymGoerRequest) {
        GymGoer gymGoer = gymGoerService.update(id, gymGoerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(gymGoer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteGymGoer(@PathVariable Long id) {
        gymGoerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
