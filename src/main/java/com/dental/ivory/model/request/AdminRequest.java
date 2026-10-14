package com.dental.ivory.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminRequest {
    @NotBlank(message="username required")
    private String userName;
    @NotBlank(message="password required")
    private String password;
    @NotBlank(message="email required")
    private String email;
}
