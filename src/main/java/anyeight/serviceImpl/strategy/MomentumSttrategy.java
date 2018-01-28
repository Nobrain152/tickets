package anyeight.serviceImpl.strategy;

import anyeight.dao.StockMapper;
import anyeight.model.BuyAndSellPrice;
import anyeight.serviceImpl.QuantitativeServiceImpl;
import anyeight.util.LineType;
import anyeight.vo.QuantitativeVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 啊 on 2017/5/14.
 */
public class MomentumSttrategy extends QuantitativeServiceImpl{
    Strategy strategy;
    StockMapper stockMapper;
    @Override
    public QuantitativeVO getAmongStocks(Calendar startTime, Calendar endTime, int formative, int hold, String blockName, ArrayList<String> blocks, int type, double proportion) {
        Calendar before=null;
        Calendar exCalendar=stockMapper.getExCalendar("",startTime,1);                       //将before转化为实际开始日期
        if(exCalendar.get(Calendar.YEAR)!=2001) {
            before= stockMapper.getAfterCalendar("", exCalendar, 1);
            if(before.get(Calendar.YEAR)==2001)
                before=null;
        }

        double a=startTime.getTimeInMillis();
        double b=endTime.getTimeInMillis();
        double total=b-a;
        Calendar afterAHoldTime=null;
        while(before.before(endTime)) {
            Calendar formativeStart=null;
            Calendar formativeEnd=null;
            Map<String,Double> canBuyList=new HashMap<>();
            Map<String, Double> yield = new HashMap<>(blocks.size());
            formativeStart = stockMapper.getExCalendar("", before, formative);
            if(formativeStart.get(Calendar.YEAR)==2001)
                continue;                                                                //在该持有期找不到数据
            formativeEnd = stockMapper.getExCalendar("", before, 1);

            ArrayList<BuyAndSellPrice>buyAndSellPrices=null;
            buyAndSellPrices=stockMapper.getAvailableClose(formativeStart,formativeEnd,blocks);

            for (int i = 0; i < buyAndSellPrices.size(); i++) {
                BuyAndSellPrice buyAndSellPrice=buyAndSellPrices.get(i);
                double openPrice=strategy.canNotBuy(buyAndSellPrice.getId(),before);
                if(openPrice!=-10000.0) {
                    yield.put(buyAndSellPrice.getId(), (buyAndSellPrice.getEndAdjClose() - buyAndSellPrice.getStartAdjClose()) / buyAndSellPrice.getStartAdjClose());
                    canBuyList.put(buyAndSellPrice.getId(),openPrice);
                }
            }
            ArrayList<String> buyCodeList = strategy.selectStock(yield,proportion);

            afterAHoldTime=stockMapper.getAfterCalendar("",before,hold-1);
            if(afterAHoldTime.after(endTime)){
                afterAHoldTime=stockMapper.getAfterCalendar("",endTime,1);                      //将最后一个持有周期的最后一天设为用户输入的结束日期
                afterAHoldTime=stockMapper.getExCalendar("",afterAHoldTime,1);
                if(afterAHoldTime.after(endTime))
                    afterAHoldTime=stockMapper.getExCalendar("",afterAHoldTime,1);
            }
            strategy.setEndHoldDate(afterAHoldTime);
            strategy.setLine(before,afterAHoldTime,buyCodeList, LineType.STRATEGY_LINE,canBuyList);
            if(type==3) {
                strategy.setLine(before, afterAHoldTime, blocks, LineType.STANDARD_LINE,canBuyList);
            }

            before=stockMapper.getAfterCalendar("",afterAHoldTime,1);
        }
        Boolean canAdd=true;
        if(type==1||type==2)
            canAdd=strategy.setStandardLine(startTime,endTime,blockName);            //爬不到基准数据
        if(!canAdd)
            return null;
        QuantitativeVO quantitativeVO=strategy.getAll(startTime,endTime);
        return quantitativeVO;
    }
}
