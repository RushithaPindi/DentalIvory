package com.dental.ivory.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientResponse {
    private Integer patientId;
    private String patientName;
    private String patientAddress;
    private String patientEmailId;
    private Integer patientAge;
    private Long patientNumber;
    private Integer clinicId;
}
