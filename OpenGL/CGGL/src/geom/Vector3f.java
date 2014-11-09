/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/
package geom;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class Vector3f {
    public static final Vector3f LEFT  = new Vector3f(-1,  0,  0);
    public static final Vector3f RIGHT = new Vector3f( 1,  0,  0);
    public static final Vector3f UP    = new Vector3f( 0,  1,  0);
    public static final Vector3f DOWN  = new Vector3f( 0, -1,  0);
    public static final Vector3f FRONT = new Vector3f( 0,  0, -1);
    public static final Vector3f BACK  = new Vector3f( 0,  0,  1);

    public float x, y, z;

	public Vector3f() {
		this(0, 0, 0);
	}
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector3f point) {
		this(point.x, point.y, point.z);
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(Vector3f v) {
		set(v.x, v.y, v.z);
		
	}
	
	public Vector3f(Vector3f from, Vector3f to) {
		this(to.x - from.x, to.y - from.y, to.z - from.z);
	}
	
	public float magnitude() {
		return (float)Math.sqrt( 
				(this.x) * (this.x) +
				(this.y) * (this.y) +
				(this.z) * (this.z) 
		);
	}	
	
	public Vector3f normalize() {
		float magnitude = this.magnitude();
		this.x /= magnitude;
		this.y /= magnitude;
		this.z /= magnitude;
		return this;
	}
	
	public Vector3f scale(double factor) {
		this.x *= factor;
		this.y *= factor;
		this.z *= factor;
		return this;
	}
	
	public Vector3f add(Vector3f v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}	
	
	public Vector3f addScaled(Vector3f v, double scale) {
		this.x += v.x * scale;
		this.y += v.y * scale;
		this.z += v.z * scale;
		return this;
		
	}	
	
	
	public Vector3f sub(Vector3f v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}		
	
	public float dot(Vector3f v) {
		return this.x * v.x + 
			   this.y * v.y + 
			   this.z * v.z;
	}
	
	public Vector3f cross(Vector3f v) {
        float resx = y * v.z - z * v.y;
        float resy = z * v.x - x * v.z;
        float resz = x * v.y - y * v.x;
        x = resx;
        y = resy;
        z = resz;
        return this;
	}

	
	public Vector3f inverse() {
		return this.scale(-1);
	}
	
	
	
	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        result = prime * result + Float.floatToIntBits(z);
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vector3f))
            return false;
        Vector3f other = (Vector3f) obj;
        return this.x == other.x && 
               this.y == other.y &&
               this.z == other.z;
    }
    @Override
	public String toString() {
		DecimalFormat decim = new DecimalFormat("0.00");
		return MessageFormat.format("({0}, {1}, {2})", decim.format(x), decim.format(y), decim.format(z));
	}
	
	public Vector3f clone() {
		return new Vector3f(this);
	}
	
	/**
	 * Distance = srqt((x2-x1)^2 + (y2-y1)^2 + (z2-z1)^2)
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static float distance(Vector3f first, Vector3f second) {
		return (float)Math.sqrt( 
				(second.x - first.x) * (second.x - first.x) +
				(second.y - first.y) * (second.y - first.y) +
				(second.z - first.z) * (second.z - first.z) 
		);
	}
	
	/**
	 * R = 2*(V dot N)*N - V
	 * 
	 * @param dir
	 * @param normal
	 * @return
	 */
	public static Vector3f reflect(Vector3f v, Vector3f n) {
		Vector3f r = n.clone();
		r = r.scale(v.dot(n)).scale(2).sub(v);
		return r;
		
	}
	
	public Vector3f rotateYY(double angle) {
		double xx = Math.cos(angle)*x - Math.sin(angle)*z;
		double zz = Math.cos(angle)*z + Math.sin(angle)*x;
		x = (float) xx;
		z = (float) zz;
		return this;
	}

	public Vector3f rotateZZ(double angle) {
		double xx = Math.cos(angle)*x - Math.sin(angle)*y;
		double yy = Math.cos(angle)*y + Math.sin(angle)*x;
		x = (float) xx;
		y = (float) yy;
		return this;
	}
	public static double radToGrad(float rad) {
		return rad * 360 / Math.PI;
	}	
	
}
