package ecolight.service;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CoordinatesService {

	// run getUniqueListOfCoordinates() first to get unique coordinates, then run this method. Unless you are sure there are only unique values.
	public int countTrianglesFromCoordinates(List<Point2D.Double> coordinates) {
		int triangleCounter = 0;
		int length = coordinates.size();

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				for (int k = j + 1; k < length; k++) {
					if (isTriangle(coordinates.get(i), coordinates.get(j), coordinates.get(k))) {
						triangleCounter++;
					}
				}
			}
		}
		return triangleCounter;
	}

	public boolean isTriangle(Point2D a, Point2D b, Point2D c) {
		return getTriangleAreaFromCoordinates(a, b, c) != 0;
	}

	public List<Point2D.Double> getUniqueListOfCoordinates(List<Point2D.Double> coordinates) {
		return coordinates.stream().distinct().collect(Collectors.toList());
	}

	public double getTriangleAreaFromCoordinates(Point2D a, Point2D b, Point2D c) {
		return Math.abs((
				a.getX() * (b.getY() - c.getY()) + 
				b.getX() * (c.getY() - a.getY()) + 
				c.getX() * (a.getY() - b.getY()))
				/ 2);
	}
}
