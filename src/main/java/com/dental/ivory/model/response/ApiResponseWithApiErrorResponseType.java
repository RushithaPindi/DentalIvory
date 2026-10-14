package com.dental.ivory.model.response;

import com.dental.ivory.util_enums.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseWithApiErrorResponseType extends ApiResponse<ApiErrorResponse>{
//    public ApiResponseWithApiErrorResponseType(ApiStatus apiStatus){
//        super(apiStatus);
//    }
}
