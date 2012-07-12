/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.math.MathContext;
import java.util.ArrayList;
import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class Map {

    ArrayList<Element> mp;
    Matrix world;
    float height, width;

    public Map() {
        mp = new ArrayList<Element>();
        world = new Matrix(50, 50);
        height= 10;
        width = 10;
        generate();
    }

    public void draw() {
        for (Element element : mp) {
            element.draw();
        }
        for (Object object : world.getNeighboursOf(20, 20, 7)) {

            ((GraphicElement) object).draw();
        }

    }

    public void generate() {
        int w = world.getColumns();
        float x = -width * world.getColumns() / 2;
        float y = -height * world.getRows() / 2;
        int h = world.getRows();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                world.insertObject(new GraphicElement(width, height, x, y), i, j);
                y += height;

            }
            x += width;

            y = -height * world.getRows() / 2;
        }
    }
}
