package anyeight.serviceImpl.strategy;

import anyeight.dao.StockMapper;
import anyeight.model.Stock;
import anyeight.util.LineType;
import anyeight.util.MathHelper;
import anyeight.vo.QuantitativeVO;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 啊 on 2017/5/14.
 */
public class Strategy {
    StockMapper stockMapper;
    protected Map<Calendar,Double>strategyEarning=new LinkedHashMap<>();
    protected Map<Calendar,Double> standardEarning=new LinkedHashMap<>();
    protected double annualizedReturn;
    protected double standardAnnualizedReturn;
    protected double maximumDrawdown=0;
    protected double Beta;
    protected double Alpha;
    protected double sharpeRatio;
    protected Map<Double,Integer>yieldDistributionMap=new TreeMap<>();


    private ArrayList<Double> strategyYieldRate=new ArrayList<>();              //每个持有期的策略收益率
    private ArrayList<Double> standardYieldRate=new ArrayList<>();              //每个持有期的标准收益率
    private final double riskFreeRate=0.175;
    private double strategyMoneyPerHold=100.0;                                          //每个持有期后所持有的钱数
    private double standardMoneyPerHold=100.0;
    private final double chengBen=100.0;                                       //总成本
    private ArrayList<Calendar>endHoldDate=new ArrayList<>();

    public Strategy(){

    }

    protected ArrayList selectStock(Map oldMap,double proportion) {                                                              //对股票进行排序并返回前20%的股票
        ArrayList<Map.Entry<String,Double>> list=new ArrayList<Map.Entry<String,Double>>(oldMap.entrySet());
        Collections.sort(list,new Comparator<Map.Entry <String, Double>>() {
            @Override
            public int compare(Map.Entry<String,Double> arg0, Map.Entry<String,Double> arg1) {
                return -(arg0.getValue().compareTo(arg1.getValue()));                                      //从大到小排序
            }});
        ArrayList result = new ArrayList();


        double size= (list.size()* proportion);
        String toSplit = "" + size;
        int integer = Integer.valueOf(toSplit.split("\\.")[0]);
        double xiaoShu = Double.valueOf("0." + toSplit.split("\\.")[1]);
        if(xiaoShu != 0)                                                                                //进一法
            integer++;
        for(int i = 0; i<integer;i++){
            result.add(list.get(i).getKey());
        }
        //System.out.println(result.size());
        return result;
    }

    protected void setLine(Calendar first, Calendar after, ArrayList<String> list,LineType type,Map<String,Double> openList){
        Map<Calendar,Double>dayGet=new HashMap<>();
        int actualSize=0;
        for(int i=0;i<list.size();i++){
            if(openList.containsKey(list.get(i))) {
                actualSize++;
                double open=openList.get(list.get(i));
                ArrayList<Stock> stocks = stockMapper.getOneStock(list.get(i), first, after);
                for (int j = 0; j < stocks.size(); j++) {
                    Calendar calendar = stocks.get(j).getDate();
                    double perStockGet=stocks.get(j).getAdjClose()/open;
                    if (dayGet.containsKey(calendar))
                        dayGet.replace(calendar, dayGet.get(calendar) + perStockGet);
                    else
                        dayGet.put(calendar, perStockGet);
                }
            }
        }
        double moneyPerStock;
        if(type.equals(LineType.STRATEGY_LINE))
            moneyPerStock=strategyMoneyPerHold/actualSize;
        else
            moneyPerStock=standardMoneyPerHold/actualSize;
        Map<Calendar,Double>dayYield=new TreeMap<>();
        if(dayGet.size()!=0) {
            Iterator<Map.Entry<Calendar, Double>> iterator1 = dayGet.entrySet().iterator();
            Map.Entry<Calendar, Double> tail1 = null;
            while (iterator1.hasNext()) {
                tail1 = iterator1.next();
                dayYield.put(tail1.getKey(),(tail1.getValue()*moneyPerStock-chengBen)/chengBen);
            }
            if(type.equals(LineType.STRATEGY_LINE))
                strategyMoneyPerHold=tail1.getValue()*moneyPerStock;
            else
                standardMoneyPerHold=tail1.getValue()*moneyPerStock;
        }
        if(type.equals(LineType.STRATEGY_LINE)) {
            strategyEarning.putAll(dayYield);
            strategyYieldRate=getYieldList(LineType.STRATEGY_LINE);
        }
        else {
            standardEarning.putAll(dayYield);
            standardYieldRate=getYieldList(LineType.STANDARD_LINE);
        }
    }

    protected QuantitativeVO getAll(Calendar start, Calendar end){
        Iterator<Map.Entry<Calendar,Double>> iterator1 = strategyEarning.entrySet().iterator();
        Map.Entry<Calendar,Double> tail1 = null;
        double drawDown=0;
        double before=0;
        while (iterator1.hasNext()) {                                 //最大回撤率的计算
            tail1 = iterator1.next();
            drawDown=before-tail1.getValue();
            if(drawDown>maximumDrawdown)
                maximumDrawdown=drawDown;
            before=tail1.getValue();
        }
        double strategyYield=tail1.getValue();
        Iterator<Map.Entry<Calendar,Double>> iterator2 = standardEarning.entrySet().iterator();
        Map.Entry<Calendar,Double> tail2 = null;
        while (iterator2.hasNext()) {
            tail2 = iterator2.next();
        }
        double standardYield=tail2.getValue();
        int days=Integer.parseInt(String.valueOf((end.getTimeInMillis()-start.getTimeInMillis())/(1000*3600*24)));
        annualizedReturn=strategyYield/days*365;
        standardAnnualizedReturn=standardYield/days*365;       //基准年化收益率
        // System.out.println(MathHelper.getCovariance(strategyYieldRate,standardYieldRate));
        // System.out.println(MathHelper.getVariance(standardYieldRate));
        Beta= MathHelper.getCovariance(strategyYieldRate,standardYieldRate)/MathHelper.getVariance(standardYieldRate);      //贝塔比率
        Alpha=annualizedReturn-riskFreeRate-Beta*Math.floor(standardAnnualizedReturn*riskFreeRate);                    //阿尔法比率
        sharpeRatio=(annualizedReturn-riskFreeRate)/Math.pow(MathHelper.getVariance(strategyYieldRate),0.5);              //夏普比率
        setYieldDistributionMap();
        QuantitativeVO quantitativeVO=new QuantitativeVO(strategyEarning,standardEarning, annualizedReturn,standardAnnualizedReturn,
                maximumDrawdown, Beta,Alpha,sharpeRatio,yieldDistributionMap);
        return quantitativeVO;
    }

    protected Boolean setStandardLine(Calendar start, Calendar end,String plate){
        //System.out.println(33333);

        standardEarning=stockMapper.getStandardLine(start,end,plate);
        //  System.out.println("standardSize"+standardEarning.size());

        if(standardEarning.size()==0)
            return false;
        else{
            standardYieldRate=getYieldList(LineType.STANDARD_LINE);
        }
        return true;
    }

    private ArrayList<Double> getYieldList(LineType type){
        ArrayList<Double>result=new ArrayList<>();
        if(type.equals(LineType.STRATEGY_LINE))
            result.add(strategyEarning.get(endHoldDate.get(0)));
        else {
            // System.out.println("standard"+standardEarning.size());
            result.add(standardEarning.get(endHoldDate.get(0)));
        }
        if(endHoldDate.size()==1)
            return result;
        double compare=result.get(0);
        // System.out.println("compare"+compare);
        double compare2;
        //System.out.println("size"+endHoldDate.size());
        for(int i=1;i<endHoldDate.size();i++){
            if(type.equals(LineType.STRATEGY_LINE))
                compare2=strategyEarning.get(endHoldDate.get(i));
            else
                compare2=standardEarning.get(endHoldDate.get(i));
            result.add((compare2-compare)/(compare+1));
            compare=compare2;
        }
        return result;
    }
    protected void setEndHoldDate(Calendar calendar){
        endHoldDate.add(calendar);
    }

    private void setYieldDistributionMap(){
        for(int i=0;i<strategyYieldRate.size();i++){
            double rate=strategyYieldRate.get(i);
            // System.out.println("原始"+rate);
            int temp=(int)(rate/0.01);
            double actual=temp;
            DecimalFormat df=new DecimalFormat("0.0");
            actual=new Double(df.format(actual).toString());
            //System.out.println("actual"+actual);
            if(yieldDistributionMap.containsKey(actual))
                yieldDistributionMap.replace(actual,yieldDistributionMap.get(actual)+1);
            else
                yieldDistributionMap.put(actual,1);
        }
        Iterator<Map.Entry<Double,Integer>> iterator1 = yieldDistributionMap.entrySet().iterator();
        Map.Entry<Double,Integer> tail1 = null;
        while (iterator1.hasNext()) {
            tail1 = iterator1.next();
            //System.out.println(tail1.getKey()+"hh"+tail1.getValue());
        }
    }

    protected Double canNotBuy(String code,Calendar calendar){                            //涨停或者这一天没有这只股票的数据
        ArrayList<Stock>stocks=new ArrayList<>();
        Calendar exCalendar=stockMapper.getExCalendar(code,calendar,1);
        stocks=stockMapper.getOneStock(code,exCalendar,calendar);

        if(stocks.size()<2){
            return -10000.0;
        }
        double close=stocks.get(0).getAdjClose();
        double exClose=stocks.get(1).getAdjClose();
        double compare1=(close-exClose)*100/close;
        double compare2=10-0.01*100/exClose;
        if(compare1>=compare2)
            return -10000.0;
        else {
            return close;
        }
    }

    /***
     public void getDate(Calendar cal){
     int year=cal.get(Calendar.YEAR);
     int month=cal.get(Calendar.MONTH)+1;
     int date=cal.get(Calendar.DATE);
     System.out.println("现在的日期为:"+year+"年"+month+"月"+date+"日");
     }
     ***/

}
