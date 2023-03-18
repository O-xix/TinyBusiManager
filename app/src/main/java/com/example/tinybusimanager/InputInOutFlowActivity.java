package com.example.tinybusimanager;

import static com.example.tinybusimanager.MainActivity.metaFinancialActivityLog;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class InputInOutFlowActivity extends AppCompatActivity {
    NotificationManager NM;
    Notification notify;
    Boolean foundMonthTable = false;

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
    LocalDate date;
    LocalTime time;


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
        NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //(Need To Fix)
        //date = ;

        buttonAdd.setOnClickListener(view -> {
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
                date = LocalDate.now();
                time = LocalTime.now();
                Toast.makeText(InputInOutFlowActivity.this, "You have added the " + title + " " + metacategory.toLowerCase(Locale.ROOT) + " of $" + cash + ". ", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < metaFinancialActivityLog.size(); i++) {
                    System.out.println(i);
                    //cycling through years
                    for (int j = 1; j <= 12; j++) {
                        //cycling through months

                        //if year = year
                        if (j == date.getMonthValue() && metaFinancialActivityLog.get(i).year == date.getYear()) {
                            metaFinancialActivityLog.get(i).fiscalMonths[j - 1].financialActivities.add(new MonthTable.FinancialFluct(date, time, title, description, cash, category, metacategory));
                            foundMonthTable = true;
                        }
                    }
                }
                if (!foundMonthTable) {
                    metaFinancialActivityLog.add(new YearTable(date.getYear()));
                    //print out separate elements in Toasts
                    //0.2
                    MonthTable.FinancialFluct financialFluct = new MonthTable.FinancialFluct(date, time, title, description, cash, category, metacategory);
                    YearTable metaYearAccessor = metaFinancialActivityLog.get(metaFinancialActivityLog.size() - 1);
                    MonthTable metaMonthAccessor = metaYearAccessor.fiscalMonths[date.getMonthValue() - 1];
                    metaMonthAccessor.financialActivities.add(financialFluct);
                }

                //Notification
                //notify = new Notification.Builder(getApplicationContext()).setContentTitle("TinyBusiManager").setContentText("You have added the " + title + " " + metacategory.toLowerCase(Locale.ROOT) + " of $" + cash + ". ").setContentTitle(metacategory + "added").setSmallIcon(R.drawable.ic_launcher_background).build();
                //notify.flags |= Notification.FLAG_AUTO_CANCEL;
                //NM.notify(0, notify);

                finish();
            }
        });
    }
}