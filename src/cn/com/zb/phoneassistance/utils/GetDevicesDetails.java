package cn.com.zb.phoneassistance.utils;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

@SuppressLint("InlinedApi")
public class GetDevicesDetails {

	private static final String TAG = "Henry";

//	@SuppressWarnings("deprecation")
//	public int getWidthPixels(Context context) {
//		WindowManager mWindowManager = (WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE);
//		return mWindowManager.getDefaultDisplay().getWidth();
//	}
//
//	@SuppressWarnings("deprecation")
//	public int getHeightPixels(Context context) {
//		WindowManager mWindowManager = (WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE);
//		return mWindowManager.getDefaultDisplay().getHeight();
//
//	}
//
//	public float getDiagonalLength(Activity activity) {
//		DisplayMetrics dm = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		double diagonalPiexels = Math.sqrt(Math.pow(dm.widthPixels, 2)
//				+ Math.pow(dm.heightPixels, 2));
//		return (float) (diagonalPiexels / (160 * dm.density));
//	}
//
//	public int getWidthPixels(Activity activity) {
//		DisplayMetrics dm = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		return dm.widthPixels;
//	}
//
//	public int getHeightPixels(Activity activity) {
//		DisplayMetrics dm = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		return dm.heightPixels;
//	}

	public DisplayDetails getDisplayDetails(Activity activity) {
		DisplayDetails displayDetails = new DisplayDetails();
		WindowManager w = activity.getWindowManager();
		Display d = w.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		d.getMetrics(metrics);
		// since SDK_INT = 1;
		displayDetails.mWidth=metrics.widthPixels;
		displayDetails.mHeight  = metrics.heightPixels;
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
			try {
				
				displayDetails.mWidth = (Integer) Display.class.getMethod("getRawWidth")
						.invoke(d);
				displayDetails.mHeight = (Integer) Display.class.getMethod("getRawHeight")
						.invoke(d);
			} catch (Exception ignored) {
			}
		}

		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 17){
			try {
				Point realSize = new Point();
				Display.class.getMethod("getRealSize", Point.class).invoke(d,
						realSize);
				displayDetails.mWidth = realSize.x;
				displayDetails.mHeight = realSize.y;
			} catch (Exception ignored) {
			}
		}
		
		//double diagonalPiexels = Math.sqrt(Math.pow(displayDetails.mWidth, 2)
		//+ Math.pow(displayDetails.mHeight, 2));
		//displayDetails.mDiagonalLength = (float)(diagonalPiexels / (160 * metrics.density));
		
		
		return displayDetails;

	}

	/*
	 * Camera.CameraInfo.CAMERA_FACING_FRONT Camera.CameraInfo.
	 */
	public int getCameraPixes(int type) {
		// Camera camera = Camera.open();
		int cameraPixes = 0;
		Camera camera = openCameraGingerbread(type);
		if (camera != null) {

			Parameters params = camera.getParameters();
			List<Size> sizes = params.getSupportedPictureSizes();
			Camera.Size result = null;
			for (int i = 0; i < sizes.size(); i++) {
				result = (Size) sizes.get(i);

				if (result.width * result.height > cameraPixes) {
					cameraPixes = result.width * result.height;
					// Log.i(TAG, "cameraPixes=" + cameraPixes);
				}
			}
			camera.release();
		}

		return cameraPixes;
	}

	private Camera openCameraGingerbread(int type) {
		int cameraCount = 0;
		Camera cam = null;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		cameraCount = Camera.getNumberOfCameras();
		for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (cameraInfo.facing == type) {
				try {

					cam = Camera.open(camIdx);
				} catch (RuntimeException e) {
					Log.e(TAG,
							"Camera failed to open: " + e.getLocalizedMessage());
				}
			}
		}
		return cam;
	}

	public String getWifiMacAdress(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		result = wifiInfo.getMacAddress();
		Log.i(TAG, "macAdd:" + result);
		return result;
	}

	public boolean getWifiModle(Context context) {
		return context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_WIFI);

	}

	public boolean getBt(Context context) {
		return context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH);

	}
	public SensorType getSensorType(Context context){
		SensorType mSensorType = new SensorType();
		SensorManager mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		Sensor  mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if(mSensor!=null)mSensorType.mAcceleration = true;
		
		  mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		if(mSensor!=null)mSensorType.mGravity = true;
		
		mSensor  = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		if(mSensor!=null)mSensorType.mLight =true;
		
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		if(mSensor!=null)mSensorType.mPressure=true;
		
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		if(mSensor!=null)mSensorType.mGyro=true;
		
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		if(mSensor!=null)mSensorType.mTemperature=true;
		

		
		
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		if(mSensor!=null)mSensorType.mDistance=true;
		
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		if(mSensor!=null)mSensorType.mElectronicCompass=true;
		
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		if(mSensor!=null)mSensorType.mDirection=true;
		
		return mSensorType;
		
	}

}
