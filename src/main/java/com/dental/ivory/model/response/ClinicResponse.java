package com.dental.ivory.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicResponse {
    private Integer clinicId;
    private String clinicName;
    private String address;
    private Long phoneNumber;
    private String email;

}
