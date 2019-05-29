package com.rbb.report.parse.util;

public class ParserFactory {

	public static Parser getParser(String fileType) throws UnsupportedOperationException {
		
		if(fileType == null) {
			throw new UnsupportedOperationException("File type can't be null");
		}

		switch (fileType) {
		case "xml":
			return new XMLParser();
		case "csv":
			return new CSVParser();
		default:
			throw new UnsupportedOperationException("Not supported. Unknown file format "+fileType);
		}
	}

}
