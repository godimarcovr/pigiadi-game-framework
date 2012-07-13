/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.LinkedList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Marco
 */
public class Game {

    Map map;
    long lastFrame;
    int fps;
    long lastFPS;
    public World world;
    int velIt = 6, posIt = 2;
    Entity e2;
    Player pl;

    public Game() {
    }

    public void start() {

        // init OpenGL here
        boolean success = Window.initialise(800, 600);
        Window.setMeterSpace(4*15, 3*15);
        Window.game2 = this;
        
        try {
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }
        //menuInitialize();//Menu initialize
        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        /***********************************************************************************************************/
        this.world = new World(new Vec2(0f, 0f), false);
        this.pl = new Player(5, 5, 0, 0);
        this.e2 = new Entity(new Vec2[]{new Vec2(-5, 0), new Vec2(0, -5), new Vec2(5, 0), new Vec2(0, 5)}, 20f, 20f);
        pl.debug = true;
        map = new Map();

        /***********************************************************************************************************/
        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            Ms.update(delta);
            TimerHandler.update(delta);
            updateFPS();
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
        Float[] bounds=Window.getBoundaries();
        GLU.gluOrtho2D(bounds[0], bounds[1], bounds[2], bounds[3]);
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
        map.draw();
        for (Entity entity : EntityCensus.ents) {
            entity.draw();
        }
        pl.draw();
        float mult = 1f;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluOrtho2D(pl.body.getPosition().x+ Window.bounds[0]
                       , pl.body.getPosition().x+ Window.bounds[1]
                       , pl.body.getPosition().y+ Window.bounds[2]
                       , pl.body.getPosition().y+ Window.bounds[3]);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
    }

    public void update(int delta) {
        String read = Kb.getChars();

        if (Kb.isPressed("S")) {
            pl.dy = -1;
        } else if (!Kb.isPressed("W")) {
            pl.dy = 0;
        }
        if (Kb.isPressed("D")) {
            pl.dx = 1;
        } else if (!Kb.isPressed("A")) {
            pl.dx = 0;
        }
        if (Kb.isPressed("A")) {
            pl.dx = -1;
        } else if (!Kb.isPressed("D")) {
            pl.dx = 0;
        }
        if (Kb.isPressed("W")) {
            pl.dy = 1;
        } else if (!Kb.isPressed("S")) {
            pl.dy = 0;
        }

        if (Kb.isPressed("K")) {
            e2.dy = -1;
        } else if (!Kb.isPressed("I")) {
            e2.dy = 0;
        }
        if (Kb.isPressed("L")) {
            e2.dx = 1;
        } else if (!Kb.isPressed("J")) {
            e2.dx = 0;
        }
        if (Kb.isPressed("J")) {
            e2.dx = -1;
        } else if (!Kb.isPressed("L")) {
            e2.dx = 0;
        }
        if (Kb.isPressed("I")) {
            e2.dy = 1;
        } else if (!Kb.isPressed("K")) {
            e2.dy = 0;
        }

          
        pl.setSpeed();
        pl.setMatrixCoordinates(map.getEntityCoordinates(pl));
        e2.setSpeed();
        world.step(delta, velIt, posIt);
    }
}
