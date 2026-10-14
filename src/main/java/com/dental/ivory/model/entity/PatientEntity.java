package com.dental.ivory.model.entity;

import com.dental.ivory.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient_entity")

@NoArgsConstructor
@Data
public class PatientEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="clinic_id")
    ClinicEntity clinicEntity;

    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "patient_address")
    private String patientAddress;
    @Column(name = "patient_email_id")
    private String patientEmailId;
    @Column(name = "patient_age")
    private Integer patientAge;
    @Column(name = "patient_mobile_number")
    private Long patientNumber;
}
