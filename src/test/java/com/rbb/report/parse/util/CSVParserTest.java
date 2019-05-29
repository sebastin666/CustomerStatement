package com.rbb.report.parse.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rbb.report.MockBase;
import com.rbb.report.model.Records;

class CSVParserTest extends MockBase {

	CSVParser csvParser = new CSVParser();

	@Test
	void testParseToObject() {
		assertEquals(getMockCSVRecordList(), ((Records) csvParser.parse(mockCSVMultipartFile)).getRecords(), "Test CSV pasring");
	}

}
