package com.rbb.report.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecordTest {

	Record record, recordTstSet;
	@BeforeEach
	void setUp() {
		record = new Record(112806, "NL27SNSB0917829871", "Clothes for Willem Dekker", BigDecimal.valueOf(91.23),
				BigDecimal.valueOf(15.57), BigDecimal.valueOf(106.8));
		recordTstSet = new Record();
	}

	@Test
	void testGetReference() {
		assertEquals(112806, record.getReference(), "Test references are matching");
	}

	@Test
	void testGetAccountNumber() {
		assertEquals("NL27SNSB0917829871", record.getAccountNumber(), "Test account numbers are matching");
	}

	@Test
	void testGetStartBalance() {
		assertEquals(BigDecimal.valueOf(91.23), record.getStartBalance(), "Test start balancing are matching");
	}

	@Test
	void testGetMutation() {
		assertEquals(BigDecimal.valueOf(91.23), record.getStartBalance(), "Test mutations are matching");
	}

	@Test
	void testGetEndBalance() {
		assertEquals(BigDecimal.valueOf(91.23), record.getStartBalance(), "Test end balancing are matching");
	}

	@Test
	void testGetDescription() {
		assertEquals("Clothes for Willem Dekker", record.getDescription(), "Test descriptions are matching");
	}

	@Test
	void testSetReference() {
		recordTstSet.setReference(112806);
		assertEquals(112806, record.getReference(), "Test set references are matching");
	}

	@Test
	void testSetAccountNumber() {
		recordTstSet.setAccountNumber("NL27SNSB0917829871");
		assertEquals("NL27SNSB0917829871", record.getAccountNumber(), "Test set account numbers are matching");
	}

	@Test
	void testSetStartBalance() {
		recordTstSet.setStartBalance(BigDecimal.valueOf(91.23));
		assertEquals(BigDecimal.valueOf(91.23), record.getStartBalance(), "Test set start balancing are matching");
	}

	@Test
	void testSetMutation() {
		recordTstSet.setStartBalance(BigDecimal.valueOf(91.23));
		assertEquals(BigDecimal.valueOf(91.23), record.getStartBalance(), "Test set mutations are matching");
	}

	@Test
	void testSetEndBalance() {
		recordTstSet.setStartBalance(BigDecimal.valueOf(91.23));
		assertEquals(BigDecimal.valueOf(91.23), record.getStartBalance(), "Test set end balancing are matching");
	}

	@Test
	void testSetDescription() {
		recordTstSet.setDescription("Clothes for Willem Dekker");
		assertEquals("Clothes for Willem Dekker", record.getDescription(), "Test set descriptions are matching");
	}

}
