package ecolight.DTO;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesWrapper {
	private ArrayList<Point2D.Double> coordinates;
}
