package com.dental.ivory.controller;

import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.PasswordNotFoundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.request.AdminRequest;
import com.dental.ivory.model.request.LoginRequest;
import com.dental.ivory.model.response.AdminResponse;
import com.dental.ivory.model.response.ApiResponse;
import com.dental.ivory.model.response.ApiResponseWithApiErrorResponseType;
import com.dental.ivory.model.response.LoginResponse;
import com.dental.ivory.service.AdminService;
import com.dental.ivory.util_enums.ApiStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-apis")
@CrossOrigin(value="*")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/admin-registration")
    @Operation(description = "This api will help to create new admin .",
            summary = "Help to add new admin .", operationId = "addAdmin",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Admin added successfully.")})
    public ResponseEntity<ApiResponse<AdminResponse>> adminRegistration(@RequestBody AdminRequest adminRequest) throws UserNotFoundException {
        AdminResponse adminResponse= adminService.adminRegistration(adminRequest);
        ApiResponse<AdminResponse> apiResponse= new ApiResponse<>(adminResponse,ApiStatus.CREATED,"Admin registration successfully");
       return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    @Operation(description = "This api will help to create new lOGIN.",
            summary = "Help to add new lOGIN .", operationId = "addLogin",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "AdminLogin added successfully.")})
    @PostMapping("/add_Login-apis")
    public ResponseEntity<ApiResponse<LoginResponse>> addLogin(@RequestBody LoginRequest loginRequest) throws EmailNotFundException {
        LoginResponse loginResponse=adminService.adminLogin(loginRequest);
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(loginResponse,ApiStatus.CREATED,"Login Successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);

    }


}
