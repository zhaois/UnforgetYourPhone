package cn.com.itisme.unforget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent = new Intent(
				"cn.com.itisme.unforget.service.AcceleratedSpeedService");
		startService(intent);
	}

}
