package com.example.budgetkeeper;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;

public class calendar extends Activity implements OnClickListener {
	
	Button green_button, blue_button, red_button, yellow_button, home_button;
	Context context;
	CalendarView calendarView;
	Calendar calendar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        
        context = this;
        
        green_button = (Button) findViewById(R.id.green_button);
        blue_button = (Button) findViewById(R.id.blue_button);
        red_button = (Button) findViewById(R.id.red_button);
        yellow_button = (Button) findViewById(R.id.yellow_button);
        home_button = (Button) findViewById(R.id.home_button);
        
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnClickListener(this);
        
        green_button.setOnClickListener(this);
        blue_button.setOnClickListener(this);
        red_button.setOnClickListener(this);
        yellow_button.setOnClickListener(this);
        home_button.setOnClickListener(this);
        
        calendarView.setDate(Calendar.getInstance().getTimeInMillis(), true, true);
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
			green.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			green.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(green);
			
			break;
		
		case R.id.blue_button:
			Intent blue = new Intent(context, view.class);
			blue.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			blue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(blue);
			
			break;	

		case R.id.red_button:
			Intent red = new Intent(context, graph.class);
			red.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			red.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(red);
			
			break;
			
		case R.id.yellow_button:
			Intent yellow = new Intent(context, calendar.class);
			yellow.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			yellow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(yellow);
			
			break;
			
		case R.id.home_button:
			Intent home = new Intent(context, MainActivity.class);
			home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(home);
			
			break;	
			
		default:
			break;
		}
		
	}
    
}
