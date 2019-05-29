package com.rbb.report.parse.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rbb.report.model.Record;
import com.rbb.report.model.Records;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class CSVParser implements Parser {

	Logger logger = LoggerFactory.getLogger(CSVParser.class);
	
	@Override
	public Object parseToObject(MultipartFile file) throws UnsupportedOperationException {

		BeanListProcessor<Record> rowProcessor = new BeanListProcessor<Record>(Record.class);
		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.getFormat().setLineSeparator("\n");
		parserSettings.setProcessor(rowProcessor);
		parserSettings.setHeaderExtractionEnabled(true);

		CsvParser parser = new CsvParser(parserSettings);
		try (Reader reader = new InputStreamReader(file.getInputStream())) {
			parser.parse(reader);
		} catch (IOException ex) {
			logger.error("Error occured while parsing xml. " + ex.getMessage());
			throw new UnsupportedOperationException("Error occured while parsing csv. ");
		}
		//
		return new Records(rowProcessor.getBeans());
	}

}
