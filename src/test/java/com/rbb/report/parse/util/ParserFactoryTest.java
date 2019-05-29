package com.rbb.report.parse.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class ParserFactoryTest {

	Parser xmlParser;
	Parser csvParser;
	
	@BeforeEach
	void setup() {
		xmlParser = Mockito.mock(XMLParser.class);
		csvParser = Mockito.mock(CSVParser.class);
	}
	
	@Test
	void testGetParser() {
		assertAll(
//		() -> assertEquals(xmlParser, ParserFactory.getParser("xml")),
//		() -> assertEquals(csvParser, ParserFactory.getParser("csv")),
		() -> assertThrows(UnsupportedOperationException.class, () -> ParserFactory.getParser(null)),
		() -> assertThrows(UnsupportedOperationException.class, () -> ParserFactory.getParser("txt"))
		);
	}

}
