package com.rbb.report.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.rbb.report.comp.StatementValidator;
import com.rbb.report.model.Record;

class StatementServiceTest {

	StatementService statementService;
	List<Record> mockRecordList;

	@BeforeEach
	void setUp() {
		statementService = new StatementService();
		mockRecordList = new ArrayList<>();
		//
		StatementValidator statementValidator = new StatementValidator();
		Whitebox.setInternalState(statementService, "statementValidator", statementValidator);
		mockRecordList = getMockDupRecordList();
	}

	@Test
	@DisplayName("When generate failed report")
	void testFilterFailedRecords() {
		assertAll(
				() -> assertIterableEquals(getExpectFailedRecordList(),
						statementService.filterFailedRecords(mockRecordList)),
				() -> assertThrows(UnsupportedOperationException.class,
						() -> statementService.filterFailedRecords(null)),
				() -> assertThrows(UnsupportedOperationException.class,
						() -> statementService.filterFailedRecords(new ArrayList<Record>())));
	}

	List<Record> getMockDupRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112806, "NL27SNSB0917829872", "Clothes for Jake", BigDecimal.valueOf(26.32),
				BigDecimal.valueOf(48.98), BigDecimal.valueOf(75.3)));
		recordList.add(new Record(112807, "NL27SNSB0917829871", "Tickets from Richerd", BigDecimal.valueOf(99.44),
				BigDecimal.valueOf(41.23), BigDecimal.valueOf(120.67)));
		recordList.add(new Record(112806, "NL27SNSB0917829873", "Subscription for peter", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112808, "NL27SNSB0917829871", "Flowers from Jan Bakker", BigDecimal.valueOf(23.96),
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
