package com.dental.ivory.controller;

import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.request.PatientRequest;
import com.dental.ivory.model.response.ApiResponse;
import com.dental.ivory.model.response.ApiResponseWithApiErrorResponseType;
import com.dental.ivory.model.response.PatientResponse;
import com.dental.ivory.service.PatientService;
import com.dental.ivory.util_enums.ApiStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/patient")
@CrossOrigin(value="*")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/add-patient")
    @Operation(description = "This api will help to create new Patient .",
            summary = "Help to add new patient .", operationId = "addPatient",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Patient added successfully.")})
    public ResponseEntity<ApiResponse<PatientResponse>> addPatient(@RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.addPatient(patientRequest);
        ApiResponse<PatientResponse> apiResponse = new ApiResponse<>(patientResponse, ApiStatus.CREATED, "patient details filled Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }



    @PostMapping("/add-multiplePatients")
    @Operation(description = "This api will help to create new multiple Patients .",
            summary = "Help to add multiple patients .", operationId = "addMultiplePatient",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Multiple patients added successfully.")})
    public ResponseEntity<ApiResponse<List<PatientResponse>>> addMultiplePatients(@RequestBody List<PatientRequest> patientRequests) throws EmailNotFundException {
        List<PatientResponse> patientResponses=patientService.addMultiplePatients(patientRequests);
        ApiResponse apiResponse =new ApiResponse(patientResponses,ApiStatus.CREATED,"Patients added successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Operation(description = "This api will help to get all the patients .",
            summary = "Help to fetch all patients .", operationId = "getPatients",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Patients fetched successfully.")})
    @GetMapping("/get-all-patients")
    public ResponseEntity<ApiResponse<List<PatientResponse>>> getAllPatients() throws UserNotFoundException {
        List<PatientResponse> patientResponses=patientService.getAllPatients();
        ApiResponse<List<PatientResponse>> apiResponse = new ApiResponse<>(patientResponses,ApiStatus.SUCCESS,"All Patients fetched successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to fetch Patient by ID .",
            summary = "Help to get the patient by ID .", operationId = "getPatientByID",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get the patient by ID successfully.")})
    @GetMapping("/get-patient-details-id/{patientId}")
    public ResponseEntity<ApiResponse<PatientResponse>> getPatientDetailsById(@PathVariable Integer patientId) throws UserNotFoundException {
        PatientResponse patientResponse=patientService.getPatientDetailsById(patientId);
        ApiResponse apiResponse = new ApiResponse(patientResponse,ApiStatus.SUCCESS,"Get the patient details by id");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to get the Patient details within range .",
            summary = "Help to get patients within range .", operationId = "getPatientsWithinRange",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get the patient details within range successfully.")})
    @GetMapping("/get-patient-details-range")
    public ResponseEntity<ApiResponse<List<PatientResponse>>> getPatientDetailsByRange(@RequestParam Integer startValue, @RequestParam Integer endValue) throws UserNotFoundException {
       List<PatientResponse> patientResponses= patientService.getPatientDetailsByRange(startValue,endValue);
       ApiResponse apiResponse = new ApiResponse(patientResponses,ApiStatus.SUCCESS,"Patient details fetched by range");
       return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to delete the Patient by id .",
            summary = "Help to delete the patient by id.", operationId = "deletePatient",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "patient details deleted successfully by id.")})
    @DeleteMapping("/delete-patient-by-patientId/{patientId}")
    public ResponseEntity<ApiResponse>  deletePatientById(@PathVariable Integer patientId) throws UserNotFoundException {
        String deletedPatient =patientService.deletePatientById(patientId);
        ApiResponse apiResponse = new ApiResponse(deletedPatient,ApiStatus.SUCCESS,"Delted successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(description = "This api will help to update the Patient details by id .",
            summary = "Help to update the patient datails by id.", operationId = "updatePatient",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Update patient details successfully.")})
    @PutMapping("/udate-patient-details-by-PatientId/{patientId}")
    public ResponseEntity<ApiResponse<PatientResponse>> updatePatientDetailsById(@PathVariable  Integer patientId,@RequestBody PatientRequest patientRequest ) throws UserNotFoundException {
       PatientResponse patientResponse= patientService.updatePatientDeatilsByPatientId(patientId,patientRequest);
       ApiResponse apiResponse = new ApiResponse(patientResponse,ApiStatus.SUCCESS,"patient details pdated successfully");
       return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @Operation(description = "This api will help to partially update the Patient details.",
            summary = "Help to partially update the patient details.", operationId = "partialUpdatePatient",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request parameter missing.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponseWithApiErrorResponseType.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Partially updated the patient details successfully.")})
    @PatchMapping("/patch-update-patient-datails-by-id/{patientId}")
    public ResponseEntity<ApiResponse<PatientResponse>> partialUpdatePatientDetailsById(Integer patientId,@RequestBody PatientRequest patientRequest) throws UserNotFoundException {
        PatientResponse patientResponse=patientService.partialUpdatePatientDetailsById(patientId,patientRequest);
        ApiResponse apiResponse = new ApiResponse(patientResponse,ApiStatus.SUCCESS,"artially pdated succsfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
