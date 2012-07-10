/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import framework.Ms.*;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.jbox2d.collision.shapes.PolygonShape;

/**
 *
 * @author Marco
 */
public class Tester {

  
    long lastFrame;
    int fps;
    long lastFPS;
    Button butTest;
    Button butTest2;
    TextBox textTest;
    Menu menuTest;
    boolean start;
    Entity e, e2;

    public Tester() {
    }

    public void start() {
        
        
        // init OpenGL here
        boolean success = Window.initialise(800, 600);
        try {
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }
        e = new Entity(new Position(50, 60));
        e2 = new Entity(new Position(100,200),new Vector[]{new Vector(200, 40), new Vector(200, 200), new Vector(40, 40), new Vector(100, 40)});
        e.dx = 0;
        e.dy = 0;
        menuInitialize();//Menu initialize
        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            Ms.update(delta);
            TimerHandler.update(delta);
            updateFPS();

            update(delta);
            update(delta);
            renderGL();

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }



        Display.destroy();
    }

    private void initGL() {

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluOrtho2D(0, Window.w, Window.h, 0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    private int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void renderGL() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        if (!start){
        this.menuTest.draw();
        }else{
            e.draw();
            e2.draw();

        }
    }

    public void update(int delta) {
        String read = Kb.getChars();
        if(start){
            
        
           if (!e.collides(e2)){
               if (Kb.isPressed("S")){
                   e.dy = 100;
                   if (e.collidesNext(e2, delta)){
                       e.dy = 0;
                   }
               }else {
                   e.dy = 0;
               }             
               if (Kb.isPressed("D")){
                   e.dx = 100;
                   if (e.collidesNext(e2, delta)){
                       e.dx = 0;
                   }
               }else {
                   e.dx = 0;
               }
               e.move(delta);
              }
        }
        menuTest.update(read);

    }
    
    public void menuInitialize(){
       
        int f1 = FontHandler.createFont("Times New Roman", Font.PLAIN, 15);
        this.textTest = new TextBox(new Box(200, 100, 500, 100), "", f1, Color.red, Color.cyan, Color.lightGray);
        this.butTest = new Button(new Box(300, 400, 0, 200), "Ok", f1, Color.red, Color.cyan, Color.lightGray, Color.green,Color.gray){
            @Override
             public void run(){
                start = true;
             }
         };
        this.butTest2 = new Button(new Box(300, 400, 0, 200), "Exit", f1, Color.red, Color.cyan, Color.lightGray, Color.green,Color.gray){
            @Override
             public void run(){
                System.exit(0);
             }
         };
 
        this.textTest.visible = true;
        this.butTest.visible = true;
        this.butTest2.visible = true;

        this.menuTest=new Menu(new Box(50,50,450,450));
        this.menuTest.addComp(butTest);
        this.menuTest.addComp(textTest);
        this.menuTest.addComp(butTest2);
        this.menuTest.verticalCompile(0.5f,0.5f);
    }
}
