package com.example.budgetkeeper;

import java.util.Calendar;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class newentry extends Activity implements OnClickListener {

	DatabaseHelper myDb;

	Button green_button, blue_button, red_button, yellow_button, home_button,
			reset, save;
	Context context;
	EditText title, amount;
	RadioButton r_clothing, r_food, r_bills, r_other;
	RadioGroup r_catagories;
	String radio = "", db_day, db_month, db_year;

	DatePicker datePicker;
	Calendar calendar;
	TextView txt_date, category_error;
	int d, m, y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newentry);

		myDb = new DatabaseHelper(this);

		context = this;

		green_button = (Button) findViewById(R.id.green_button);
		blue_button = (Button) findViewById(R.id.blue_button);
		red_button = (Button) findViewById(R.id.red_button);
		yellow_button = (Button) findViewById(R.id.yellow_button);
		home_button = (Button) findViewById(R.id.home_button);
		r_catagories = (RadioGroup) findViewById(R.id.r_categories);

		title = (EditText) findViewById(R.id.title);

		amount = (EditText) findViewById(R.id.amount);
		r_clothing = (RadioButton) findViewById(R.id.r_clothing);
		r_bills = (RadioButton) findViewById(R.id.r_bills);
		r_food = (RadioButton) findViewById(R.id.r_food);
		r_other = (RadioButton) findViewById(R.id.r_other);
		save = (Button) findViewById(R.id.save);
		reset = (Button) findViewById(R.id.reset);

		green_button.setOnClickListener(this);
		blue_button.setOnClickListener(this);
		red_button.setOnClickListener(this);
		yellow_button.setOnClickListener(this);
		home_button.setOnClickListener(this);
		save.setOnClickListener(this);
		reset.setOnClickListener(this);

		txt_date = (TextView) findViewById(R.id.date);
		category_error = (TextView) findViewById(R.id.category_error);

		calendar = Calendar.getInstance();

		y = calendar.get(Calendar.YEAR);
		m = calendar.get(Calendar.MONTH);
		d = calendar.get(Calendar.DAY_OF_MONTH);

		db_day = "";
		db_month = "";
		db_year = "";

		showDate(y, m + 1, d);

		r_catagories.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// find which radio button is selected

				if (checkedId == R.id.r_bills) {
					radio = r_bills.getText().toString();
				} else if (checkedId == R.id.r_clothing) {
					radio = r_clothing.getText().toString();
				} else if (checkedId == R.id.r_food)
					radio = r_food.getText().toString();
				else {
					radio = r_other.getText().toString();
				}

				if (!radio.isEmpty()) {
					category_error.setError(null);
				}

			}
		});

	}

	@SuppressWarnings("deprecation")
	public void setDate(View view) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "Select Date",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, y, m, d);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			// arg1 = year
			// arg2 = month
			// arg3 = day
			showDate(arg1, arg2 + 1, arg3);
		}
	};

	private void showDate(int year, int month, int day) {
		txt_date.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
		db_day = Integer.toString(day);
		db_month = Integer.toString(month);
		db_year = Integer.toString(year);

		// Toast.makeText(getApplicationContext(), db_day + "-" + db_month + "-"
		// + db_year, Toast.LENGTH_SHORT)
		// .show();

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

		case R.id.save:
			// String radio = "";
			// if (r_bills.isChecked())
			// radio = r_bills.getText().toString();

			if (title.getText().toString().isEmpty()) {
				title.setError("Enter Title");
			} else if (radio.isEmpty()) {
				category_error.setError("Select Category");

			} else if (amount.getText().toString().isEmpty()) {
				Toast.makeText(newentry.this, "Enter Amount", Toast.LENGTH_LONG)
						.show();
			} else {

				boolean isInserted = myDb.insertData(
						title.getText().toString(), db_day, db_month, db_year,
						radio, amount.getText().toString());

				if (isInserted = true) {
					Toast.makeText(newentry.this, "Data Saved",
							Toast.LENGTH_LONG).show();
					
					title.setText("");
					radio = "";
					amount.setText("");
					db_day = "";
					db_month = "";
					db_year = "";

					r_catagories.check(-1);

					showDate(y, m + 1, d);


				} else {
					Toast.makeText(newentry.this, "Data not Saved",
							Toast.LENGTH_LONG).show();
				}
			}

			break;

		case R.id.reset:
			title.setText("");
			radio = "";
			amount.setText("");
			db_day = "";
			db_month = "";
			db_year = "";

			r_catagories.check(-1);

			showDate(y, m + 1, d);

			break;

		default:
			break;
		}

	}

}
