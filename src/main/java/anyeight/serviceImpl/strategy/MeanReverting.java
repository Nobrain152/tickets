package anyeight.serviceImpl.strategy;

import anyeight.dao.StockMapper;
import anyeight.model.Average;
import anyeight.model.Stock;
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
public class MeanReverting extends QuantitativeServiceImpl{
    StockMapper stockMapper;
    Strategy strategy;
    @Override
    public QuantitativeVO getAmongStocks(Calendar startTime, Calendar endTime, int formative, int hold, String blockName, ArrayList<String> blocks, int type, double proportion) {
        Calendar before=null;
        Calendar exCalendar=stockMapper.getExCalendar("",startTime,1);                       //将before转化为实际开始日期
        if(exCalendar.get(Calendar.YEAR)!=2001) {
            before= stockMapper.getAfterCalendar("", exCalendar, 1);
            if(before.get(Calendar.YEAR)==2001)
                before=null;
        }
        while(before.before(endTime)) {
            Map<String, Double> yield = new HashMap<>();
            Map<String ,Double>adjClose=new HashMap<>();
            for(int i=0;i<blocks.size();i++){
                String id=blocks.get(i);
                ArrayList<Average>averages=stockMapper.getAverageList(id,before,before);
                if(averages.size()<1)
                    continue;
                Average average=averages.get(0);
                switch (formative){
                    case 5:adjClose.put(id,average.getAverage5());break;
                    case 10:adjClose.put(id,average.getAverage10());break;
                    default:adjClose.put(id,average.getAverage20());
                }
            }
            Map<String,Double> canBuyList=new HashMap<>();
            for(int i=0;i<blocks.size();i++){
                String code=blocks.get(i);
                ArrayList<Stock>stocks=null;
                stocks=stockMapper.getOneStock(code,before,before);
                if(stocks.size()==0)
                    continue;
                if(!adjClose.containsKey(code))
                    continue;
                double eachAdjClose=adjClose.get(code);
                double actualAdjClose=stocks.get(0).getAdjClose();
                double openPrice=strategy.canNotBuy(code,before);
                if(openPrice!=-10000.0) {
                    yield.put(code,(eachAdjClose-actualAdjClose)/actualAdjClose);
                    canBuyList.put(code,openPrice);
                }
            }

            ArrayList<String> buyCodeList = strategy.selectStock(yield,proportion);
            Calendar afterAHoldTime=null;

            afterAHoldTime=stockMapper.getAfterCalendar("",before,hold-1);
            if(afterAHoldTime.after(endTime)){
                afterAHoldTime=stockMapper.getAfterCalendar("",endTime,1);                      //用户输入的持有期过大
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
            canAdd=strategy.setStandardLine(startTime,endTime,blockName);
        if(!canAdd)
            return null;
        QuantitativeVO quantitativeVO=strategy.getAll(startTime,endTime);
        return quantitativeVO;
    }
}
