/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/
package cggl;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

public class TextWriter {

	private GLUT glut;

	public TextWriter() {
		glut = new GLUT();
	}

	public void writeTextImediate(GL2 gl, double x, double y, String text) {
		writeTextImediate(gl, x, y, text, GLUT.BITMAP_HELVETICA_12);
	}

	public void writeTextImediate(GL2 gl, double x, double y, String text, int font) {
		beginWriteText(gl);
		writeText(gl, x, y, text, font);
		endWriteText(gl);
	}	
	
	public void beginWriteText(GL2 gl) 
	{
		// Change to PROJECTION matrix so that we can define an ortho 
		// projection, and save the previous matrix to restore on end
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glOrtho(0, 1, 0, 1, -1, 1000);
		gl.glScalef(1, -1, 1);     // Invert YY so that it grows down
		gl.glTranslatef(0, -1, 0); // change origin to top left 

		// Change to MODELVIEW matrix to write text in 2D,
		// and save the previous matrix to restore on end
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		
		// Disable lighting (saving attributes to restore latter)
		gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
		gl.glDisable(GL2.GL_LIGHTING);
	}

	public void writeText(GL2 gl, double x, double y, String text) {
		writeText(gl, x, y, text, GLUT.BITMAP_TIMES_ROMAN_10);
	}

	public void writeText(GL2 gl, double x, double y, String text, int font) {
	    gl.glRasterPos2d(x, y);
		glut.glutBitmapString(font, text);
	}

	public  void endWriteText(GL2 gl) 
	{
		// Restore saved attributes 
		gl.glPopAttrib();

		// Restore PROJECTION matrix
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPopMatrix();
		// Restore MODELVIEW matrix
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPopMatrix();
	}

}
