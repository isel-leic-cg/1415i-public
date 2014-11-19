import static javax.media.opengl.GL2.*;
import geom.Vector3f;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

import cggl.World;

/**
 * Quadrado que roda (angle) em torno de um ponto C a uma velocidade
 * rotatingSpeedPerSecond.
 * 
 * 
 * NOTA: Para rodar em torno de um ponto
 * 
 * P' = T(Cx, Cy) * R(angle) * T(-Cx, -Cy) * P
 */
public class Wheel extends cggl.SceneObject {

	private float angle = 0;
	public final Vector3f pos;
	private float rotatingSpeedPerSecond;
	private final float sz;

	private final float[] color;
	private char rotatingKeyIncrease, rotatingKeyDecrease;

	public Wheel(float cx, float cy, float cz, float size, 
			float[] color,
			float rotatingSpeedPerSecond,
			char rotatingKey
			) {
		this.pos = new Vector3f(cx, cy, cz);
		this.sz = size;
		this.color = color;
		this.rotatingSpeedPerSecond = rotatingSpeedPerSecond;
		this.rotatingKeyIncrease = Character.toLowerCase(rotatingKey);
		this.rotatingKeyDecrease = Character.toUpperCase(rotatingKey);
	}

	public void update(long currentMs, long deltaMs, float deltaS) 
	{
		if(World.Instance.Input.isKeyPressed(rotatingKeyIncrease)) {
			this.rotatingSpeedPerSecond += 5;
		}
		if(World.Instance.Input.isKeyPressed(rotatingKeyDecrease)) {
			this.rotatingSpeedPerSecond -= 5;
		}
		if(rotatingSpeedPerSecond >  1000) rotatingSpeedPerSecond =  1000;
		if(rotatingSpeedPerSecond < -1000) rotatingSpeedPerSecond = -1000; 
		
		if(World.Instance.Input.isKeyPressed('a')) { this.pos.addScaled(Vector3f.LEFT,  deltaS); }		
		if(World.Instance.Input.isKeyPressed('s')) { this.pos.addScaled(Vector3f.DOWN,  deltaS); }	
		if(World.Instance.Input.isKeyPressed('d')) { this.pos.addScaled(Vector3f.RIGHT, deltaS); }	
		if(World.Instance.Input.isKeyPressed('w')) { this.pos.addScaled(Vector3f.UP,    deltaS); }
		
		angle += rotatingSpeedPerSecond * deltaS;
	}

	@Override
	protected void drawInternal(GL2 gl) {
		GLUT glut = new GLUT();

		
		gl.glTranslatef(pos.x, pos.y, pos.z);
		gl.glRotatef(angle, 0, 0, 1);
		//gl.glRotatef(angle, 0, 1, 0);
		gl.glScalef(sz, sz, sz);

		gl.glTranslatef(0, 0, -.5f);
		gl.glColor3fv(color, 0);

		glut.glutWireTorus(sz/4, sz, 10, 20);

	}

}
