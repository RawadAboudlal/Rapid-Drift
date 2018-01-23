package com.rawad.rapiddrift.util;

import com.rawad.rapiddrift.math.Quaternionf;
import com.rawad.rapiddrift.math.Vector3f;

/**
 * @author Rawad
 *
 */
public final class CameraUtils {
	
	private static final float MIN_PRECISION = 0.000001f;
	
	private CameraUtils() {}
	
	public static Quaternionf lookAt(Vector3f cameraPosition, Vector3f point, Vector3f forward) {
		
		Vector3f camToFocus = cameraPosition.subtract(point).normalize();
		
		float dot = camToFocus.dot(forward);
		
		if(Math.abs(dot + 1) < MIN_PRECISION) {
			return new Quaternionf(new Vector3f(0, 1, 0), (float) Math.PI);// up vector
		}
		
		if(Math.abs(dot - 1) < MIN_PRECISION) {
			return new Quaternionf();
		}
		
		Vector3f rotationAxis = forward.cross(camToFocus).normalize();
		float rotationAngle = (float) Math.acos(dot);
		
		return new Quaternionf(rotationAxis, rotationAngle);
		
	}
	
}
