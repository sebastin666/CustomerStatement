package com.rbb.report.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.mock.web.MockMultipartFile;

import com.rbb.report.MockBase;
import com.rbb.report.component.StatementValidator;
import com.rbb.report.model.Record;

class StatementServiceTest extends MockBase{

	StatementService statementService;

	@BeforeEach
	void setUp() {
		statementService = new StatementService();
		//
		StatementValidator statementValidator = new StatementValidator();
		Whitebox.setInternalState(statementValidator, "allowedFormats", getAllowedFormats());
		Whitebox.setInternalState(statementService, "statementValidator", statementValidator);
	}
	
	@Test
	@DisplayName("When generate failed report")
	void testGenerateReport() {		
		mockCSVMultipartFile = new MockMultipartFile("records", "records.txt", "text/plain", getMockCSVData().getBytes());
		//	
		assertThrows(UnsupportedOperationException.class, () -> statementService.generateReport(mockCSVMultipartFile), "Test with invalid file");
		assertThrows(UnsupportedOperationException.class, () -> statementService.generateReport(null), "Test with null");
	}

	@Test
	@DisplayName("When filter failed records")
	void testFilterFailedRecords() {		
		assertAll(
				() -> assertIterableEquals(getExpectFailedRecordList(),
						statementService.filterFailedRecords(mockRecordList), "Test with valid records"),
				() -> assertThrows(UnsupportedOperationException.class,
						() -> statementService.filterFailedRecords(getMockInvalidRecordList()),"Test with in-valid records"),
				() -> assertThrows(UnsupportedOperationException.class,
						() -> statementService.filterFailedRecords(null),"Test with null"),
				() -> assertThrows(UnsupportedOperationException.class,
						() -> statementService.filterFailedRecords(new ArrayList<Record>()), "Test with empty records"));
	}
	
	List<Record> getMockInvalidRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112806, "NL27SNSB0917829872", "Clothes for Jake", BigDecimal.valueOf(26.32),
				BigDecimal.valueOf(48.98), BigDecimal.valueOf(75.3)));
		recordList.add(new Record(112807, "NL27SNSB0917829871", "Tickets from Richerd", BigDecimal.valueOf(99.44),
				BigDecimal.valueOf(41.23), null));
		recordList.add(new Record(112806, "NL27SNSB0917829873", "Subscription for peter", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112808, "NL27SNSB0917829871", "Flowers from Jan Bakker", null,
				BigDecimal.valueOf(-27.43), BigDecimal.valueOf(-3.47)));
		return recordList;
	}

	List<Record> getExpectFailedRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112806, "NL27SNSB0917829872", "Clothes for Jake", BigDecimal.valueOf(26.32),
				BigDecimal.valueOf(48.98), BigDecimal.valueOf(75.3)));
		recordList.add(new Record(112806, "NL27SNSB0917829873", "Subscription for peter", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112807, "NL27SNSB0917829871", "Tickets from Richerd", BigDecimal.valueOf(99.44),
				BigDecimal.valueOf(41.23), BigDecimal.valueOf(120.67)));
		return recordList;
	}
}
