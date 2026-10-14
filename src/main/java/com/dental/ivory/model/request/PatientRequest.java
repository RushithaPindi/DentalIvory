package com.dental.ivory.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRequest {
    private String patientName;
    private String patientAddress;
    private String patientEmailId;
    private Integer patientAge;
    private Long patientNumber;
}
