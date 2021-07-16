package ecolight.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CoordinatesControllerTest {

	private final String URI = "/api/ecolight/triangle-calculator";

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testCountTrianglesFromCoordinates() throws Exception {
		
		String json = "{\"coordinates\" : [{\"x\":2,\"y\":1},{\"x\":5,\"y\":3},{\"x\":-4,\"y\":-3},{\"x\":1,\"y\":-5}]}";
		
		mockMvc
				.perform(post(URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numberOfTriangles").value(3));
	}
}
