package com.example.tinybusimanager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MonthTable {
    int month;
    int year;
    ArrayList<FinancialFluct> financialActivities;
    double NetInFlow;
    double NetOutFlow;
    double TotalFlow;

    public MonthTable(int month, int year) {
        this.month = month;
        this.year = year;
        financialActivities = new ArrayList<>();
    }

    public void calculateNetInflow() {
        for (int i = 0; i < financialActivities.size(); i++) {
            if (financialActivities.get(i).metacategory.equals("PAYMENT")) {
                NetInFlow += financialActivities.get(i).cash;
            }
        }
    }

    public void calculateNetOutflow() {
        for (int i = 0; i < financialActivities.size(); i++) {
            if (financialActivities.get(i).metacategory.equals("EXPENSE")) {
                NetOutFlow += financialActivities.get(i).cash;
            }
        }
    }

    public void calculateNetFlow() {
        TotalFlow = NetInFlow - NetOutFlow;
    }

    public static class FinancialFluct {
        LocalDate date;
        LocalTime time;
        String title;
        String description;
        double cash;
        String category;
        String metacategory;

        FinancialFluct(LocalDate date, LocalTime time, String title, String description, double cash, String category, String metacategory) {
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
