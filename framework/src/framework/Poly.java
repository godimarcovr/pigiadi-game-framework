package framework;

import java.awt.Color;
import java.awt.Graphics;

public class Poly {

	private int count = 0;
	private Vector vertices[];

    public Poly() {
    }

        
        
	public Poly(int count, double radius) {
		this.count = count;
		double a;
		vertices = new Vector[this.count];

		for (int i = 0; i < this.count; i++) {
			a = 2 * Math.PI * i / this.count;
			float tempX = (float) (Math.cos(a) * radius);
			float tempY = (float) (Math.sin(a) * radius);
			vertices[i] = new Vector(tempX, tempY);
		}
	}

        public static Poly createRect(float w, float h){
            Poly p = new Poly(4, 30);
            p.setVertices(new Vector[]{new Vector(0, 0), new Vector(w, 0), new Vector(w, h), new Vector(0, h)});
            return p;
        }

	public void Transform(Vector position, double rotation) {
		translate((int) position.x, (int) position.y);
		rotate(rotation);
	}

	public void translate(float x, float y) {
		for (int i = 0; i < count; i++) {
			vertices[i].x += x;
			vertices[i].y += y;
		}
		findCenter();
	}
	
	public int xCenter, yCenter;

	public void findCenter() {

		double area = 0;
		int xSum = 0, ySum = 0;

		// find Area
		for (int i = 0; i < count - 1; i++) {
			area += vertices[i].x*vertices[i+1].y - vertices[i+1].x * vertices[i].y;
			xSum += (vertices[i].x + vertices[i+1].x) * (vertices[i].x * vertices[i+1].y - vertices[i+1].x * vertices[i].y);
			ySum += (vertices[i].y + vertices[i+1].y) * (vertices[i].x * vertices[i+1].y - vertices[i+1].x * vertices[i].y);
		}
		area += vertices[count-1].x*vertices[0].y - vertices[0].x * vertices[count-1].y;
		xSum += (vertices[count-1].x + vertices[0].x) * (vertices[count-1].x * vertices[0].y - vertices[0].x * vertices[count-1].y);
		ySum += (vertices[count-1].y + vertices[0].y) * (vertices[count-1].x * vertices[0].y - vertices[0].x * vertices[count-1].y);
	
		area = area / 2;

		xCenter = (int) (xSum / (6 * area));
		yCenter = (int) (ySum / (6 * area));
	}
	
	public void rotate(double rot) {
		// findCenter(); if you leave this on each time, the polygon will shift
		// slightly if rotated a lot

		for (int i = 0; i < count; i++) {
			double Xo = vertices[i].x; // temp X location for use in y vertices
										// modification
			vertices[i].x = (float) (xCenter + ((Xo - xCenter) * Math.cos(rot) - (vertices[i].y - yCenter) * Math.sin(rot)));
			vertices[i].y = (float) (yCenter + ((Xo - xCenter) * Math.sin(rot) + (vertices[i].y - yCenter) * Math.cos(rot)));
		}
	}

	
	public boolean collide(Poly poly){
		// test separation axes of current polygon
		for(int j = count-1, i = 0; i < count; j = i, i++)
		{
			Vector v0 = vertices[j];
			Vector v1 = vertices[i];

			Vector edge = new Vector(0,0);
			edge.x = v1.x - v0.x; // edge
			edge.y = v1.y - v0.y; // edge
			
			Vector axis = edge.perp(); // Separate axis is perpendicular to the edge

			if(separatedByAxis(axis, poly))
				return false;
		}

		// test separation axes of other polygon
		for(int j = poly.count-1, i = 0; i < poly.count; j = i, i++)
		{
			Vector v0 = poly.vertices[j];
			Vector v1 = poly.vertices[i];

			Vector edge2 = new Vector(0,0);
			edge2.x = v1.x - v0.x; // edge
			edge2.y = v1.y - v0.y; // edge
			
			Vector axis = edge2.perp(); // Separate axis is perpendicular to the edge

			if(separatedByAxis(axis, poly))
				return false;
		}
		return true;
	}
	
	float min,max;

	public void calculateInterval(Vector axis) {
		this.min = this.max = (float) vertices[0].dot(axis);

		for (int i = 1; i < count; i++) {
			float d = (float) vertices[i].dot(axis);
			if (d < this.min)
				this.min = d;
			else if (d > this.max)
				this.max = d;
		}
	}

	public boolean intervalsSeparated(float mina, float maxa, float minb,
			float maxb) {
		return (mina > maxb) || (minb > maxa);
	}

	float mina, maxa;
	float minb, maxb;

	public boolean separatedByAxis(Vector axis, Poly poly) {
		calculateInterval(axis);
		mina = min;
		maxa = max;
		poly.calculateInterval(axis);
		minb = poly.min;
		maxb = poly.max;
		return intervalsSeparated(mina, maxa, minb, maxb);
	}
        
        public Vector[] getVertices(){
            return vertices;
        }

        public Vector[] getCloneVertices(){
            Vector[] ret=new Vector[vertices.length];
            for (int i = 0; i < vertices.length; i++) {
                ret[i]=new Vector(vertices[i].x,vertices[i].y);
            }
            return ret;
        }
        
        public void setVertices(Vector[] v){
            vertices = v;
            findCenter();
        }
        

            
}