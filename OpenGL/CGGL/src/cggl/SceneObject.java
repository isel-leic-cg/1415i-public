package cggl;

import javax.media.opengl.GL2;

public abstract class SceneObject {

	public void initGL(GL2 gl) { }
	public abstract void update(long deltaMs, float deltaS);
	protected abstract void drawInternal(GL2 gl);

	public void draw(GL2 gl) {
		gl.glPushMatrix();
		drawInternal(gl);
		gl.glPopMatrix();
	}
	
}
