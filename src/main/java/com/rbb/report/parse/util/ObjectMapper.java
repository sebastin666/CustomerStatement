package com.rbb.report.parse.util;

import java.io.File;

import com.rbb.report.model.Record;
import com.rbb.report.model.Records;

public interface ObjectMapper {
		
	public Object mapObject(File file) throws UnsupportedOperationException;

}
