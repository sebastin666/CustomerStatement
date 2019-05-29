package com.rbb.report.parse.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.rbb.report.MockBase;
import com.rbb.report.model.Records;

class XMLParserTest extends MockBase {

	private XMLParser xmlParser = new XMLParser();
	private MockMultipartFile mockXMLMultipartFile;
	private MockMultipartFile mockInvalidXMLMultipartFile;
	private MockMultipartFile mockWrongFormatMultipartFile;

	@BeforeEach
	void setUp() {
		try {
			mockXMLMultipartFile = new MockMultipartFile("records", "records.xml", "text/plain",
					getMockXMLData().getBytes("UTF8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			mockInvalidXMLMultipartFile = new MockMultipartFile("records", "records.xml", "text/plain",
					getMockXMLInvalidData().getBytes("UTF8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testParseToObject() {
		if (mockXMLMultipartFile != null)
			assertEquals(getMockXMLRecordList(), ((Records) xmlParser.parse(mockXMLMultipartFile)).getRecords(),
					"Test XML pasring");
	}

	@Test
	void testParseToObjectWithInvalidData() {
		if (mockInvalidXMLMultipartFile != null)
			assertThrows(UnsupportedOperationException.class,
					() -> xmlParser.parse(mockInvalidXMLMultipartFile), "Test XML pasring with invalid data");
	}

}
