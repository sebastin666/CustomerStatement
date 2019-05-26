package com.rbb.report.parse.util;

public class ObjectMapperFactory {

	public static ObjectMapper getObjectMapper(String fileType) throws UnsupportedOperationException {

		if (fileType == null) {
			throw new UnsupportedOperationException("File type can't be null");
		}

		if (fileType.equalsIgnoreCase("xml")) {
			return new XMLObjectMapper();
		} else if (fileType.equalsIgnoreCase("csv")) {
			return new CSVObjectMapper();
		} else {
			throw new UnsupportedOperationException("File type can't be null");
		}

	}

}
