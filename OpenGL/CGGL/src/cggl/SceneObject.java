package cggl;

import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT_AND_DIFFUSE;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public abstract class SceneObject {

	public void initGL(GL2 gl) { }
	public abstract void update(long currentMs, long deltaMs, float deltaS);
	protected void drawInternal(GL2 gl) { };
	protected void drawInternal(GL2 gl, GLU glu, GLUT glut) { 
		drawInternal(gl);
	};

	public void draw(GL2 gl, GLU glu, GLUT glut) {
		gl.glPushMatrix();
		drawInternal(gl, glu, glut);
		gl.glPopMatrix();
	}
	protected void setColor(GL2 gl, float[] color) 
	{
		gl.glColor3fv(color, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT,  new float[] { 0.11f, 0.06f, 0.11f, 1.0f }, 0);
		//gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE,  new float[] { 0.43f, 0.47f, 0.54f, 1.0f }, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, new float[] { 0.33f, 0.33f, 0.52f, 1.0f }, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, new float[] { 0.00f, 0.00f, 0.00f, 0.0f }, 0);
		gl.glMaterialf( GL2.GL_FRONT, GL2.GL_SHININESS, 10);
		
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, color, 0);

	}
	
}
