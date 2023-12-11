package com.cst438;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FinalProjectApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
    FlightRepository flightRepository;


	@Test
	public void testFlights() throws Exception  {
		MockHttpServletResponse response;

		response = mvc.perform(MockMvcRequestBuilders.get("/flights").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();


		assertEquals(200, response.getStatus());

	}

}
