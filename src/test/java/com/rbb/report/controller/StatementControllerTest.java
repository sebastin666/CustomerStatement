package com.rbb.report.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
class StatementControllerTest {

    private MockMvc mockMvc;
    private StatementController controller;
    
    @BeforeEach
    public void init() {
    	controller = new StatementController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        is = controller.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
    }

    
	@Test
	@Disabled
	void testStatementReceiver() {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file","records.csv",
	              "text/plain", getMockCSVData().getBytes());

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/statement/upload")
	    		  .file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA);
	      try {
			MvcResult result = mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
			assertEquals(200, result.getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String getMockCSVData() {
		return "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\r\n" + 
				"194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\r\n" + 
				"112806,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\r\n" + 
				"183049,NL69ABNA0433647324,Clothes for Jan King,86.66,+44.5,131.16\r\n" + 
				"183356,NL74ABNA0248990274,Subscription for Peter de Vries,92.98,-46.65,46.33\r\n" + 
				"112806,NL69ABNA0433647324,Clothes for Richard de Vries,90.83,-10.91,79.92\r\n" + 
				"112806,NL93ABNA0585619023,Tickets from Richard Bakker,102.12,+45.87,147.99\r\n" + 
				"139524,NL43AEGO0773393871,Flowers from Jan Bakker,99.44,+41.23,140.67\r\n" + 
				"179430,NL93ABNA0585619023,Clothes for Vincent Bakker,23.96,-27.43,-3.47\r\n" + 
				"141223,NL93ABNA0585619023,Clothes from Erik Bakker,94.25,+41.6,135.85\r\n" + 
				"195446,NL74ABNA0248990274,Flowers for Willem Dekker,26.32,+48.98,75.3";
	}

}
