import static javax.media.opengl.GL2.*;
import javax.media.opengl.GL2;

public class Axis extends cggl.SceneObject {

	public Axis()
	{
	}

	@Override
	public void update(long deltaMs, float deltaS) 
	{
	}

	@Override
	protected void drawInternal(GL2 gl) {

		gl.glScaled(.5, .5, .5);
		
		gl.glLineWidth(1);
		gl.glBegin(GL_LINES);
		{
			gl.glColor3f(1, 0, 0);
			gl.glVertex3f(0, 0, 0);
			gl.glVertex3f(1, 0, 0);

			gl.glColor3f(0, 1, 0);
			gl.glVertex3f(0, 0, 0);
			gl.glVertex3f(0, 1, 0);

			gl.glColor3f(0, 0, 1);
			gl.glVertex3f(0, 0, 0);
			gl.glVertex3f(0, 0, 1);
		}
		gl.glEnd();
		
		
	}

}
