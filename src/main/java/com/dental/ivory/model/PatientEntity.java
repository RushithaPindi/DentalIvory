package com.dental.ivory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient_entity")

@NoArgsConstructor
@Data
public class PatientEntity extends BaseEntity {

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
