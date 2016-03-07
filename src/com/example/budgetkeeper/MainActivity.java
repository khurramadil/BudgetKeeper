package com.example.budgetkeeper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,
		OnTouchListener {

	Button green_button, blue_button, red_button, yellow_button, home_button;
	Context context;
	TextView tv_newentry, tv_view, tv_graph, tv_calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;

		green_button = (Button) findViewById(R.id.green_button);
		blue_button = (Button) findViewById(R.id.blue_button);
		red_button = (Button) findViewById(R.id.red_button);
		yellow_button = (Button) findViewById(R.id.yellow_button);
		home_button = (Button) findViewById(R.id.home_button);
		tv_newentry = (TextView) findViewById(R.id.NE);
		tv_view = (TextView) findViewById(R.id.ViewR);
		tv_graph = (TextView) findViewById(R.id.GraphV);
		tv_calendar = (TextView) findViewById(R.id.CalenderV);

		green_button.setOnClickListener(this);
		blue_button.setOnClickListener(this);
		red_button.setOnClickListener(this);
		yellow_button.setOnClickListener(this);
		home_button.setOnClickListener(this);
		tv_newentry.setOnTouchListener(this);
		tv_view.setOnTouchListener(this);
		tv_graph.setOnTouchListener(this);
		tv_calendar.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.green_button:
			Intent green = new Intent(context, newentry.class);
//			green.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			green.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(green);

			break;

		case R.id.blue_button:
			Intent blue = new Intent(context, view.class);
//			blue.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			blue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(blue);

			break;

		case R.id.red_button:
			Intent red = new Intent(context, graph.class);
//			red.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			red.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(red);

			break;

		case R.id.yellow_button:
			Intent yellow = new Intent(context, calendar.class);
//			yellow.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			yellow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(yellow);

			break;

		case R.id.home_button:
			Intent home = new Intent(context, MainActivity.class);
			home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			home.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

			startActivity(home);

			break;

		default:
			break;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.NE:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				Intent green = new Intent(context, newentry.class);
	//			green.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	//			green.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(green);
				
			}

			break;

		case R.id.ViewR:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				Intent blue = new Intent(context, view.class);
	//			blue.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	//			blue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(blue);
	
			}

			break;

		case R.id.GraphV:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				Intent red = new Intent(context, graph.class);
	//			red.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	//			red.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(red);

			}

			break;

		case R.id.CalenderV:
			if (event.getAction() == MotionEvent.ACTION_UP) {
				Intent yellow = new Intent(context, calendar.class);
	//			yellow.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	//			yellow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(yellow);
			}

			break;
		
		default:
			break;
		}
		return true;
	}

}
