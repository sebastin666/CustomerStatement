package com.rbb.report.parse.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.rbb.report.model.Records;

public class XMLObjectMapper implements ObjectMapper {
		
	@Override
	public Object mapObject(File file) throws UnsupportedOperationException {
		Object obj = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Records.class);
			Unmarshaller parser = context.createUnmarshaller();
			obj = parser.unmarshal(file);
		} catch (JAXBException e) {
			System.out.println("Error occured at xml parsing ::" + e.getMessage());
			throw new UnsupportedOperationException(e.getMessage());
		}
		return obj;
	}

}
