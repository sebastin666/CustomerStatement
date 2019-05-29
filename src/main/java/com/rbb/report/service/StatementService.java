package com.rbb.report.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbb.report.comp.StatementValidator;
import com.rbb.report.model.Record;
import com.rbb.report.model.Records;
import com.rbb.report.parse.util.ParserUtil;

@Service
public class StatementService {

	@Autowired
	StatementValidator statementValidator;
	
	Logger logger = LoggerFactory.getLogger(StatementService.class);

	public List<Record> genarateReport(MultipartFile file) throws UnsupportedOperationException {
		logger.debug("GenarateReport Called");
		if(file == null) {
			throw new UnsupportedOperationException("Invalid file. Please try again.");
		}
		//				
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		logger.debug(" fileType::" + fileType);
		//
		if (!statementValidator.isAllowedFormat(fileType)) {
			throw new UnsupportedOperationException("Not supported. Unknown file format " + fileType + ".");
		}
		//
		Records records = (Records) ParserUtil.parse(fileType, file);	
		// Call for validations
		List<Record> failedRecords = filterFailedRecords(records.getRecords());

		return failedRecords;
	}

	protected List<Record> filterFailedRecords(List<Record> totalRecords) {
		if(totalRecords == null || totalRecords.isEmpty()) {
			throw new UnsupportedOperationException("No records available in the uploaded file.");
		}
		List<Record> failedRecords = statementValidator.getDuplicateRecords(totalRecords);
		logger.debug("dup failedRec::" + failedRecords);
		failedRecords.addAll(statementValidator.getWrongEndBalanceRecords(
				totalRecords.stream().filter(record -> !failedRecords.contains(record)).collect(Collectors.toList())));
		logger.debug("final failedRec::" + failedRecords);
		return failedRecords;
	}

}
