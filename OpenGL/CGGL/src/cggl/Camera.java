package cggl;

import geom.Vector3f;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Camera {

	public Vector3f eye;
	public Vector3f center;
	public Vector3f up;

	public Camera() {
		this(new Vector3f(0, 0, 2.41f),	// 2.41 = 1 / th(45º/2)
			 new Vector3f(0, 0, 0),		// to be equals to default OpenGL
			 new Vector3f(0, 1, 0)      // setting. in Z=0 ==> x:[-1, 1] Y: [-1, 1] 
		);
	}
	
	public Camera(Vector3f eye, Vector3f center, Vector3f up) {
		lookAt(eye, center, up);
	}	
	
	public void lookAt(Vector3f eye, Vector3f center, Vector3f up)
	{
		this.eye = eye;
		this.center = center;
		this.up = up;
	}
	
	public void lookAt( float eyeX,  float eyeY,  float eyeZ, 
						float lookX, float lookY, float lookZ,
						float upX,   float upY,   float upZ)
	{
		this.eye.set(eyeX, eyeY, eyeZ); 
		this.center.set(lookX, lookY, lookZ); 
		this.up.set(upX, upY, upZ);
	}	
	
	
	public void setup(GL2 gl) 
	{
		GLU glu = new GLU();
		 glu.gluLookAt(
				 eye.x, eye.y, eye.z,
				 center.x, center.y, center.z,
				 up.x, up.y, up.z);
	}

}
