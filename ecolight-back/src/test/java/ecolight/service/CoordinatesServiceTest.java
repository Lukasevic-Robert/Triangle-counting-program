package ecolight.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class CoordinatesServiceTest {

	@Autowired
	private CoordinatesService coordinatesService;
	
	private Point2D.Double a = new Point2D.Double(2, 1);
	private Point2D.Double b = new Point2D.Double(5, 3);
	private Point2D.Double c = new Point2D.Double(-4, -3);
	private Point2D.Double d = new Point2D.Double(1, -5);
	private Point2D.Double e = new Point2D.Double(-5, -5);
	private Point2D.Double f = new Point2D.Double(6, 36);
	private Point2D.Double g = new Point2D.Double(-10, 12);
	private Point2D.Double h = new Point2D.Double(10, -50);
	
	private ArrayList<Point2D.Double> coordinates;
	
	   @BeforeAll
	    public void setup() {
			coordinates = new ArrayList<>();
			coordinates.add(a);
			coordinates.add(b);
			coordinates.add(c);
			coordinates.add(d);
			coordinates.add(e);
			coordinates.add(f);
			coordinates.add(g);
			coordinates.add(h);
	    }

	@Test
	@Order(1)
	void testCountTrianglesFromCoordinates() {
		assertEquals(55, coordinatesService.countTrianglesFromCoordinates(coordinates));
	}

	@Test
	void testIsTriangle() {
		assertAll(
		() -> assertFalse(coordinatesService.isTriangle(a, b, c)),
		() -> assertTrue(coordinatesService.isTriangle(a, b, d))
				);
	}

	@Test
	void testGetUniqueListOfCoordinates() {
		coordinates.add(e);
		coordinates.add(f);
		coordinates.add(a);
		boolean unique = true;
	
		List<Point2D.Double> testArray = coordinatesService.getUniqueListOfCoordinates(coordinates);
		for (int i = 0; i < testArray.size(); i++) {
			if(!testArray.get(i).equals(coordinates.get(i))) {
				unique = false;
			}	
		}
		assertTrue(unique);
		assertEquals(coordinates.size() - 3, coordinatesService.getUniqueListOfCoordinates(coordinates).size());
	}

	@Test
	void testGetTriangleAreaFromCoordinates() {
		assertEquals(8, coordinatesService.getTriangleAreaFromCoordinates(a, b, d));
	}
}
