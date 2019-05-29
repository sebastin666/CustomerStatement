package com.rbb.report.parse.util;

import org.springframework.web.multipart.MultipartFile;

public interface Parser {
		
	public Object parseToObject(MultipartFile file) throws UnsupportedOperationException;

}
