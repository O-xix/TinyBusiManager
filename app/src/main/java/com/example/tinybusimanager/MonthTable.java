package com.example.tinybusimanager;

import java.sql.Date;
import java.sql.Time;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class MonthTable {
    Month month;
    Year year;
    ArrayList<FinancialFluct> financialActivities;
    double NetInFlow;
    double NetOutFlow;
    double TotalFlow;

    public MonthTable(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    public void calculateNetInflow() {
        for (int i = 0; i < financialActivities.size(); i++) {
            if (financialActivities.get(i).metacategory == "in") {
                NetInFlow += financialActivities.get(i).cash;
            }
        }
    }

    public void calculateNetOutflow() {
        for (int i = 0; i < financialActivities.size(); i++) {
            if (financialActivities.get(i).metacategory == "out") {
                NetOutFlow += financialActivities.get(i).cash;
            }
        }
    }

    public void calculateNetFlow() {
        TotalFlow = NetInFlow - NetOutFlow;
    }

    private class FinancialFluct {
        Date date;
        Time time;
        String title;
        String description;
        double cash;
        String category;
        String metacategory;

        private FinancialFluct(Date date, Time time, String title, String description, double cash, String category, String metacategory) {
            this.date = date;
            this.time = time;
            this.title = title;
            this.description = description;
            this.cash = cash;
            this.category = category;
            this.metacategory = metacategory;
        }


    }
}
