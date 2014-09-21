package cn.com.itisme.unforget.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class AcceleratedSpeedService extends Service implements
		SensorEventListener {
	private static final String TAG = "sensor";
	private SensorManager manager;
	private Sensor sensor;
	private float accelerator;
	private Vibrator vibrator;
	private int i = 0;
	private Intent intent;

	@Override
	public IBinder onBind(Intent arg0) {

		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {

		super.onCreate();
		manager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		Log.i(TAG, "oncreate");
		manager.registerListener(this, sensor, 0);
		vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			accelerator = event.values[0] * event.values[0] + event.values[1]
					* event.values[1] + event.values[2] * event.values[2];
			accelerator = (float) Math.sqrt(accelerator);
			// if(accelerator>20&&accelerator<120){
			if (accelerator < 1.5) {
				i++;
				if (i > 6) {
					Log.i(TAG, "" + accelerator + "   " + i);
					vibrator.vibrate(1000);
					intent = new Intent("cn.som.itisme.unforget.MainActivity");
					startActivity(intent);
				}
			} else {
				i = 0;
			}
			// Log.i(TAG, "" + accelerator);
		}

	}

}
