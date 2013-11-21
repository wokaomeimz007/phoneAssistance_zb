package cn.com.zb.phoneassistance.utils;

public class SensorType {
	public boolean mAcceleration ;
	public boolean mDirection;
	public boolean mLight;
	public boolean mElectronicCompass;
	public boolean mDistance;
	public boolean mGyro;
	public boolean mTemperature;
	public boolean mPressure;
	public boolean mGravity;
	
	@Override
	public String toString() {
			return "mAcceleration="+mAcceleration+","+
					"mDirection="+mDirection+","+
					"mLight="+mLight+","+
					"mElectronicCompass="+mElectronicCompass+","+
					"mDistance="+mDistance+","+
					"mGyro="+mGyro+","+
					"mTemperature="+mTemperature+","+
					"mPressure="+mPressure+","+
					"mGravity="+mGravity;

	}
}
