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
public class RotatingSquare extends cggl.SceneObject {

	private float angle = 0;
	private final float cx;
	private final float cy;
	private float rotatingSpeedPerSecond;
	private final float sz;

	private final float[] color;
	private char rotatingKeyIncrease, rotatingKeyDecrease;

	public RotatingSquare(float cx, float cy, float sz, 
			float[] color,
			float rotatingSpeedPerSecond,
			char rotatingKey
			) {
		this.cx = cx;
		this.cy = cy;
		this.sz = sz;
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
		
		angle += rotatingSpeedPerSecond * deltaS;
	}

	@Override
	protected void drawInternal(GL2 gl) {

		gl.glTranslatef(cx, cy, 0);
		gl.glRotatef(angle, 0, 0, 1);

		gl.glColor3fv(color, 0);
		gl.glBegin(GL_POLYGON);
		{
			gl.glVertex3f(-sz / 2, -sz / 2, 0); // Lower Left
			gl.glVertex3f(+sz / 2, -sz / 2, 0); // Lower Right
			gl.glVertex3f(+sz / 2, +sz / 2, 0); // Upper Right
			gl.glVertex3f(-sz / 2, +sz / 2, 0); // Upper left
		}
		gl.glEnd();

		gl.glPointSize(6);
		gl.glColor3f(0, 0, 0);
		gl.glBegin(GL_POINTS);
		{
			gl.glVertex3f(0, 0, 0);
		}
		gl.glEnd();
	}

}
