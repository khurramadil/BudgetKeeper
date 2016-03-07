package com.example.budgetkeeper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class graph extends Activity implements OnClickListener,
		OnItemSelectedListener {

	String db_view_graph_year;
	Spinner spinner_graph_year;
	Calendar calendar;
	int y;

	Button green_button, blue_button, red_button, yellow_button, home_button;
	Context context;
	int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12;
	int[] temp = new int[12];

	DatabaseHelper myDb2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph);

		context = this;

		green_button = (Button) findViewById(R.id.green_button);
		blue_button = (Button) findViewById(R.id.blue_button);
		red_button = (Button) findViewById(R.id.red_button);
		yellow_button = (Button) findViewById(R.id.yellow_button);
		home_button = (Button) findViewById(R.id.home_button);

		green_button.setOnClickListener(this);
		blue_button.setOnClickListener(this);
		red_button.setOnClickListener(this);
		yellow_button.setOnClickListener(this);
		home_button.setOnClickListener(this);

		myDb2 = new DatabaseHelper(this);

		a1 = 25000;
		a2 = 6000;
		a3 = 7000;
		a4 = 9000;
		a5 = 8000;
		a6 = 6000;
		a7 = 5000;
		a8 = 1000;
		a9 = 9000;
		a10 = 5000;
		a11 = 600;
		a12 = 599;

		spinner_graph_year = (Spinner) findViewById(R.id.spinner_graph_year);

		spinner_graph_year.setOnItemSelectedListener(this);

		List<String> year = new ArrayList<String>();
		year.add("2015");
		year.add("2016");
		year.add("2017");
		year.add("2018");
		year.add("2019");
		year.add("2020");
		year.add("2021");
		year.add("2022");
		year.add("2023");
		year.add("2024");
		year.add("2025");
		year.add("2026");

		ArrayAdapter<String> yearDataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, year);

		yearDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_graph_year.setAdapter(yearDataAdapter);

		calendar = Calendar.getInstance();

		y = calendar.get(Calendar.YEAR);

		y = (y - 2015);

		spinner_graph_year.setSelection(y, true);

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

	public void barGraph(View view) {

		for (int i = 0; i < 12; i++) {
			String m = "" + (i + 1);
			Cursor res_graph = myDb2.searchData(m, db_view_graph_year);

						
			while (res_graph.moveToNext()) {
				res_graph.getString(6);
				temp[i] = temp[i] + Integer.parseInt(res_graph.getString(6));

			}

		}

		barGraph bg = new barGraph(temp);
		Intent lineIntent = bg.getIntent(this);
		startActivity(lineIntent);
		for(int j = 0; j<temp.length; j++){
			temp[j] = 0;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		if (parent == spinner_graph_year) {
			spinner_graph_year.setSelection(position);
			db_view_graph_year = (String) spinner_graph_year.getSelectedItem();
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
