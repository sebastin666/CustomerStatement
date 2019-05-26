package com.rbb.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbb.report.service.StatementService;
import com.rbb.report.model.Record;

@RestController
@RequestMapping("/statement")
public class StatementController {

	@Autowired
	StatementService statementService;

	@PostMapping("/upload")
	public List<Record> statementReceiver(@RequestParam("file") MultipartFile file) { // csv, xml
		System.out.println("************* statementReceiver Called *****************");
		List<Record> failedRecords = null;
		try {
			failedRecords = statementService.genarateReport(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return failedRecords;
	}

	@GetMapping("/test")
	public String getUrl() {
		return "welcome";
	}

}
