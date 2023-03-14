package com.example.tinybusimanager;

import java.time.Year;
import java.util.ArrayList;

public class YearTable {
    Year year;
    ArrayList<com.example.tinybusimanager.MonthTable> fiscalMonths;
    double NetInFlow;
    double NetOutFlow;
    double TotalFlow;

    public YearTable(Year year) {
        this.year = year;
    }

    public void calculateNetInflow() {
        for (int i = 0; i < fiscalMonths.size(); i++) {
            NetInFlow += fiscalMonths.get(i).NetInFlow;
        }
    }

    public void calculateNetOutflow() {
        for (int i = 0; i < fiscalMonths.size(); i++) {
            NetOutFlow += fiscalMonths.get(i).NetOutFlow;
        }
    }

    public void calculateTotalFlow() {
        TotalFlow = NetInFlow - NetOutFlow;
    }
}
