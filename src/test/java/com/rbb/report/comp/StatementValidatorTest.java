package com.rbb.report.comp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.rbb.report.model.Record;

class StatementValidatorTest {

	StatementValidator statementValidator;
	List<Record> mockRecordList;

	@BeforeEach
	void setUp() throws Exception {
		statementValidator = new StatementValidator();
		mockRecordList = new ArrayList<>();
		//
		List<String> allowedFormats = new ArrayList<>();
		allowedFormats.add("csv");
		allowedFormats.add("xml");
		Whitebox.setInternalState(statementValidator, "allowedFormats", allowedFormats);
		//
		mockRecordList = getMockDupRecordList();
	}

	@Test
	@DisplayName("Allowed File Format")
	void testIsAllowedFormat() {
		assertAll("Test Allowed Format", () -> assertThat(statementValidator.isAllowedFormat("csv")).isTrue(),
				() -> assertThat(statementValidator.isAllowedFormat("xml")).isTrue(),
				() -> assertThat(statementValidator.isAllowedFormat(null)).isFalse(),
				() -> assertThat(statementValidator.isAllowedFormat("txt")).isFalse());

	}

	@Test
	@DisplayName("When getting duplicate records")
	void testGetDuplicateRecords() {		
		assertAll(
				() -> assertIterableEquals(getExpectDupRecordList(), statementValidator.getDuplicateRecords(mockRecordList),
						"Failed to get duplicate records."),
				() -> assertNull(statementValidator.getDuplicateRecords(null)),
				() -> assertIterableEquals(new ArrayList<Record>(),
						statementValidator.getDuplicateRecords(new ArrayList<Record>())));
	}

	@Test
	@DisplayName("When getting Wrong End Balance Records")
	void testGetWrongEndBalanceRecords() {
		assertAll(
		() -> assertIterableEquals(getExpectWrongEndBalRecordList(), statementValidator.getWrongEndBalanceRecords(mockRecordList),
				"Failed to get wrong end balance records."),
		() -> assertNull(statementValidator.getWrongEndBalanceRecords(null)),
		() -> assertIterableEquals(new ArrayList<Record>(), statementValidator.getWrongEndBalanceRecords(new ArrayList<Record>()))
		);
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
				BigDecimal.valueOf(-27.43), BigDecimal.valueOf(3.47)));
		return recordList;
	}

	List<Record> getExpectDupRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112806, "NL27SNSB0917829872", "Clothes for Jake", BigDecimal.valueOf(26.32),
				BigDecimal.valueOf(48.98), BigDecimal.valueOf(75.3)));
		recordList.add(new Record(112806, "NL27SNSB0917829873", "Subscription for peter", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		return recordList;
	}

	List<Record> getExpectWrongEndBalRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(112807, "NL27SNSB0917829871", "Tickets from Richerd", BigDecimal.valueOf(99.44),
				BigDecimal.valueOf(41.23), BigDecimal.valueOf(120.67)));
		recordList.add(new Record(112808, "NL27SNSB0917829871", "Flowers from Jan Bakker", BigDecimal.valueOf(23.96),
				BigDecimal.valueOf(-27.43), BigDecimal.valueOf(3.47)));
		return recordList;
	}

}
