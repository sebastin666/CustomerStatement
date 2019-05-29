package com.rbb.report.controller;

import com.rbb.report.MockBase;
import com.rbb.report.model.Record;
import com.rbb.report.service.StatementService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

public class StatementControllerTest extends MockBase{

	private StatementController controller;
	private StatementService statementService;
	
	@BeforeEach
	void setUp() {
		controller = new StatementController();				
		//
		statementService = Mockito.mock(StatementService.class);
		Whitebox.setInternalState(controller, "statementService", statementService);
	}

	@Test
	public void testStatementReceiverWithNoContent() {		
		ResponseEntity<?> entity = controller.validateStatement(mockCSVMultipartFile);
		assertThat(entity.getStatusCode().value()).isEqualTo(204);
	}

	@Test
	public void testStatementReceiverWithContent() {
		List<Record> data = new ArrayList<>();
		data.add(new Record());
		Mockito.when(statementService.genarateReport(any())).thenReturn(data);
		ResponseEntity<?> entity = controller.validateStatement(mockCSVMultipartFile);
		assertThat(entity.getStatusCode().value()).isEqualTo(200);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStatementReceiverWithInvalidFileContent() {
		Mockito.when(statementService.genarateReport(any())).thenThrow(UnsupportedOperationException.class);
		ResponseEntity<?> entity = controller.validateStatement(mockCSVMultipartFile);
		assertThat(entity.getStatusCode().value()).isEqualTo(400);
	}

}