package com.dental.ivory.controller;

import com.dental.ivory.exceptions.ClinicNotFoundException;
import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.entity.ClinicEntity;
import com.dental.ivory.model.request.ClinicRequest;
import com.dental.ivory.model.response.ApiResponse;
import com.dental.ivory.model.response.ApiResponseWithApiErrorResponseType;
import com.dental.ivory.model.response.ClinicResponse;
import com.dental.ivory.model.response.PatientResponse;
import com.dental.ivory.service.ClinicService;
import com.dental.ivory.util_enums.ApiStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/clinic_apis")
@CrossOrigin(value = "*")
public class ClinicController {
    @Autowired
    ClinicService clinicService;

    @PostMapping("/clinics")
    @Operation(description = "This api will help to create new clinic .",
            summary = "Help to add new clinic .", operationId = "addClinic",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                         @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                         @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clinic added successfully.")})
    public ResponseEntity<ApiResponse<ClinicResponse>> addClinic(@RequestBody ClinicRequest clinicRequest) throws ClinicNotFoundException {
        ClinicResponse clinicResponse = clinicService.addClinic(clinicRequest);
        ApiResponse<ClinicResponse> apiResponse = new ApiResponse(clinicResponse, ApiStatus.CREATED, "clinic is added");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @Operation(description = "This api will help to add multiple  clinics .",
            summary = "Help to add multiple clinics .", operationId = "addMultipleClinic",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clinics added successfully.")})
    @PostMapping("/multiple_clinics-apis")
    public ResponseEntity<ApiResponse<List<ClinicResponse>>> multipleClinics(@RequestBody List<ClinicRequest> clinicRequest) throws EmailNotFundException, ClinicNotFoundException {
        List<ClinicResponse> clinicResponses=clinicService.multipleClinics(clinicRequest);
        ApiResponse apiResponse = new ApiResponse(clinicResponses,ApiStatus.CREATED,"Clinics addedd Successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @Operation(description = "This api will help to get all the clinics .",
            summary = "Help to fetch all clinics .", operationId = "getClinics",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clinics fetched successfully.")})
    @GetMapping("/get-all-clinics-apis")
    public ResponseEntity<ApiResponse<List<ClinicResponse>>> getAllClinics(){
        List<ClinicResponse> clinicResponses =clinicService.getAllClinics();
        ApiResponse apiResponse = new ApiResponse(clinicResponses,ApiStatus.SUCCESS,"Fetched all data from clinics");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to fetch Clinics by ID .",
            summary = "Help to get the clinics by ID .", operationId = "getClinicsByID",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get the clinic by ID successfully.")})
    @GetMapping("/get-clincs-by-Id/{clinicId}")
    public ResponseEntity<ApiResponse<ClinicResponse>> getClinicsById(@PathVariable Integer clinicId) throws UserNotFoundException {
        ClinicResponse clinicResponse=clinicService.getClinicById(clinicId);
        ApiResponse apiResponse = new ApiResponse(clinicResponse,ApiStatus.SUCCESS,"clinic Id deatils fetched successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to fetch Patient by name .",
            summary = "Help to get the patient by name .", operationId = "getPatientByName",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get the patient by name successfully.")})
    @GetMapping("/get-clinic-details-by-clinicName")
    public ResponseEntity<ApiResponse<List<ClinicResponse>>> getCinicDetailsByName(@RequestParam String clinicName) throws UserNotFoundException {
        List<ClinicResponse> clinicResponses =clinicService.getClinicDetailsByName(clinicName);
        ApiResponse apiResponse = new ApiResponse(clinicResponses,ApiStatus.SUCCESS,"Name related clinics fetched successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @Operation(description = "This api will help to get the Patients count .",
            summary = "Help to get the Patients count .", operationId = "getPatientsCount",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get the Patients count successfully.")})
    @GetMapping("/get-clinic-count")
    public ResponseEntity<ApiResponse> getAllClinicDetailsByCount(){
        Long clinicResponses=clinicService.countAllClinicDetails();
        ApiResponse apiResponse = new ApiResponse(clinicResponses,ApiStatus.SUCCESS,"Fetched all counts successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to delete the clinics by id .",
            summary = "Help to delete the clinics by id.", operationId = "deleteClinic",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clinic details deleted successfully by id.")})
    @DeleteMapping("/delte-clinic-id/{id}")
    public ResponseEntity<ApiResponse> delteClinicNameById(@PathVariable Integer id){
        String delteClinicDetils=clinicService.deleteClinicNames(id);
        ApiResponse apiResponse = new ApiResponse(delteClinicDetils,ApiStatus.SUCCESS,"clinic details successfully by id");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to update the clinic details by id .",
            summary = "Help to update the clinic datails by id.", operationId = "clinicPatient",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clinic patient details successfully.")})
    @PutMapping("/update-clinic-details-Bu-Id/{clinicId}")
    public ResponseEntity<ApiResponse<ClinicResponse>> updateClinicDetailsByID(@PathVariable Integer clinicId, @RequestBody ClinicRequest clinicRequest) throws UserNotFoundException {
      ClinicResponse clinicResponse=clinicService.updateClinicDetailsById (clinicId,clinicRequest);
      ApiResponse apiResponse= new ApiResponse(clinicResponse,ApiStatus.SUCCESS,"partially updated the clinic details");
      return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
