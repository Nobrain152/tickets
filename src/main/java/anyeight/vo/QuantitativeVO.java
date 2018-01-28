package anyeight.vo;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by 段梦洋 on 2017/3/21.
 */
public class QuantitativeVO {
    private Map<Calendar,Double> strategyEarning;                      //每天的策略收益率
    private Map<Calendar,Double> standardEarning;                       //每天的基准收益率
    private double annualizedReturn;                                   //年收益率
    private double standardAnnualizedReturn;                           //年基准收益率
    private double maximumDrawdown;                                      //最大回撤
    private double Beta;
    private double Alpha;
    private double sharpeRatie;                                           //夏普比率
    private Map<Double,Integer>yieldDistributionMap;                     //每百分之几的收益率有几个股票（直方图要用）

    public QuantitativeVO(Map<Calendar,Double>strategyEarning,Map<Calendar,Double>standardEarning,double annualizedReturn,double standardAnnualizedReturn,
                          double maximumDrawdown, double beta,double alpha,double sharpeRatie,Map<Double,Integer>yieldDistributionMap){
        this.strategyEarning=strategyEarning;
        this.standardEarning=standardEarning;
        this.standardAnnualizedReturn=standardAnnualizedReturn;
        this.annualizedReturn=annualizedReturn;
        this.maximumDrawdown=maximumDrawdown;
        this.Beta=beta;
        this.Alpha=alpha;
        this.sharpeRatie=sharpeRatie;
        this.yieldDistributionMap=yieldDistributionMap;
    }
    public QuantitativeVO(){

    }

    public Map<Calendar, Double> getStrategyEarning() {
        return strategyEarning;
    }

    public Map<Calendar, Double> getStandardEarning() {
        return standardEarning;
    }

    public double getAnnualizedReturn() {
        return annualizedReturn;
    }

    public double getStandardAnnualizedReturn() {
        return standardAnnualizedReturn;
    }

    public double getMaximumDrawdown() {
        return maximumDrawdown;
    }

    public double getAlpha() {
        return Alpha;
    }

    public double getBeta() {
        return Beta;
    }

    public double getSharpeRatie() {
        return sharpeRatie;
    }

    public Map<Double, Integer> getYieldDistributionMap() {
        return yieldDistributionMap;
    }


}
