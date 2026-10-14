package com.dental.ivory.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private Integer adminId;
    private String email;
}
