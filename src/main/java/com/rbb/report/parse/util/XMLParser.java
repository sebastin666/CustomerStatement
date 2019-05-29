package com.rbb.report.parse.util;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rbb.report.model.Records;

public class XMLParser implements Parser {
		
	Logger logger = LoggerFactory.getLogger(XMLParser.class);
	
	@Override
	public Object parseToObject(MultipartFile file) throws UnsupportedOperationException {
		Object obj = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Records.class);
			Unmarshaller parser = context.createUnmarshaller();
			obj = parser.unmarshal(file.getInputStream());
		} catch (Exception e) {			
			logger.error("Error occured while parsing xml. " + e.getMessage());
			throw new UnsupportedOperationException("Error occured while parsing xml. ");
		}
		return obj;
	}

}
