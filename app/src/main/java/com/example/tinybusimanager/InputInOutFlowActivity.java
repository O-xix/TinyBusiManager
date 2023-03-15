package com.example.tinybusimanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class InputInOutFlowActivity extends AppCompatActivity {
    NotificationManager NM;
    Notification notify;

    EditText editTextTitle;
    EditText editTextDescription;
    ToggleButton toggleButtonExpensePayment;
    EditText editTextCategory;
    EditText editTextDollars;
    EditText editTextCents;
    Button buttonAdd;

    String title;
    String description;
    String metacategory;
    String category;
    int dollars;
    int cents;
    double cash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_in_out_flow);

        //Assigning elements of GUI to variables.
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        toggleButtonExpensePayment = (ToggleButton) findViewById(R.id.toggleButtonExpensePayment);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        editTextDollars = (EditText) findViewById(R.id.editTextDollars);
        editTextCents = (EditText) findViewById(R.id.editTextCents);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        NM = (NotificationManager)getSystemService(this.NOTIFICATION_SERVICE);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = editTextTitle.getText().toString();
                description = editTextDescription.getText().toString();
                metacategory = toggleButtonExpensePayment.getText().toString();
                category = editTextCategory.getText().toString();

                if (title.replaceAll("\\s", "").equals("")) {
                    Toast.makeText(InputInOutFlowActivity.this, "Please add a meaningful title.", Toast.LENGTH_SHORT).show();
                }
                else if (editTextDollars.getText().toString().equals("") || editTextCents.getText().toString().equals("")) {
                    if (editTextDollars.getText().toString().equals("") && editTextCents.getText().toString().equals("")) {
                        Toast.makeText(InputInOutFlowActivity.this, "Please add the cash amount.", Toast.LENGTH_SHORT).show();
                    }
                    else if (editTextDollars.getText().toString().equals("")) {
                        Toast.makeText(InputInOutFlowActivity.this, "Please add the dollar amount. (Enter 0 if no dollar value.)", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(InputInOutFlowActivity.this, "Please add the cents amount. (Enter 00 if no cent value.)", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    dollars = Integer.parseInt(String.valueOf(editTextDollars.getText()));
                    cents = Integer.parseInt(String.valueOf(editTextCents.getText()));
                    cash = dollars + ((double) cents / 100);
                    Toast.makeText(InputInOutFlowActivity.this, "You have added the " + title + " " + metacategory.toLowerCase(Locale.ROOT) + " of $" + cash + ". ", Toast.LENGTH_SHORT).show();
                    notify = new Notification.Builder(getApplicationContext()).setContentTitle("TinyBusiManager").setContentText("You have added the " + title + " " + metacategory.toLowerCase(Locale.ROOT) + " of $" + cash + ". ").setContentTitle(metacategory + "added").setSmallIcon(R.drawable.ic_launcher_background).build();
                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    NM.notify(0, notify);
                    finish();
                }
            }
        });
    }
}