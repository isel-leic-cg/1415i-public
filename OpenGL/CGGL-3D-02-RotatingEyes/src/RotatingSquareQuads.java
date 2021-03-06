import static javax.media.opengl.GL2.*;

import javax.media.opengl.GL2;

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
public class RotatingSquareQuads extends cggl.SceneObject {

	private float angle = 0;
	private final float cx;
	private final float cy;
	private float rotatingSpeedPerSecond;
	private final float sz;

	private final float[] color;
	private float[] colorBack = { 0, 1, 1 };
	private float[] colorSide = { 1, 0, 1 };	
	private char rotatingKeyIncrease, rotatingKeyDecrease;

	public RotatingSquareQuads(float cx, float cy, float sz, 
			float[] color,
			float rotatingSpeedPerSecond,
			char rotatingKey
			) {
		this.cx = cx;
		this.cy = cy;
		this.sz = sz;
		this.color = this.colorBack = this.colorSide = color;
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
		
		angle += rotatingSpeedPerSecond * deltaS;
	}

	@Override
	protected void drawInternal(GL2 gl) {

		gl.glEnable(GL_LIGHTING);
		gl.glEnable(GL_LIGHT0);
		gl.glEnable(GL_COLOR_MATERIAL);
		
		gl.glTranslatef(cx, cy, 0);
		gl.glRotatef(angle, 0, 0, 1);
		gl.glScalef(sz, sz, 1);
		
		
		gl.glColor3fv(color, 0);
		gl.glBegin(GL_QUADS);
		{
			gl.glNormal3f(0, 0, 1);
			gl.glVertex3f(-.5f, -.5f, 0); // Lower Left
			gl.glVertex3f(+.5f, -.5f, 0); // Lower Right
			gl.glVertex3f(+.5f, +.5f, 0); // Upper Right
			gl.glVertex3f(-.5f, +.5f, 0); // Upper left
			
			gl.glColor3fv(colorBack, 0);
			gl.glNormal3f(0, 0, -1);
			gl.glVertex3f(-.5f, -.5f, -sz/4); // Lower Left
			gl.glVertex3f(+.5f, -.5f, -sz/4); // Lower Right
			gl.glVertex3f(+.5f, +.5f, -sz/4); // Upper Right
			gl.glVertex3f(-.5f, +.5f, -sz/4); // Upper left

			gl.glColor3fv(colorSide, 0);
			gl.glNormal3f(0, 1, 0);
			gl.glVertex3f(+.5f, +.5f, 0); 
			gl.glVertex3f(+.5f, +.5f, -sz/4); 
			gl.glVertex3f(-.5f, +.5f, -sz/4); 
			gl.glVertex3f(-.5f, +.5f, 0); 
		}
		gl.glEnd();		

	}

}
