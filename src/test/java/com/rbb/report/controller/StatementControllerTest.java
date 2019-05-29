package com.rbb.report.controller;

import com.rbb.report.model.Record;
import com.rbb.report.service.StatementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

public class StatementControllerTest {

	private StatementController controller = new StatementController();
	StatementService statementService = Mockito.mock(StatementService.class);
	MockMultipartFile mockMultipartFile = new MockMultipartFile("records", "records.csv", "text/plain",
			getMockCSVData().getBytes());

	@Test
	public void testStatementReceiverWithNoContent() {
		Whitebox.setInternalState(controller, "statementService", statementService);
		ResponseEntity<?> entity = controller.statementReceiver(mockMultipartFile);
		assertThat(entity.getStatusCode().value()).isEqualTo(204);
	}

	@Test
	public void testStatementReceiverWithContent() {
		Whitebox.setInternalState(controller, "statementService", statementService);
		List<Record> data = new ArrayList<>();
		data.add(new Record());
		Mockito.when(statementService.genarateReport(any())).thenReturn(data);
		ResponseEntity<?> entity = controller.statementReceiver(mockMultipartFile);
		assertThat(entity.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void testStatementReceiverWithException() {
		List<Record> data = new ArrayList<>();
		data.add(new Record());
		Mockito.when(statementService.genarateReport(any())).thenReturn(data);
		ResponseEntity<?> entity = controller.statementReceiver(mockMultipartFile);
		assertThat(entity.getStatusCode().value()).isEqualTo(400);
	}

	String getMockCSVData() {
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

}