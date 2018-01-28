package anyeight.model;

import java.util.Calendar;

public class Stock {
    private Calendar date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volome;
    private double adjClose;
    private String id;
    private String name;
    private String market;

    public Stock(){

    }

    public Calendar getDate(){
        return this.date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public long getVolome() {
        return volome;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public double getClose() {
        return close;
    }

    public String getCode() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMarket() {
        return market;
    }
}
