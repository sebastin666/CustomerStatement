package com.rbb.report.parse.util;

import org.springframework.web.multipart.MultipartFile;

public interface Parser {
		
	public Object parse(MultipartFile file) throws UnsupportedOperationException;

}
