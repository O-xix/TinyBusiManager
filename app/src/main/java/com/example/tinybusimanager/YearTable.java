package com.example.tinybusimanager;

import java.time.Year;
import java.util.ArrayList;

public class YearTable {
    int year;
    com.example.tinybusimanager.MonthTable[] fiscalMonths = new MonthTable[12];
    double NetInFlow;
    double NetOutFlow;
    double TotalFlow;

    public YearTable(int year) {
        this.year = year;
    }

    public void calculateNetInflow() {
        for (int i = 0; i < fiscalMonths.length; i++) {
            NetInFlow += fiscalMonths[i].NetInFlow;
        }
    }

    public void calculateNetOutflow() {
        for (int i = 0; i < fiscalMonths.length; i++) {
            NetOutFlow += fiscalMonths[i].NetOutFlow;
        }
    }

    public void calculateTotalFlow() {
        TotalFlow = NetInFlow - NetOutFlow;
    }
}
