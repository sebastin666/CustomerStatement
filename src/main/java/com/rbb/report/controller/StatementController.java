package com.rbb.report.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbb.report.service.StatementService;

import io.swagger.annotations.Api;

import com.rbb.report.model.Record;

@RestController
@RequestMapping("/statement")
@Api(value = "Upload Customer Statement File", tags = "Genarate the fail report form the uploaded statement.")
public class StatementController {

	Logger logger = LoggerFactory.getLogger(StatementController.class);

	@Autowired
	StatementService statementService;

	@PostMapping("/validate")
	public ResponseEntity<?> validateStatement(@RequestParam("file") MultipartFile file) {
		try {
			List<Record> failedRecords = statementService.genarateReport(file);
			if (failedRecords != null && !failedRecords.isEmpty()) {
				return new ResponseEntity<List<Record>>(failedRecords, HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.error("Error Uploading file: ", ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Record>>(HttpStatus.NO_CONTENT);
	}

}
