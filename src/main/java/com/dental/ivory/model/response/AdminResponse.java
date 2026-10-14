package com.dental.ivory.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminResponse {
    private Integer adminId;
    private String userName;
    private String email;

}
