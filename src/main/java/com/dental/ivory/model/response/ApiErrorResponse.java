package com.dental.ivory.model.response;

import lombok.Data;

import java.util.List;
@Data
public class ApiErrorResponse {
    private List<Object> errors;
}
