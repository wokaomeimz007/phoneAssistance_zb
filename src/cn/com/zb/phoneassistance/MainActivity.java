package cn.com.zb.phoneassistance;

import java.util.List;

import cn.com.zb.phoneassistance.utils.DisplayDetails;
import cn.com.zb.phoneassistance.utils.GetDevicesDetails;
import cn.com.zb.phoneassistance.utils.SensorType;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String TAG = "Henry";

	// private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){
	//
	// @Override
	// public void onReceive(Context context, Intent intent) {
	// String action = intent.getAction();
	// if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
	// int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
	// }
	// }
	//
	// };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GetDevicesDetails mGetDevicesDetails = new GetDevicesDetails();

		mGetDevicesDetails.getCameraPixes(Camera.CameraInfo.CAMERA_FACING_BACK);
		mGetDevicesDetails
				.getCameraPixes(Camera.CameraInfo.CAMERA_FACING_FRONT);

		Log.i(TAG,
				"cameraback = "
						+ mGetDevicesDetails
								.getCameraPixes(Camera.CameraInfo.CAMERA_FACING_BACK));
		Log.i(TAG,
				"camerafront = "
						+ mGetDevicesDetails
								.getCameraPixes(Camera.CameraInfo.CAMERA_FACING_FRONT));

		DisplayDetails mDisplayDetails = mGetDevicesDetails
				.getDisplayDetails(MainActivity.this);
		Log.i(TAG, "mDisplayDetails="+mDisplayDetails.toString());
		SensorType mType = mGetDevicesDetails.getSensorType(this);
		Log.i(TAG, "SensorType="+mType.toString());
		Log.i(TAG, "BTMODE="+mGetDevicesDetails.getBt(this));
		Log.i(TAG, "WifiMode"+mGetDevicesDetails.getWifiModle(this));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	// private int test() {
	// //Camera camera = Camera.open();
	// int cameraPixes=0;
	// Camera camera =
	// openCameraGingerbread(Camera.CameraInfo.CAMERA_FACING_FRONT);
	// if(camera!=null){
	// camera.release();
	// Parameters params = camera.getParameters();
	// List sizes = params.getSupportedPictureSizes();
	// Camera.Size result = null;
	// for (int i = 0; i < sizes.size(); i++) {
	// result = (Size) sizes.get(i);
	// //Log.i("MainActivity", "Supported Size. Width: " + result.width
	// //+ "height : " + result.height);
	// if(result.width * result.height>cameraPixes){
	// cameraPixes = result.width * result.height;
	// Log.i(TAG, "cameraPixes="+cameraPixes);
	// }
	// }
	// }
	//
	// return cameraPixes;
	// }

	// private Camera openBackgroundFacingCameraGingerbread() {
	// int cameraCount = 0;
	// Camera cam = null;
	// Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
	// cameraCount = Camera.getNumberOfCameras();
	// for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
	// Camera.getCameraInfo( camIdx, cameraInfo );
	// if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT ) {
	// try {
	//
	// cam = Camera.open( camIdx );
	// } catch (RuntimeException e) {
	// Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
	// }
	// }
	// }
	// return cam;
	// }

	// private Camera.Size getBestPreviewSize(int width, int height,
	// Camera.Parameters parameters) {
	// Camera.Size result = null;
	//
	// for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
	// if (size.width <= width && size.height <= height) {
	// if (result == null) {
	// result = size;
	// } else {
	// int resultArea = result.width * result.height;
	// int newArea = size.width * size.height;
	//
	// if (newArea > resultArea) {
	// result = size;
	// }
	// }
	// }
	// }
	//
	// return (result);
	// }

}
