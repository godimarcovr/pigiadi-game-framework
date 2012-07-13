/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

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
        world = new Matrix(100, 100);
        height = 10;
        width = 10;
        generate();
    }

    public void draw() {
        Color.red.bind();
        for (Element element : mp) {
            element.draw();
        }
       for (Object object : world.getNeighboursOf(Window.game2.pl.c, Window.game2.pl.r, 20)) {

            ((GraphicElement) object).draw();
        }
        /*
             for (int i = 0; i < world.getColumns(); i++) {
            for (int j = 0; j < world.getRows(); j++) {
                
                ((GraphicElement) world.getElementAt(i, j)).draw();
            }
        }*/
        TextureImpl.bindNone();
    }

    public void generate() {
        int w = world.getColumns();
        float x = -width* world.getColumns()/2 ;
        float y = -height* world.getRows()/2 ;
        int h = world.getRows();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                world.insertObject(new GraphicElement(width, height,x,y), i, j);
                y += height;

            }
            x += width;

            y = -height* world.getRows() / 2;
        }
       generateObstacles(50);
    }

    public void generateObstacles(int nObstacles) {
        Random r = new Random();
        int n = 0;
        int l =0;
        int maxLength = 5;
        
        while (n < nObstacles) {
            int x = r.nextInt(world.getColumns());
            int y = r.nextInt(world.getRows());
            float newX = ((GraphicElement) world.getElementAt(x, y)).p.x;
            float newY = ((GraphicElement) world.getElementAt(x, y)).p.y;
            mp.add(new Element(width/2 - 1, height/2 - 1,newX, newY));
            while (l<maxLength){
                mp.add( new Element(width/2 - 1, height/2 - 1,newX+l*width/2, newY));
                l++;
            }
            l = 0;
            n++;
        }
    }

        //world.insertObject(new Element(0, 0, 10, 10), 0, 0);

        public Position getEntityCoordinates(Entity e){
            return new Position( (float) (Math.floor((e.body.getPosition().x) / width) + (world.getColumns() / 2)), (float) (Math.floor(world.getRows() - (e.body.getPosition().y) / height) - (world.getRows() / 2)) + 1);
        }
        
    
}
