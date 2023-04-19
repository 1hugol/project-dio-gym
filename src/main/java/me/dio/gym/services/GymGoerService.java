package me.dio.gym.services;

import me.dio.gym.controllers.request.GymGoerRequest;
import me.dio.gym.controllers.response.QueryResponse;
import me.dio.gym.models.GymGoer;
import me.dio.gym.repositories.GymGoerRepository;
import me.dio.gym.services.exceptions.CepNotFoundException;
import me.dio.gym.services.exceptions.DatabaseException;
import me.dio.gym.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class GymGoerService {
    private final GymGoerRepository gymGoerRepository;
    @Autowired
    private ICepServiceRequest cepServiceRequest;

    public GymGoerService(GymGoerRepository gymGoerRepository) {
        this.gymGoerRepository = gymGoerRepository;
    }

    public List<GymGoer> findAll() {
        return gymGoerRepository.findAll();
    }

    public GymGoer findById(Long id) {
        Optional<GymGoer> obj = gymGoerRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public GymGoer create(GymGoerRequest gymGoerRequest) {
        GymGoer gymGoer = createAssociationBetweenGymGoerAndAddress(gymGoerRequest);
        return gymGoerRepository.save(gymGoer);
    }

    public GymGoer update(Long id, GymGoerRequest obj) {
        try {
            GymGoer gymGoer = gymGoerRepository.getReferenceById(id);
            QueryResponse queryResponse = validateCep(obj.getCep());
            updateData(gymGoer, obj, queryResponse);
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

    private GymGoer createAssociationBetweenGymGoerAndAddress(GymGoerRequest gymGoerRequest) {
        GymGoer gymGoer = new GymGoer();
        QueryResponse queryResponse = validateCep(gymGoerRequest.getCep());
            gymGoer.setName(gymGoerRequest.getName());
            gymGoer.setCpf(gymGoerRequest.getCpf());
            gymGoer.setBirthDate(gymGoerRequest.getBirthDate());
            gymGoer.setCep(queryResponse.getCep());
            gymGoer.setAddress(queryResponse.getLogradouro());
            gymGoer.setDistrict(queryResponse.getBairro());
            gymGoer.setCity(queryResponse.getLocalidade());
            gymGoer.setState(queryResponse.getUf());
            return gymGoer;
    }

    private void updateData(GymGoer gymGoer, GymGoerRequest obj, QueryResponse queryResponse) {
        gymGoer.setName(obj.getName());
        gymGoer.setCpf(obj.getCpf());
        gymGoer.setBirthDate(obj.getBirthDate());
        gymGoer.setCep(queryResponse.getCep());
        gymGoer.setAddress(queryResponse.getLogradouro());
        gymGoer.setDistrict(queryResponse.getBairro());
        gymGoer.setCity(queryResponse.getLocalidade());
        gymGoer.setState(queryResponse.getUf());
    }

    private QueryResponse validateCep(String cep) {
        QueryResponse queryResponse = cepServiceRequest.getAddress(cep);
        if (queryResponse.getCep() != null) return queryResponse;
        else throw new CepNotFoundException(cep);
    }
}
