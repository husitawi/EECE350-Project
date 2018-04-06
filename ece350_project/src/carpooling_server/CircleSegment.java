package carpooling_server;

import java.util.*;

public class CircleSegment {
	
	public static boolean isIntersecting(double circlex, double circley, double radius, double x1, double y1, double x2, double y2) {
		
		//Calculate change in x and y for the segment
		double deltax = x2 - x1;
		double deltay = y2 - y1;

		//Set up our quadratic formula
		double a = deltax * deltax + deltay * deltay;
		double b = 2 * (deltax * (x1-circlex) + deltay * (y1 - circley));
		double c = (x1 - circlex) * (x1 - circlex) + (y1 - circley) * (y1 - circley) - radius * radius;

		//Check if there is a negative in the discriminant
		double discriminant = b * b - 4 * a * c;
		if (discriminant < 0) 
			return false;

		//Try both +- in the quadratic formula
		double quad1 = (-b + Math.sqrt(discriminant))/(2 * a);
		double quad2 = (-b - Math.sqrt(discriminant))/(2 * a);

		//If the result is between 0 and 1, there is an intersection
		if (quad1 >= 0 && quad1 <= 1) 
			return true;
		else if (quad2 >= 0 && quad2 <= 1) 
			return true;
		return false;
	}

}
