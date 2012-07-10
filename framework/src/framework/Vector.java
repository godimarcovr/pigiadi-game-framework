package framework;

import java.util.Random;

public class Vector {

	public float x, y;

	public Vector(float Ix, float Iy) {
		x = Ix;
		y = Iy;
	}

	public Vector scale(float scale) {
		return new Vector(x * scale, y * scale);
	}

	public Vector multiply(Vector other) {
		return new Vector(x * other.x, y * other.y);
	}

	public Vector perp() {
		Vector vect = new Vector(-y, x);
		return vect;// y = -y;
	}

	public void randomize(Vector max, Vector min) { //randomize position
		Random r = new Random();
		x = (r.nextFloat() * (max.x - min.x) + min.x);
		y = (r.nextFloat() * (max.y - min.y) + min.y);
	}

	public double dot(Vector axis) { //dot product
		return x * axis.x + y * axis.y;
	}

}
