package anyeight.model;

/**
 * Created by 啊 on 2017/5/14.
 */
public class BuyAndSellPrice {
    private double startAdjClose;
    private double endAdjClose;
    private String id;

    public BuyAndSellPrice(){

    }

    public double getStartAdjClose(){
        return startAdjClose;
    }
    public double getEndAdjClose(){
        return endAdjClose;
    }
    public String getId(){
        return id;
    }
}
