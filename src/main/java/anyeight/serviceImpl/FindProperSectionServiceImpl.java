package anyeight.serviceImpl;

import anyeight.dao.StockMapper;
import anyeight.service.FindProperSectionService;
import anyeight.service.QuantitativeService;
import anyeight.vo.QuantitativeVO;
import anyeight.vo.SearchProperVO;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by 啊 on 2017/5/11.
 */
public class FindProperSectionServiceImpl implements FindProperSectionService {
    StockMapper stockMapper;
    QuantitativeService quantitativeService;

    public SearchProperVO findPropperHold(Calendar startTime, Calendar endTime, int formative, double proportion) {
        Map<Integer,Double> excessEarning=new TreeMap<>();
        Map<Integer,Double> strategyWin=new TreeMap<>();
        int i=5;
        Calendar temp= null;
        temp = stockMapper.getAfterCalendar("",startTime,i);              //持有期不能少于5天
        if(temp.get(Calendar.YEAR)==2001){
            SearchProperVO searchProperVO=new SearchProperVO(excessEarning,strategyWin);
            // System.out.println("aaaaa");
            return searchProperVO;
        }

        double a=startTime.getTimeInMillis();
        double b=endTime.getTimeInMillis();
        double total=b-a;
        while(!temp.after(endTime)) {

            //progressOfAll.setProgress(((double) temp.getTimeInMillis() - a) / total);

            // System.out.println("aaa");
            //isProper=true;
            QuantitativeVO quantitativeVO=quantitativeService.getAll(startTime,endTime,formative,i,"",1, proportion);
            if(quantitativeVO.getAlpha()==-1&&quantitativeVO.getBeta()==-1&&quantitativeVO.getStandardAnnualizedReturn()==-1){
                SearchProperVO searchProperVO=new SearchProperVO(excessEarning,strategyWin);
                return searchProperVO;
            }
            //isProper=false;
            //System.out.println("bbb");
            excessEarning.put(i,getChaoE(quantitativeVO));
            strategyWin.put(i,getShenglv(quantitativeVO));
            i+=5;
            temp = stockMapper.getAfterCalendar("", startTime, i);

        }
        SearchProperVO searchProperVO=new SearchProperVO(excessEarning,strategyWin);
        return searchProperVO;
    }

    public SearchProperVO findProperFormative(Calendar startTime, Calendar endTime, int hold, double proportion) {
        Map<Integer,Double>excessEarning=new TreeMap<>();
        Map<Integer,Double> strategyWin=new TreeMap<>();
        for(int i=5;i<=20;i+=5) {
            //isProper=true;
            QuantitativeVO quantitativeVO=quantitativeService.getAll(startTime,endTime,i,hold,"",1,proportion);
            if(quantitativeVO.getAlpha()==-1&&quantitativeVO.getBeta()==-1&&quantitativeVO.getStandardAnnualizedReturn()==-1){
                SearchProperVO searchProperVO=new SearchProperVO(excessEarning,strategyWin);
                return searchProperVO;
            }
           // isProper=false;
            excessEarning.put(i, getChaoE(quantitativeVO));
            strategyWin.put(i,getShenglv(quantitativeVO));
        }
        SearchProperVO searchProperVO=new SearchProperVO(excessEarning,strategyWin);
        return searchProperVO;
    }

    private double getChaoE(QuantitativeVO quantitativeVO){
        double result=getReturn(quantitativeVO.getStrategyEarning())-getReturn(quantitativeVO.getStandardEarning());
        return result;
    }

    private double getReturn(Map<Calendar,Double>map){
        Iterator<Map.Entry<Calendar,Double>> iterator1 = map.entrySet().iterator();
        Map.Entry<Calendar,Double> tail= null;
        while (iterator1.hasNext()) {
            tail= iterator1.next();
        }
        return tail.getValue();
    }

    private double getShenglv(QuantitativeVO quantitativeVO){
        int zheng=0;
        Map map=quantitativeVO.getYieldDistributionMap();
        int size=0;
        Iterator<Map.Entry<Double,Integer>> iterator1 = map.entrySet().iterator();
        Map.Entry<Double,Integer> tail1 = null;
        while (iterator1.hasNext()) {
            tail1 = iterator1.next();
            if(tail1.getKey()>0) {
                zheng += tail1.getValue();
            }
            size+=tail1.getValue();
        }
        double result=1.0*zheng/size;
        //System.out.println("result"+result);
        return result;
    }
}
