package com.rbb.report.parse.util;

import java.io.File;

public final class Parser {

	public static Object parse(String fileType, File file) throws UnsupportedOperationException{
		
		return ObjectMapperFactory.getObjectMapper(fileType).mapObject(file);
		
	}
}
