package com.rbb.report.parse.util;

import org.springframework.web.multipart.MultipartFile;

public interface ObjectMapper {
		
	public Object mapObject(MultipartFile file) throws UnsupportedOperationException;

}
