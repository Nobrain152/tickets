package anyeight.model;

/**
 * Created by 啊 on 2017/5/14.
 */
public class Market {
    private long totalVolume;
    private int hardenStock;
    private int dropStock;
    private int over5Stock;
    private int down5Stock;
    private int over5PriceStock;
    private int down5PriceStock;

    public Market(){

    }

    public long getTotalVolume() {
        return totalVolume;
    }

    public int getHardenStock() {
        return hardenStock;
    }

    public int getDropStock() {
        return dropStock;
    }

    public int getOver5Stock() {
        return over5Stock;
    }

    public int getDown5Stock() {
        return down5Stock;
    }

    public int getOver5PriceStock() {
        return over5PriceStock;
    }

    public int getDown5PriceStock() {
        return down5PriceStock;
    }

    public void setTotalVolume(long totalVolume) {
        this.totalVolume = totalVolume;
    }
}
