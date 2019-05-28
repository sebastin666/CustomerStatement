package com.rbb.report.comp;

import com.rbb.report.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatementValidator {

	Logger logger = LoggerFactory.getLogger(StatementValidator.class);

	@Value("#{'${upload.allowed.format}'.split(',')}")
	private List<String> allowedFormats;

	public boolean isAllowedFormat(String fileFormat) {
		logger.debug("Allowed Formats : " + allowedFormats.contains(fileFormat));
		return allowedFormats.contains(fileFormat);
	}

	public List<Record> getDuplicateRecords(List<Record> totalRecords) {		
		try {
			if (totalRecords != null && !totalRecords.isEmpty()) {
				return totalRecords.stream().filter(record -> Collections.frequency(totalRecords, record) > 1)
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new UnsupportedOperationException(
					"Error occured while validating duplicate references. Please check for any invalid data in the uploaded file.");
		}
		return totalRecords;
	}

	public List<Record> getWrongEndBalanceRecords(List<Record> totalRecords) {		
		try {
			if (totalRecords != null && !totalRecords.isEmpty()) {
				return totalRecords.stream().filter(record -> (record.getStartBalance().add(record.getMutation()))
						.compareTo(record.getEndBalance()) != 0).collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new UnsupportedOperationException(
					"Error occured while validating end balance. Please check for any invalid data in the uploaded file.");
		}
		return totalRecords;
	}

}
