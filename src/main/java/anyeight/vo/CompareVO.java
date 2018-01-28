package anyeight.vo;

import java.util.ArrayList;

/**
 * Created by 段梦洋 on 2017/3/4.
 */

/***
 * 注：在异常情况下，high值设置为负，用来提示不同的错误信息:
 * -1:用户输入代码或股票名称不存在
 * -2：用户输入的时间不在有数据的范围内
 * -3：用户输入的时间下股票无数据（周末或者什么的）
 */
public class CompareVO {
    private String name;
    private String stockID;
    private double low;
    private double high;
    private double change;                              //涨跌幅
    private ArrayList<Double> settlement;                         //每天的收盘价的集合
    private ArrayList<Double> logarithmicYield;                  //每天的对数收益率的集合
    private double logarithmicYieldVariance;         //对数收益率方差


    public double getLow(){
        return low;
    }
    public double getHigh(){
        return high;
    }
    public double getChange(){
        return change;
    }
    public ArrayList<Double> getSettlement(){
        return settlement;
    }
    public ArrayList<Double> getLogarithmicYield(){
        return logarithmicYield;
    }
    public double getLogarithmicYieldVariance(){
        return logarithmicYieldVariance;
    }

    public String getName() {
        return name;
    }

    public String getStockID() {
        return stockID;
    }

    public void setHigh(double high) {
        this.high = high;
    }
}
