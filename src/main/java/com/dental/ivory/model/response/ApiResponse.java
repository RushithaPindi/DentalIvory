package com.dental.ivory.model.response;

import com.dental.ivory.util_enums.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T  data;   //Actual Reponse
    private ApiStatus apiStatus;
    private String message;

    public ApiResponse(ApiStatus apiStatus) {
    }
}
