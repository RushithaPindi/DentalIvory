package com.dental.ivory.model.request;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicRequest {
    private String clinicName;
    private String address;
    private Long phoneNumber;
    private String email;
    private Integer clinicId;
}
