import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_COLOR_MATERIAL;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;

import javax.media.opengl.GL2;

import geom.Vector3f;


public class RotatingEyesApp extends cggl.World {

	private Wheel wheel;
	private boolean lockCameraToWheel = false;

	public static void main(String[] args) 
	{
		new RotatingEyesApp().start("CGGL: Rotating Eyes");
	}

	@Override 
	public void createScene() 
	{
		// Create world
		float[] eyesColor = new float[] { 1f, 1f, 0f }; 
		wheel = new Wheel(
				.4f, .5f, 0, 
				.5f, 
				eyesColor, 
				-180, 
				'e');
		add(wheel);

		add(new Axis());
		
		Camera.lookAt(
				new Vector3f(1, 0.5f, 1.41f),	
				new Vector3f(0, 0f, 0),		
				new Vector3f(0, 1, 0)      
		);
	}
	
	@Override
	protected void initScene(GL2 gl) {
		super.initScene(gl);
		gl.glEnable(GL_LIGHTING);
		gl.glEnable(GL_LIGHT0);
		gl.glEnable(GL_COLOR_MATERIAL);
	}
	
	@Override
	protected void updateScene(long currentMs, long deltaMs, float deltaS) {
		super.updateScene(currentMs, deltaMs, deltaS);
		
		// Camera 2 - Look from x
		if(Input.isKeyPressed('1', false)) 
		{
			lockCameraToWheel = false; 
			Camera.lookAt(
					new Vector3f(5, 0, 0),	
					new Vector3f(0, 0f, 0),		
					new Vector3f(0, 1, 0)
			);
		}

		// Camera 2 - Look from middle of xx and zz
		if(Input.isKeyPressed('2', false)) 
		{
			lockCameraToWheel = false; 
			Camera.lookAt(
					new Vector3f(1, 0.5f, 1.41f),	
					new Vector3f(0, 0f, 0),		
					new Vector3f(0, 1, 0)      
			);
		}		

		// Camera 3 - Look from top
		if(Input.isKeyPressed('3', false)) 
		{
			lockCameraToWheel = false; 
			Camera.lookAt(
					new Vector3f(0, 5,  0),	
					new Vector3f(0, 0,  1),		
					new Vector3f(0, 0, -1)  	// up vector ("parte de cima da camera")    
			);
		}		
		
		// Camera 4 - Look to moving actor
		if(Input.isKeyPressed('4')) 
		{
			lockCameraToWheel  = true; 
		}
		
		if(lockCameraToWheel) {
			Vector3f eye = wheel.pos.clone();
			Vector3f pos = eye.clone().add(Vector3f.BACK);
			
			Camera.lookAt(
					pos,	
					eye,		
					new Vector3f(0, 1, 0)    
			);

		}
	}


}
