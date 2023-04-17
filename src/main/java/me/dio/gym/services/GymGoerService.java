package me.dio.gym.services;

import lombok.AllArgsConstructor;
import me.dio.gym.models.GymGoer;
import me.dio.gym.controllers.request.GymGoerRequest;
import me.dio.gym.repositories.GymGoerRepository;
import me.dio.gym.services.exceptions.DatabaseException;
import me.dio.gym.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GymGoerService {
    private final GymGoerRepository gymGoerRepository;

    public List<GymGoer> findAll() {
        return gymGoerRepository.findAll();
    }

    public GymGoer findById(Long id) {
        Optional<GymGoer> obj = gymGoerRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public GymGoer create(GymGoerRequest gymGoerRequest) {
        GymGoer gymGoer = new GymGoer();
        BeanUtils.copyProperties(gymGoerRequest, gymGoer);
        return gymGoerRepository.save(gymGoer);
    }

    public GymGoer update(Long id, GymGoer obj) {
        try {
            GymGoer gymGoer = gymGoerRepository.getReferenceById(id);
            updateData(gymGoer, obj);
            return gymGoerRepository.save(gymGoer);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            gymGoerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(GymGoer gymGoer, GymGoer obj) {
        gymGoer.setName(obj.getName());
        gymGoer.setCpf(obj.getCpf());
        gymGoer.setDistrict(obj.getDistrict());
        gymGoer.setBirthDate(obj.getBirthDate());
    }
}
