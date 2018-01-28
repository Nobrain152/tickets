package anyeight.model;

import java.sql.Date;

/**
 * Created by T5-SK on 2017/5/15.
 */
public class StockBetweenTwoCan {
    private String ID;
    private Date dayBegin;
    private Date dayEnd;

    public StockBetweenTwoCan(String ID, Date dayBegin, Date dayEnd){
        this.ID = "stock_" + ID;
        this.dayBegin = dayBegin;
        this.dayEnd = dayEnd;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public Date getDayBegin(){
        return dayBegin;
    }

    public void setDayBegin(Date dayBegin){
        this.dayBegin = dayBegin;
    }

    public Date getDayEnd(){
        return dayEnd;
    }

    public void setDayEnd(Date dayEnd){this.dayEnd = dayEnd;}
}
