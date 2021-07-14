package ecolight.controller;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecolight.DTO.CoordinatesWrapper;
import ecolight.service.CoordinatesService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/ecolight")
public class CoordinatesController {

	private CoordinatesService coordinatesService;

	// using POST over GET because React.js does not support sending body with get request
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/count-triangles")
	public Map<String, Integer> countTrianglesFromCoordinates(@RequestBody CoordinatesWrapper coordinatesWrapper) {
		List<Point2D.Double> uniqueCoordinates = coordinatesService
				.getUniqueListOfCoordinates(coordinatesWrapper.getCoordinates());
		Map<String, Integer> response = new HashMap<>();
		response.put("numberOfTriangles", coordinatesService.countTrianglesFromCoordinates(uniqueCoordinates));
		return response;
	}
}
