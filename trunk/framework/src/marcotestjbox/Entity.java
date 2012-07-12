



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package marcotestjbox;
	import marcotestjbox.Game;
import framework.Window;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Entity {

    public PolygonShape s;
    public Fixture fix;
    public Body myBody;
    public float dx, dy;
    boolean debug;

    public Entity(float x, float y, boolean debug) {
        /*
        this.s = new PolygonShape();
        this.s.setAsBox(4, 20f);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type=BodyType.DYNAMIC;
        bodyDef.position.set(0.0f, 0.0f);

        FixtureDef fixdef=new FixtureDef();
        fixdef.shape=this.s;
        fixdef.density=1.0f;
        fixdef.friction=0.3f;

        this.fix=Window.game2.world.createBody(bodyDef).createFixture(fixdef);*/
        this.debug = debug;
        if (debug){
        FixtureDef fd = new FixtureDef();
        PolygonShape sd = new PolygonShape();
        sd.setAsBox(5f, 5f);
        fd.shape = sd;
        fd.density = 0;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        float friction = 0f;

        fd.friction = friction;
        bd.position = new Vec2(x,y);
        bd.angle = 0f;
        myBody = Window.game2.world.createBody(bd);
        myBody.createFixture(fd);
        }
        
        
        else{
        FixtureDef fd = new FixtureDef();
        PolygonShape sd = new PolygonShape();
        sd.setAsBox(5f, 5f);
        fd.shape = sd;
        fd.density = 0;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        float friction = 0f;

        fd.friction = friction;
        bd.position = new Vec2(x,y);
        bd.angle = 0f;
        myBody = Window.game2.world.createBody(bd);
        myBody.createFixture(fd);
        }
          this.s = (PolygonShape)myBody.getFixtureList().getShape();
    }
    
    public void move(int delta){
        float x =(delta * dx) / 1000;
        float y = (delta * dy) / 1000;
        myBody.setLinearVelocity(new Vec2(x, y));
    }

    public void draw() {

        if (debug){
            Color.red.bind();
        }else{
        Color.white.bind();
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(myBody.getPosition().x, myBody.getPosition().y, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            for (int j = 0; j < s.getVertexCount(); j++) {
                //System.out.print(s.getVertex(j).x+"\n");
                 GL11.glVertex2f(s.getVertex(j).x,s.getVertex(j).y);
            }

            }
            GL11.glEnd();
            GL11.glPopMatrix();
        

    }
}