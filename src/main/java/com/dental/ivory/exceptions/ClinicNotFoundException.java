package com.dental.ivory.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClinicNotFoundException extends Exception{
    String clinicName;
    String email;
    public ClinicNotFoundException (String s) {
        super(s);

    }
    public ClinicNotFoundException (String s, String clinicName, String email) {
        super(s);
        this.clinicName = clinicName;
        this.email=email;
    }

}
