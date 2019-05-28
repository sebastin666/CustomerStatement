package com.rbb.report.parse.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjectMapperFactoryTest {

	@Test
	void testGetObjectMapper() {
		assertThrows(UnsupportedOperationException.class, () -> ObjectMapperFactory.getObjectMapper(null));
		assertThrows(UnsupportedOperationException.class, () -> ObjectMapperFactory.getObjectMapper("txt"));
	}

}
