package com.dental.ivory.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="clinic_table")
@Data
@NoArgsConstructor

public class ClinicEntity extends BaseEntity {
    @OneToMany(mappedBy = "clinicEntity")
    List<PatientEntity> patientEntity ;
    @Column(name ="clinic_name")
    private String clinicName;
    @Column(name ="clinic_address")
    private String address;
    @Column(name="phone_number")
    private Long phoneNumber;
    @Column(name="email")
    private String email;

}
