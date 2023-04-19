package me.dio.gym.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "TB_GYM_GOER")
public class GymGoer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 65)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    private LocalDate birthDate;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(length = 9)
    private String cep;
    @Column(length = 50)
    private String address;
    @Column(length = 50)
    private String district;
    @Column(length = 30)
    private String city;
    @Column(length = 2)
    private String state;

    @OneToMany(mappedBy = "gymGoerId")
    private Set<PhysicalEvaluation> physicalEvaluationList = new LinkedHashSet<>();

    public GymGoer(Long id, String name, String cpf, LocalDate birthDate, String cep,String address, String district, String city, String state) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.cep = cep;
        this.address = address;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Set<PhysicalEvaluation> getPhysicalEvaluationList() {
        return physicalEvaluationList;
    }
}
