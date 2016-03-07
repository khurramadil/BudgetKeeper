package com.example.budgetkeeper;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class view extends Activity implements OnClickListener,
		OnItemSelectedListener {

	int abc;

	DatabaseHelper myDb;

	Button green_button, blue_button, red_button, yellow_button, home_button,
			btn_view, btn_search_specific;
	Context context;

	Spinner spinner_month, spinner_year;
	Calendar calendar;
	int m, y;
	String db_view_month;
	String db_view_year;
	String db_search_day, db_search_month, db_search_year;
	String db_delete_d, db_delete_m, db_delete_y;
	String db_edit_tile, db_edit_d, db_edit_m, db_edit_y, db_edit_category,
			db_edit_amount;
	DatePicker datePicker;
	TextView search_date;
	
	EditText txt_title_search;
	Button btn_title_search;
	// txt_custom_view;
	int c_day, c_month, c_year;
	int entries;
	int db_edit_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);

		myDb = new DatabaseHelper(this);

		context = this;

		txt_title_search = (EditText) findViewById(R.id.txt_title);
		btn_title_search = (Button) findViewById(R.id.btn_title_search);
		btn_title_search.setOnClickListener(this);
		spinner_month = (Spinner) findViewById(R.id.spinner_month);
		spinner_year = (Spinner) findViewById(R.id.spinner_year);

		spinner_month.setOnItemSelectedListener(this);
		spinner_year.setOnItemSelectedListener(this);

		List<String> months = new ArrayList<String>();
		months.add("January");
		months.add("February");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("August");
		months.add("September");
		months.add("October");
		months.add("November");
		months.add("December");

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

		ArrayAdapter<String> monthDataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, months);
		ArrayAdapter<String> yearDataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, year);

		monthDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_month.setAdapter(monthDataAdapter);
		spinner_year.setAdapter(yearDataAdapter);

		green_button = (Button) findViewById(R.id.green_button);
		blue_button = (Button) findViewById(R.id.blue_button);
		red_button = (Button) findViewById(R.id.red_button);
		yellow_button = (Button) findViewById(R.id.yellow_button);
		home_button = (Button) findViewById(R.id.home_button);

		btn_view = (Button) findViewById(R.id.btn_view);
		search_date = (TextView) findViewById(R.id.search_date);
		
		// txt_custom_view = (TextView) findViewById(R.id.txt_custom_dialog);

		btn_search_specific = (Button) findViewById(R.id.btn_search_specific);

		green_button.setOnClickListener(this);
		blue_button.setOnClickListener(this);
		red_button.setOnClickListener(this);
		yellow_button.setOnClickListener(this);
		home_button.setOnClickListener(this);

		btn_view.setOnClickListener(this);
		btn_search_specific.setOnClickListener(this);

		calendar = Calendar.getInstance();

		y = calendar.get(Calendar.YEAR);
		m = calendar.get(Calendar.MONTH);

		y = (y - 2015);

		spinner_month.setSelection(m, true);
		spinner_year.setSelection(y, true);

		c_year = calendar.get(Calendar.YEAR);
		c_month = calendar.get(Calendar.MONTH);
		c_day = calendar.get(Calendar.DAY_OF_MONTH);

		db_search_day = "";
		db_search_month = "";
		db_search_year = "";

		showDate(c_year, c_month + 1, c_day);

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

		case R.id.btn_view:

			Cursor res3 = myDb.searchData(db_view_month, db_view_year);

			StringBuffer buffer3 = new StringBuffer();

			Spannable word = new SpannableString("AMOUNT");
			word.setSpan(new ForegroundColorSpan(Color.BLUE), 0, word.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			while (res3.moveToNext()) {
				// buffer2.append("ID:													" + res2.getString(0)
				// + "\n");
				buffer3.append("TITLE:									" + res3.getString(1) + "\n");
				buffer3.append("DATE:									" + res3.getString(2) + "-"
						+ res3.getString(3) + "-" + res3.getString(4) + "\n");
				buffer3.append("CATEGORY:		" + res3.getString(5) + "\n");
				buffer3.append(word + ":					" + res3.getString(6) + "\n"
						+ "_________________________________\n\n");
			}

			if (res3.getCount() == 0) {
				showMessage("Error", "Nothing Found");
			} else {

				showMessage("Data", buffer3.toString());
			}
			break;

		case R.id.btn_title_search:
			
			String x = txt_title_search.getText().toString();

			Cursor res4 = myDb.searchTitle(x);

			StringBuffer buffer4 = new StringBuffer();

//			Spannable word2 = new SpannableString("AMOUNT");
//			word2.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
//					word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			if (res4.getCount() > 0 ) {
				while (res4.moveToNext()) {
					// buffer2.append("ID:													" + res2.getString(0)
					// + "\n");
					buffer4.append("TITLE:									" + res4.getString(1) + "\n");
					buffer4.append("DATE:									" + res4.getString(2) + "-"
							+ res4.getString(3) + "-" + res4.getString(4)
							+ "\n");
					buffer4.append("CATEGORY:		" + res4.getString(5) + "\n");
					buffer4.append("AMOUNT:					" + res4.getString(6) + "\n"
							+ "_________________________________\n\n");
				}

				res4.moveToFirst();
				db_delete_d = res4.getString(2);
				db_delete_m = res4.getString(3);
				db_delete_y = res4.getString(4);

				db_edit_id = Integer.parseInt(res4.getString(0));
				db_edit_tile = res4.getString(1);
				db_edit_d = res4.getString(2);
				db_edit_m = res4.getString(3);
				db_edit_y = res4.getString(4);
				db_edit_category = res4.getString(5);
				db_edit_amount = res4.getString(6);

				showMessage("Data", buffer4.toString(), 1);

			}

			
		
		case R.id.btn_search_specific:

			Cursor res5 = myDb.searchData(db_search_month, db_search_year,
					db_search_day);

			StringBuffer buffer5 = new StringBuffer();

			Spannable word2 = new SpannableString("AMOUNT");
			word2.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
					word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			if (res5.getCount() == 1) {
				while (res5.moveToNext()) {
					// buffer2.append("ID:													" + res2.getString(0)
					// + "\n");
					buffer5.append("TITLE:									" + res5.getString(1) + "\n");
					buffer5.append("DATE:									" + res5.getString(2) + "-"
							+ res5.getString(3) + "-" + res5.getString(4)
							+ "\n");
					buffer5.append("CATEGORY:		" + res5.getString(5) + "\n");
					buffer5.append("AMOUNT:					" + res5.getString(6) + "\n"
							+ "_________________________________\n\n");
				}

				res5.moveToFirst();
				db_delete_d = res5.getString(2);
				db_delete_m = res5.getString(3);
				db_delete_y = res5.getString(4);

				db_edit_id = Integer.parseInt(res5.getString(0));
				db_edit_tile = res5.getString(1);
				db_edit_d = res5.getString(2);
				db_edit_m = res5.getString(3);
				db_edit_y = res5.getString(4);
				db_edit_category = res5.getString(5);
				db_edit_amount = res5.getString(6);

				showMessage("Data", buffer5.toString(), 1);

			}

			else if (res5.getCount() == 0) {
				showMessage("Error", "Nothing Found");
			} else {
				if (res5.getCount() > 1) {
			//		Toast.makeText(view.this, "" + res4.getCount(),
			//				Toast.LENGTH_LONG).show();

					while (res5.moveToNext()) {
						buffer5.append("ID:													" + res5.getString(0)
								+ "\n");
						buffer5.append("TITLE:									" + res5.getString(1)
								+ "\n");
						buffer5.append("DATE:									" + res5.getString(2)
								+ "-" + res5.getString(3) + "-"
								+ res5.getString(4) + "\n");
						buffer5.append("CATEGORY:		" + res5.getString(5) + "\n");
						buffer5.append("AMOUNT:					" + res5.getString(6)
								+ "\n"
								+ "_________________________________\n\n");
					}
					res5.moveToFirst();

				}
				showMessage("Data", buffer5.toString(), 2);
			}
			break;

		default:
			break;
		}

	}

	public void showMessage(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setNegativeButton("Back",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog closed

					}
				});
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	public void showMessage(String title, String message, int x) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		entries = x;

		builder.setNegativeButton("Back",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog closed

					}
				});

		builder.setPositiveButton("Delete",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					//	Toast.makeText(view.this, "Delete pressed",
						//		Toast.LENGTH_SHORT).show();

						if (entries == 2) {

							LayoutInflater li = LayoutInflater.from(context);
							View promptsView = li
									.inflate(R.layout.prompt, null);

							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);

							// set prompts.xml to alertdialog builder
							alertDialogBuilder.setView(promptsView);

							final EditText userInput = (EditText) promptsView
									.findViewById(R.id.editTxtDeleteId);

							// set dialog message
							alertDialogBuilder
									.setCancelable(false)
									.setPositiveButton(
											"Delete",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													// get user input and set it
													// to
													// result
													// edit text
													int temp = Integer
															.parseInt(userInput
																	.getText()
																	.toString());

													int r = -2;

													r = myDb.deleteData(temp);

													if (r > 0) {
														Toast.makeText(
																view.this,
																r
																		+ " Record Deleted",
																Toast.LENGTH_LONG)
																.show();
													} else {
														Toast.makeText(
																view.this,
																" No Record Found",
																Toast.LENGTH_LONG)
																.show();
													}

												}
											})
									.setNegativeButton(
											"Cancel",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													dialog.cancel();
												}
											});

							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder
									.create();

							// show it
							alertDialog.show();

						} else if (entries == 1) {

							int r = -2;

							r = myDb.deleteData(db_delete_d, db_delete_m,
									db_delete_y);

							if (r > 0) {
								Toast.makeText(view.this,
										r + " Record Deleted",
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(view.this, " No Record Found",
										Toast.LENGTH_LONG).show();
							}
						}

					}
				});

		builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
		
				if (entries == 2) {
			//		Toast.makeText(view.this, "multiple entries",
			//				Toast.LENGTH_SHORT).show();

					LayoutInflater li = LayoutInflater.from(context);
					View promptsView = li.inflate(R.layout.prompt, null);
			

					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);

					// set prompts.xml to alertdialog builder
					alertDialogBuilder.setView(promptsView);

					final EditText userInput = (EditText) promptsView
							.findViewById(R.id.editTxtDeleteId);

					// set dialog message
					alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("Update",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// get user input and set it
											// to
											// result
											// edit text
											int temp = Integer
													.parseInt(userInput
															.getText()
															.toString());

											Toast.makeText(context, "" + temp,
													Toast.LENGTH_LONG).show();

											// write code to search entry and
											// set its data in editentry
											Cursor res5 = myDb.searchData(temp);

											StringBuffer buffer5 = new StringBuffer();

											if (res5.getCount() != 0) {
												while (res5.moveToNext()) {
													buffer5.append("ID:													"
															+ res5.getString(0)
															+ "\n");
													buffer5.append("TITLE:									"
															+ res5.getString(1)
															+ "\n");
													buffer5.append("DATE:									"
															+ res5.getString(2)
															+ "-"
															+ res5.getString(3)
															+ "-"
															+ res5.getString(4)
															+ "\n");
													buffer5.append("CATEGORY:		"
															+ res5.getString(5)
															+ "\n");
													buffer5.append("AMOUNT:					"
															+ res5.getString(6)
															+ "\n"
															+ "_________________________________\n\n");
												}
											}
											res5.moveToFirst();

											db_edit_id = Integer.parseInt(res5
													.getString(0));
											db_edit_tile = res5.getString(1);
											db_edit_d = res5.getString(2);
											db_edit_m = res5.getString(3);
											db_edit_y = res5.getString(4);
											db_edit_category = res5
													.getString(5);
											db_edit_amount = res5.getString(6);

											// launch editentry with data to
											// edit and update

											Intent edit_screen2 = new Intent(
													context, Editentry.class);

											edit_screen2.putExtra("id",
													db_edit_id);
											edit_screen2.putExtra("title",
													db_edit_tile);
											edit_screen2.putExtra("day",
													db_edit_d);
											edit_screen2.putExtra("month",
													db_edit_m);
											edit_screen2.putExtra("year",
													db_edit_y);
											edit_screen2.putExtra("category",
													db_edit_category);
											edit_screen2.putExtra("amount",
													db_edit_amount);

											edit_screen2
													.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

											edit_screen2
													.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

											startActivity(edit_screen2);

										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();

				}

				else if (entries == 1) {
					Intent edit_screen = new Intent(context, Editentry.class);

					edit_screen.putExtra("id", db_edit_id);
					edit_screen.putExtra("title", db_edit_tile);
					edit_screen.putExtra("day", db_edit_d);
					edit_screen.putExtra("month", db_edit_m);
					edit_screen.putExtra("year", db_edit_y);
					edit_screen.putExtra("category", db_edit_category);
					edit_screen.putExtra("amount", db_edit_amount);

					edit_screen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					edit_screen.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

					startActivity(edit_screen);

				}
			}
		});

		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();

	}

	// methods for view data

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		if (parent == spinner_month) {
			spinner_month.setSelection(position);
			db_view_month = Integer.toString(position + 1);
	//		Toast.makeText(view.this, "Month selected = " + db_view_month,
	//				Toast.LENGTH_LONG).show();

		} else {
			spinner_year.setSelection(position);
			db_view_year = (String) spinner_year.getSelectedItem();
	//		Toast.makeText(view.this, "Month selected = " + db_view_year,
	//				Toast.LENGTH_LONG).show();

		}

		// String db_view_month = parent.getItemAtPosition(position).toString();
		// Toast.makeText(view.this, "Month selected = " + db_view_month ,
		// Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	// method to set date to search database

	@SuppressWarnings("deprecation")
	public void search_setDate(View view) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "Select Date",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		if (id == 999) {
			return new DatePickerDialog(this, myDateListener2, c_year, c_month,
					c_day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener2 = new DatePickerDialog.OnDateSetListener() {
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
		search_date.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
		db_search_day = Integer.toString(day);
		db_search_month = Integer.toString(month);
		db_search_year = Integer.toString(year);

		// Toast.makeText(getApplicationContext(), db_day + "-" + db_month + "-"
		// + db_year, Toast.LENGTH_SHORT)
		// .show();

	}

}
