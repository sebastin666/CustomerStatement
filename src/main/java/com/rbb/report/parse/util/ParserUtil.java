package com.rbb.report.parse.util;

import org.springframework.web.multipart.MultipartFile;

public final class ParserUtil {

	public static Object parse(String fileType, MultipartFile file) throws UnsupportedOperationException{
		
		return ParserFactory.getParser(fileType).parse(file);
		
	}
}
