package com.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.catalog.service.PolynomialService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/polynomial")
public class PolynomialControllerr {
	
	 @Autowired
	    private PolynomialService polynomialService;

	    // Endpoint to handle POST requests with JSON input
	    @PostMapping("/solve")
	    public ResponseEntity<String> solvePolynomial(@RequestBody JsonNode inputJson) {
	        try {
	            // Call the service to process the input JSON and compute the result
	            double constantTerm = polynomialService.processPolynomial(inputJson);
	            return ResponseEntity.ok("The constant term (c) is: " + constantTerm);
	        } catch (Exception e) {
	            // Handle potential errors, like invalid JSON format or parsing issues
	            return ResponseEntity.badRequest().body("Error processing polynomial: " + e.getMessage());
	        }
	    }

}
