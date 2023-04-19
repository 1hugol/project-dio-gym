package me.dio.gym.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GymGoerRequest {
    @NotBlank
    private String name;
    @NotBlank
    @CPF
    private String cpf;

    @Size(max = 9)
    private String cep;
    @Size(max = 50)
    private String address;
    @Size(max = 50)
    private String district;
    @Size(max = 30)
    private String city;
    @Size(max = 2)
    private String state;
    @Past
    private LocalDate birthDate;

    public GymGoerRequest(String name, String cpf, String cep, LocalDate birthDate) {
        this.name = name;
        this.cpf = cpf;
        this.cep = cep;
        this.birthDate = birthDate;
    }

    public GymGoerRequest(String name, String cpf, String address, String district, String city, String state, LocalDate birthDate) {
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.district = district;
        this.city = city;
        this.state = state;
        this.birthDate = birthDate;
    }
}
