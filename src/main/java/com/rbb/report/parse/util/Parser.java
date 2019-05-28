package com.rbb.report.parse.util;

import org.springframework.web.multipart.MultipartFile;

public final class Parser {

	public static Object parse(String fileType, MultipartFile file) throws UnsupportedOperationException{
		
		return ObjectMapperFactory.getObjectMapper(fileType).mapObject(file);
		
	}
}
