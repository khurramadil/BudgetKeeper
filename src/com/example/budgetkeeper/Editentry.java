package com.example.budgetkeeper;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Editentry extends Activity implements OnClickListener {

	DatabaseHelper editDb;

	Button btn_update, btn_cancel;
	Context context;
	EditText title, amount;
	RadioButton r_clothing, r_food, r_bills, r_other;
	RadioGroup r_catagories;
	String radio = "", db_day, db_month, db_year;

	DatePicker datePicker;
	Calendar calendar;
	TextView txt_date, category_error;
	int d, m, y;

	String db_edit_tile, db_edit_d, db_edit_m, db_edit_y, db_edit_category,
			db_edit_amount;
	int db_edit_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editentry);

		Intent extra = getIntent();

		db_edit_id = extra.getIntExtra("id", -1);
		db_edit_tile = extra.getStringExtra("title");
		db_edit_d = extra.getStringExtra("day");
		db_edit_m = extra.getStringExtra("month");
		db_edit_y = extra.getStringExtra("year");
		db_edit_category = extra.getStringExtra("category");
		db_edit_amount = extra.getStringExtra("amount");

		editDb = new DatabaseHelper(this);

		context = this;

		r_catagories = (RadioGroup) findViewById(R.id.r_categories);

		title = (EditText) findViewById(R.id.title);

		amount = (EditText) findViewById(R.id.amount);
		r_clothing = (RadioButton) findViewById(R.id.r_clothing);
		r_bills = (RadioButton) findViewById(R.id.r_bills);
		r_food = (RadioButton) findViewById(R.id.r_food);
		r_other = (RadioButton) findViewById(R.id.r_other);

		btn_cancel = (Button) findViewById(R.id.cancel);
		btn_update = (Button) findViewById(R.id.update);

		btn_cancel.setOnClickListener(this);
		btn_update.setOnClickListener(this);

		txt_date = (TextView) findViewById(R.id.date);
		category_error = (TextView) findViewById(R.id.category_error);

		calendar = Calendar.getInstance();

		y = calendar.get(Calendar.YEAR);
		m = calendar.get(Calendar.MONTH);
		d = calendar.get(Calendar.DAY_OF_MONTH);

		db_day = db_edit_d;
		db_month = db_edit_m;
		db_year = db_edit_y;

		// db_day = "";
		// db_month = "";
		// db_year = "";

		d = Integer.parseInt(db_edit_d);
		m = Integer.parseInt(db_edit_m);
		y = Integer.parseInt(db_edit_y);
		m = m -1;

		showDate(y, m + 1, d);

		title.setText(db_edit_tile);
		txt_date.setText(db_edit_d + "/" + db_edit_m + "/" + db_edit_y);
		radio = db_edit_category;

		if (radio.equalsIgnoreCase("Clothing")) {
			r_catagories.check(R.id.r_clothing);
		} else if (radio.equalsIgnoreCase("Bills")) {
			r_catagories.check(R.id.r_bills);
		} else if (radio.equalsIgnoreCase("Food")) {
			r_catagories.check(R.id.r_food);
		} else {
			r_catagories.check(R.id.r_other);
		}

		amount.setText(db_edit_amount);

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

		case R.id.update:

	//		Toast.makeText(context, "" + db_edit_id, Toast.LENGTH_SHORT).show();
	//		Toast.makeText(context, db_edit_tile, Toast.LENGTH_SHORT).show();
	//		Toast.makeText(context, db_edit_d, Toast.LENGTH_SHORT).show();
	//		Toast.makeText(context, db_edit_m, Toast.LENGTH_SHORT).show();
	//		Toast.makeText(context, db_edit_y, Toast.LENGTH_SHORT).show();
	//		Toast.makeText(context, db_edit_category, Toast.LENGTH_SHORT)
	//				.show();
	//		Toast.makeText(context, db_edit_amount, Toast.LENGTH_SHORT).show();

			if (title.getText().toString().isEmpty()) {
				title.setError("Enter Title");
			} else if (radio.isEmpty()) {
				category_error.setError("Select Category");

			} else if (amount.getText().toString().isEmpty()) {
				Toast.makeText(Editentry.this, "Enter Amount",
						Toast.LENGTH_LONG).show();
			} else {

				boolean isUpdated = editDb.updateData(db_edit_id, title
						.getText().toString(), db_day, db_month, db_year,
						radio, amount.getText().toString());

				if (isUpdated = true) {
					Toast.makeText(Editentry.this, "Data Saved",
							Toast.LENGTH_LONG).show();

				} else {
					Toast.makeText(Editentry.this, "Data not Saved",
							Toast.LENGTH_LONG).show();
				}

				finish();
			}

			break;

		case R.id.cancel:

			finish();

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
