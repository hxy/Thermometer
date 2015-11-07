package com.example.powertest;

import android.os.Bundle;
import android.provider.Settings.Global;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private ThermometerView tView;
	private EditText editText;
	private Button button;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tView = (ThermometerView)findViewById(R.id.thermometerView);
		editText = (EditText)findViewById(R.id.edit);
		button = (Button)findViewById(R.id.btn);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tView.setTemperature(Integer.parseInt(editText.getText().toString()));
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}

}
