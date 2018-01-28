package anyeight.po;

import java.sql.Date;

/**
 * Created by 啊 on 2017/5/12.
 */
public class StockPO {
    //date：日期
    private Date date;
    //open：开盘价
    private  double open;
    //high：最高价
    private  double high;
    //close：收盘价
    private  double close;
    //low：最低价
    private  double low;
    //volume：成交量
    private  double volume;
    //price_change：价格变动
    private  double price_change;
    //p_change：涨跌幅
    private double p_change;
    //ma5：5日均价
    private  double ma5;
    //ma10：10日均价
    private  double ma10;
    //ma20:20日均价
    private  double ma20;
    //v_ma5:5日均量
    private  double v_ma5;
    //v_ma10:10日均量
    private  double v_ma10;
    //v_ma20:20日均量
    private  double v_ma20;
//
//    public StockPO(){}
//
//    public StockPO(Date date,double open,double high,double close,double low,double volume,double price_change,double p_change,
//                   double ma5,double ma10,double ma20,double v_ma5,double v_ma10,double v_ma20){
//        this.date = date;
//        this.open = open;
//        this.high = high;
//        this.close = close;
//        this.low = low;
//        this.volume = volume;
//        this.price_change = price_change;
//        this.p_change = p_change;
//        this.ma5 = ma5;
//        this.ma10 = ma10;
//        this.ma20 = ma20;
//        this.v_ma5 = v_ma5;
//        this.v_ma10 = v_ma10;
//        this.v_ma20 = v_ma20;
//    }

    public double getLow() {
        return low;
    }

    public Date getDate() {
        return date;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public double getMa5() {
        return ma5;
    }

    public double getMa10() {
        return ma10;
    }

    public double getMa20() {
        return ma20;
    }

    public double getOpen() {
        return open;
    }

    public double getP_change() {
        return p_change;
    }

    public double getPrice_change() {
        return price_change;
    }

    public double getV_ma5() {
        return v_ma5;
    }

    public double getV_ma10() {
        return v_ma10;
    }

    public double getV_ma20() {
        return v_ma20;
    }

    public double getVolume() {
        return volume;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setPrice_change(double price_change) {
        this.price_change = price_change;
    }

    public void setP_change(double p_change) {
        this.p_change = p_change;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }

    public void setV_ma5(double v_ma5) {
        this.v_ma5 = v_ma5;
    }

    public void setV_ma10(double v_ma10) {
        this.v_ma10 = v_ma10;
    }

    public void setV_ma20(double v_ma20) {
        this.v_ma20 = v_ma20;
    }
}
