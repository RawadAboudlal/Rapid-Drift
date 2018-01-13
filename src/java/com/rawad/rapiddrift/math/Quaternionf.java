package com.rawad.rapiddrift.math;

public class Quaternionf {
	
	/** For converting a 3D vector to a quaternion. */
//	private static final int RIGHT_ANGLE = 90;
	
	public final float x;
	public final float y;
	public final float z;
	public final float w;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public Quaternionf(float x, float y, float z, float w) {
		super();
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		
	}
	
	/**
	 * 
	 * @param axis
	 * @param angle Angle of rotation in degrees. Counter-clockwise looking in the direction of the vector.
	 */
	public Quaternionf(Vector3f axis, float angle) {
		super();
		
		double thetaInRads = Math.toRadians(angle);
		
		float c = (float) Math.cos(thetaInRads / 2d);
		float s = (float) Math.sin(thetaInRads / 2d);
		
		this.w = c;
		this.x = axis.x * s;
		this.y = axis.y * s;
		this.z = axis.z * s;
		
	}
	
	/**
	 * Creates a pure quaternion, with the real part, w, equal to 0;
	 * 
	 * @param vec
	 */
	public Quaternionf(Vector3f vec) {
		super();
		
		this.w = 0;
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		
	}
	
	public Quaternionf() {
		this(0, 0, 1f, 0);// Important default value; which is 1f is irrelevant.
	}
	
	/**
	 * Rotates the given {@code vec} by this {@code Quaternionf} using the formula: p' = q * p * q^-1
	 * 
	 * @param vec
	 * @return
	 */
	public Vector3f rotate(Vector3f vec) {
		
		Quaternionf p = new Quaternionf(vec);
		Quaternionf qConjugate = this.conjugate();
		
		Quaternionf result = this.multiply(p).multiply(qConjugate);// p' = q * p * q^-1 (^-1 = conjugate).
		return new Vector3f(result.x, result.y, result.z);
		// q = quaternion, p = original vector, p' = rotated vector.
		
	}
	
	public Quaternionf multiply(Quaternionf p) {
		
		float w = p.w * this.w - p.x * this.x - p.y * this.y - p.z * this.z;
		float x = p.w * this.x + p.x * this.w - p.y * this.z + p.z * this.y;
		float y = p.w * this.y + p.x * this.z + p.y * this.w - p.z * this.x;
		float z = p.w * this.z - p.x * this.y + p.y * this.x + p.z * this.w;
		
		return new Quaternionf(x, y, z, w);
		
	}
	
	public Quaternionf conjugate() {
		return new Quaternionf(this.w, -this.x, -this.y, -this.z);
	}
	
	public Vector4f toRotationAxisAngle() {
		
		double theta = this.getRotationAngle();
		
		float s = (float) Math.sin(theta / 2d);
		
		if(s == 0f) s = 1f;// Prevents divide by zero; x,y,z are all zero in this case anyways.
		
		return new Vector4f(this.x / s, this.y / s, this.z / s, (float) Math.toDegrees(theta));
		
	}
	
	public Vector3f toVector3f() {
		
//		float s = (float) Math.sin(this.getRotationAngle() / 2d);
		// acos(w) = theta / 2 since w = cos(theta / 2), avoid inaccuracy this way.
		
//		return new Vector3f(this.x / s, this.y / s, this.z / s);
		
		return new Vector3f(this.x, this.y, this.z);
		
	}
	
	public Quaternionf normalize() {
		
		float length = length();
		
		return new Quaternionf(this.w / length, this.x / length, this.y / length, this.z / length);
		
	}
	
	private double getRotationAngle() {
		
		float w = this.w;
		
		if(Math.abs(w) > 1) {
			w /= this.length();// Normalizes w. Keeps w in domain of acos (range of cos). Occurs due to precision error.
		}
		
		return 2d * Math.acos(w);
		
	}
	
	public float lengthSquared() {
		return w * w + x * x + y * y + z * z;
	}
	
	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}
	
	@Override
	public Quaternionf clone() {
		return new Quaternionf(w, x, y, z);
	}
	
	@Override
	public String toString() {
		return "[" + w + ", " + x + ", " + y + ", " + z + "]";
	}
	
}
