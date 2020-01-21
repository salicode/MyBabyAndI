package com.bawp.babyneeds;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bawp.babyneeds.data.DatabaseHandler;
import com.bawp.babyneeds.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class BabyFeeder extends AppCompatActivity {
    private AlertDialog dialog;
    private EditText babyItem;
    private EditText itemQuantity;
    private EditText itemColor;
    private EditText itemSize;
    private int seconds = 0;
    private boolean startRun;
    private DatabaseHandler databaseHandler;
    DatePickerDialog picker;



    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_feeder);


       // babyItem = findViewById(R.id.babyItem);
        //itemQuantity =findViewById(R.id.itemQuantity);

















        databaseHandler = new DatabaseHandler(this);

       // byPassActivity();

        //check if item was saved
        List<Item> items = databaseHandler.getAllItems();
        for (Item item : items) {
            Log.d("Main", "onCreate: " + item.getItemColor());
        }


        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                //  Toast.makeText(
                //        getApplicationContext(),
                //  expandableListTitle.get(groupPosition)
                //        + " -> "
                //       + expandableListDetail.get(
                //      expandableListTitle.get(groupPosition)).get(
                //    childPosition), Toast.LENGTH_SHORT
                // ).show();

                Timer();
                if (groupPosition == 0 && childPosition == 0 || childPosition == 1) {

                             MyStart();
                        //   MyStop();
                        //createPopupDialog();
                        //Intent intent = new Intent(BabyFeeder.this,Diat.class);
                       // startActivity(intent);
                        //  onClickStart();
                       // createPopupDialog();
                        //startRun = true;
                    } else if (groupPosition == 1 && childPosition == 0) {
                    Intent intent = new Intent(BabyFeeder.this,Diat.class);
                     startActivity(intent);
                } else if (groupPosition == 2 && childPosition == 0 ){
                    createPopupDialog();
                } else if (groupPosition == 3 && childPosition == 0 ){
                    createPopupDialog();
                } else if (groupPosition == 4 && childPosition == 0 ){
                    Intent intent = new Intent(BabyFeeder.this,ListActivity.class);
                    startActivity(intent);
                } else if (groupPosition == 5 && childPosition == 0 ){
                    Intent intent = new Intent(BabyFeeder.this,Benefit.class);
                    startActivity(intent);
                } else if (groupPosition == 6 && childPosition == 0){
                    Intent intent = new Intent(BabyFeeder.this,MyAlarmActivity.class);
                    startActivity(intent);
                }

                return false;
            }
        });

}

    private void saveItem(View view) {
        //Todo: save each baby item to db
        Item item = new Item();

        String newItem = babyItem.getText().toString().trim();
        String newColor = itemColor.getText().toString().trim();
        String  quantity = itemQuantity.getText().toString().trim();
        String size = itemSize.getText().toString().trim();

        item.setItemName(newItem);
        item.setItemColor(newColor);
        item.setItemQuantity(quantity);
        item.setItemSize(size);

        databaseHandler.addItem(item);

        Snackbar.make(view, "Item Saved",Snackbar.LENGTH_SHORT)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //code to be run
                dialog.dismiss();
                //Todo: move to next screen - details screen
                startActivity(new Intent(BabyFeeder.this, ListActivity.class));

            }
        }, 1200);// 1sec
    }

    @SuppressLint("MissingSuperCall")
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("startRun", startRun);
    }

    public void MyStart() {
        startRun = true;
    }

    public void MyStop() {
        startRun = false;
    }

    public void MyResest() {
        startRun = false;
        seconds = 0;
    }

    private void Timer() {
       // final TextView timeView = findViewById(R.id.timeview);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                @SuppressLint("DefaultLocale") String time = String.format(  "LEFT:  %d:%02d:%02d", hours, minutes, secs);

               // timeView.setText(time);

                if (startRun) {
                    seconds++;
                }

                handler.postDelayed(this, 100);
            }
        });
    }

    private void createPopupDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.popup, null);

        babyItem = view.findViewById(R.id.babyItem);
        itemQuantity = view.findViewById(R.id.itemQuantity);
        itemColor = view.findViewById(R.id.itemColor);
        itemSize = view.findViewById(R.id.itemSize);
        Button saveButton = view.findViewById(R.id.saveButton);

        babyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(BabyFeeder.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                babyItem.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        itemQuantity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BabyFeeder.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        itemQuantity.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!babyItem.getText().toString().isEmpty()
                        && !itemColor.getText().toString().isEmpty()
                        && !itemQuantity.getText().toString().isEmpty()
                        && !itemSize.getText().toString().isEmpty()) {
                    saveItem(v);
                }else {
                    Snackbar.make(v, "Empty Fields not Allowed", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });

        builder.setView(view);
        dialog = builder.create();// creating our dialog object
        dialog.show();// important step!



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        }

