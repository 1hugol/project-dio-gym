package me.dio.gym.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Column(length = 50)
    private String district;

    private LocalDate birthDate;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "gymGoerId")
    private Set<PhysicalEvaluation> physicalEvaluationList = new LinkedHashSet<>();

    public GymGoer(Long id, String name, String cpf, String district, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.district = district;
        this.birthDate = birthDate;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Set<PhysicalEvaluation> getPhysicalEvaluationList() {
        return physicalEvaluationList;
    }
}
