package com.rbb.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;

import com.rbb.report.model.Record;

public class MockBase {

	protected List<Record> mockRecordList = getMockDupRecordList();
	protected MockMultipartFile mockCSVMultipartFile = new MockMultipartFile("records", "records.csv", "text/plain",
			getMockCSVData().getBytes());

	protected String getMockCSVData() {
		return "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\r\n"
				+ "194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\r\n"
				+ "112806,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\r\n"
				+ "183049,NL69ABNA0433647324,Clothes for Jan King,86.66,+44.5,131.16\r\n"
				+ "183356,NL74ABNA0248990274,Subscription for Peter de Vries,92.98,-46.65,46.33\r\n"
				+ "112806,NL69ABNA0433647324,Clothes for Richard de Vries,90.83,-10.91,79.92\r\n"
				+ "112806,NL93ABNA0585619023,Tickets from Richard Bakker,102.12,+45.87,147.99\r\n"
				+ "139524,NL43AEGO0773393871,Flowers from Jan Bakker,99.44,+41.23,140.67\r\n"
				+ "179430,NL93ABNA0585619023,Clothes for Vincent Bakker,23.96,-27.43,-3.47\r\n"
				+ "141223,NL93ABNA0585619023,Clothes from Erik Bakker,94.25,+41.6,135.85\r\n"
				+ "195446,NL74ABNA0248990274,Flowers for Willem Dekker,26.32,+48.98,75.3";
	}

	protected String getMockXMLData() {
		return "<records>\r\n" + "  <record reference=\"130498\">\r\n"
				+ "    <accountNumber>NL69ABNA0433647324</accountNumber>\r\n"
				+ "    <description>Tickets for Peter Theuß</description>\r\n"
				+ "    <startBalance>26.9</startBalance>\r\n" + "    <mutation>-18.78</mutation>\r\n"
				+ "    <endBalance>8.12</endBalance>\r\n" + "  </record>\r\n" + "  <record reference=\"167875\">\r\n"
				+ "    <accountNumber>NL93ABNA0585619023</accountNumber>\r\n"
				+ "    <description>Tickets from Erik de Vries</description>\r\n"
				+ "    <startBalance>5429</startBalance>\r\n" + "    <mutation>-939</mutation>\r\n"
				+ "    <endBalance>6368</endBalance>\r\n" + "  </record>\r\n" + "  <record reference=\"147674\">\r\n"
				+ "    <accountNumber>NL93ABNA0585619023</accountNumber>\r\n"
				+ "    <description>Subscription from Peter Dekker</description>\r\n"
				+ "    <startBalance>74.69</startBalance>\r\n" + "    <mutation>-44.91</mutation>\r\n"
				+ "    <endBalance>29.78</endBalance>\r\n" + "  </record>\r\n" + "</records>";
	}

	protected String getMockXMLInvalidData() {
		return "<records>\r\n" + "  <record reference=\"130498TTT\">\r\n"
				+ "    <accountNumber>NL69ABNA0433647324</accountNumber>\r\n"
				+ "    <description>Tickets for Peter Theuß</description>\r\n"
				+ "    <startBalance>26.9</startBalance>\r\n" + "    <mutation>-18.78</mutation>\r\n"
				+ "    <endBalance>8.12</endBalance>\r\n" + "  </record>\r\n" + "</records>";
	}

	protected List<String> getAllowedFormats() {
		List<String> allowedFormats = new ArrayList<>();
		allowedFormats.add("csv");
		allowedFormats.add("xml");
		return allowedFormats;
	}

	protected List<Record> getMockXMLRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(130498, "NL69ABNA0433647324", "Tickets for Peter Theuß", BigDecimal.valueOf(26.9),
				BigDecimal.valueOf(-18.78), BigDecimal.valueOf(8.12)));
		recordList.add(new Record(167875, "NL93ABNA0585619023", "Tickets from Erik de Vries", BigDecimal.valueOf(5429),
				BigDecimal.valueOf(-939), BigDecimal.valueOf(6368)));
		recordList.add(new Record(147674, "NL93ABNA0585619023", "Subscription from Peter Dekker",
				BigDecimal.valueOf(74.69), BigDecimal.valueOf(-44.91), BigDecimal.valueOf(29.78)));
		return recordList;
	}

	protected List<Record> getMockCSVRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(194261, "NL91RABO0315273637", "Clothes from Jan Bakker", BigDecimal.valueOf(21.6),
				BigDecimal.valueOf(-41.83), BigDecimal.valueOf(-20.23)));
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(183049, "NL69ABNA0433647324", "Clothes for Jan King", BigDecimal.valueOf(86.66),
				BigDecimal.valueOf(44.5), BigDecimal.valueOf(131.16)));
		recordList.add(new Record(183356, "NL74ABNA0248990274", "Subscription for Peter de Vries",
				BigDecimal.valueOf(92.98), BigDecimal.valueOf(-46.65), BigDecimal.valueOf(46.33)));
		recordList.add(new Record(112806, "NL69ABNA0433647324", "Clothes for Richard de Vries",
				BigDecimal.valueOf(90.83), BigDecimal.valueOf(-10.91), BigDecimal.valueOf(79.92)));
		recordList.add(new Record(112806, "NL93ABNA0585619023", "Tickets from Richard Bakkerr",
				BigDecimal.valueOf(102.12), BigDecimal.valueOf(45.87), BigDecimal.valueOf(147.99)));
		recordList.add(new Record(139524, "NL43AEGO0773393871", "Flowers from Jan Bakker", BigDecimal.valueOf(99.44),
				BigDecimal.valueOf(41.23), BigDecimal.valueOf(140.67)));
		recordList.add(new Record(179430, "NL93ABNA0585619023", "Clothes for Vincent Bakker", BigDecimal.valueOf(23.96),
				BigDecimal.valueOf(-27.43), BigDecimal.valueOf(-3.47)));
		recordList.add(new Record(141223, "NL93ABNA0585619023", "Clothes from Erik Bakker", BigDecimal.valueOf(94.25),
				BigDecimal.valueOf(41.6), BigDecimal.valueOf(135.85)));
		recordList.add(new Record(195446, "NL74ABNA0248990274", "Flowers for Willem Dekker", BigDecimal.valueOf(26.32),
				BigDecimal.valueOf(48.98), BigDecimal.valueOf(75.3)));
		return recordList;
	}

	protected List<Record> getMockDupRecordList() {
		List<Record> recordList = new ArrayList<>();
		recordList.add(new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112806, "NL27SNSB0917829872", "Clothes for Jake", BigDecimal.valueOf(26.32),
				BigDecimal.valueOf(48.98), BigDecimal.valueOf(75.3)));
		recordList.add(new Record(112807, "NL27SNSB0917829871", "Tickets from Richerd", BigDecimal.valueOf(99.44),
				BigDecimal.valueOf(41.23), BigDecimal.valueOf(120.67)));
		recordList.add(new Record(112806, "NL27SNSB0917829873", "Subscription for peter", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8)));
		recordList.add(new Record(112808, "NL27SNSB0917829871", "Flowers from Jan Bakker", BigDecimal.valueOf(23.96),
				BigDecimal.valueOf(-27.43), BigDecimal.valueOf(-3.47)));
		return recordList;
	}
}
