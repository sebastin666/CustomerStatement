package com.rbb.report.parse.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.rbb.report.model.Record;
import com.rbb.report.model.Records;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class CSVObjectMapper implements ObjectMapper{

	@Override
	public Object mapObject(File file) throws UnsupportedOperationException {
		
		BeanListProcessor rowProcessor = new BeanListProcessor(Record.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.setProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);

        CsvParser parser = new CsvParser(parserSettings);
        try(Reader reader = new InputStreamReader(new FileInputStream(file));){
        	parser.parse(reader);            
        }catch(Exception ex){
        	ex.printStackTrace();
        	throw new UnsupportedOperationException("Error occured at IO operation");
        }
        //
		return new Records(rowProcessor.getBeans());
	}
	
}
