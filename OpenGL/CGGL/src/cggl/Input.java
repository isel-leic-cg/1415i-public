package cggl;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Input implements KeyListener {

	void configure(Canvas canvas) {

		canvas.addKeyListener(this);
		canvas.requestFocus();
	}
	
	public boolean isKeyPressed(char key) 
	{	
		return isKeyPressed(key, false);
	}

	public boolean isKeyPressed(char key, boolean releaseKey) 
	{	
		if(key < 0 || key >= keysPressed.length) return false;

		boolean pressed = keysPressed[key];
		if(releaseKey) keysPressed[key] = false;
		return pressed;
	}
	
	private boolean[] keysPressed = new boolean[256];

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyChar();
		if(code >= 0 && code < keysPressed.length) 
			keysPressed[code] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyChar();
		if(code >= 0 && code < keysPressed.length) 
			keysPressed[code] = false;
	}


	@Override
	public void keyTyped(KeyEvent e) {	}


}
