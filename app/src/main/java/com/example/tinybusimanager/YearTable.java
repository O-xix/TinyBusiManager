package com.example.tinybusimanager;

public class YearTable {
    int year;
    com.example.tinybusimanager.MonthTable[] fiscalMonths;
    double NetInFlow;
    double NetOutFlow;
    double TotalFlow;

    public YearTable(int year) {
        fiscalMonths = new MonthTable[12];
        this.year = year;
        for (int i = 0; i < fiscalMonths.length; i++) {
            fiscalMonths[i] = new MonthTable(i, year);
        }
    }

    public void calculateNetInflow() {
        for (MonthTable fiscalMonth : fiscalMonths) {
            NetInFlow += fiscalMonth.NetInFlow;
        }
    }

    public void calculateNetOutflow() {
        for (MonthTable fiscalMonth : fiscalMonths) {
            NetOutFlow += fiscalMonth.NetOutFlow;
        }
    }

    public void calculateTotalFlow() {
        TotalFlow = NetInFlow - NetOutFlow;
    }
}
