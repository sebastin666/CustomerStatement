package com.rbb.report.comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rbb.report.model.Record;

@Component
public class StatementValidator {

	@Value("${upload.csvformat}")
	private String csvFormat;
	@Value("${upload.xmlformat}")
	private String xmlFormat;

	private List<String> allowedFormats = new ArrayList<>();

	@PostConstruct
	public void init() {
		allowedFormats.add(csvFormat);
		allowedFormats.add(xmlFormat);
		System.out.println("Allowed formats::" + allowedFormats);
	}

	public boolean isAllowedFormat(String fileFormat) {
		return allowedFormats.contains(fileFormat);
	}

	public List<Record> filterDuplicateRecords(List<Record> totalRecords) {
		
		return totalRecords.stream().filter(record -> Collections.frequency(totalRecords, record) > 1)
				.collect(Collectors.toList());		
	}
	
	public List<Record> filterWrongEndBalanceRecords(List<Record> totalRecords) {
		
		return totalRecords.stream()
				.filter(record -> (((Record) record).getStartBalance().add(((Record) record).getMutation()))
						.compareTo(((Record) record).getEndBalance()) != 0).collect(Collectors.toList());	
	}

}
