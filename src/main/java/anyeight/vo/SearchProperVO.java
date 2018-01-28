package anyeight.vo;


import java.util.Map;

/**
 * Created by 段梦洋 on 2017/4/16.
 */
public class SearchProperVO {
    private Map<Integer,Double> excessEarning;
    private Map<Integer,Double> strategyWin;
    public SearchProperVO(Map map1,Map map2){
        this.excessEarning=map1;
        this.strategyWin=map2;
    }
    public SearchProperVO(){

    }

    public Map<Integer,Double> getExcessEarning() {
        return excessEarning;
    }

    public Map<Integer,Double> getStrategyWin() {
        return strategyWin;
    }
}
