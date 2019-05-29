package com.rbb.report.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecordsTest {

	List<Record> recordList = new ArrayList<>();
	Records records = new Records();
	
	@BeforeEach
	void setUp() {
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		records.setRecords(recordList);
	}
	
	@Test
	void testGetRecords() {
		assertIterableEquals(recordList, records.getRecords(), "Test list of records are matching.");
	}
	
	@Test
	void testSetRecords() {
		records = new Records();
		records.setRecords(null);
		assertNull(records.getRecords(), "Test list of records should be null.");
	}

}
