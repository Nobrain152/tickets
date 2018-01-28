package anyeight.serviceImpl;

import anyeight.dao.StockMapper;
import anyeight.model.Average;
import anyeight.model.Market;
import anyeight.model.Stock;
import anyeight.service.StockService;
import anyeight.vo.CompareVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by 啊 on 2017/5/11.
 */
public class StockServiceImpl implements StockService {
    StockMapper stockMapper;
    public ArrayList<Stock> getStockInfo(String idtentification, Calendar beginDate, Calendar endDate) {
        String id=stockMapper.getStockId(idtentification);
        ArrayList<Stock>stocks=stockMapper.getOneStock(id,beginDate,endDate);
        return stocks;
    }

    public CompareVO getCompare(String idtentification, Calendar beginDate, Calendar endDate) {
        String id=stockMapper.getStockId(idtentification);
        ArrayList<Stock> stocks=new ArrayList<Stock>();
        double exBeginClose=-1;                                                                 //通过多拿一个工作日的收盘价来计算第一天的收益率
        Calendar actualBeginDate=stocks.get(stocks.size()-1).getDate();
        Calendar beforeStartCalendar=stockMapper.getExCalendar(id,actualBeginDate,1);
        if(beforeStartCalendar!=null) {
            ArrayList<Stock> arrayList = stockMapper.getOneStock(id, beforeStartCalendar, beforeStartCalendar);
            exBeginClose = arrayList.get(0).getAdjClose();
        }
        return calCompare(stocks,exBeginClose);
    }

    public Market getMarketInfo(Calendar date) {
        return stockMapper.getMarket(date);
    }

    public ArrayList<Average> getAverageVO(String idtentification, Calendar beginDate, Calendar endDate) {
        String id=stockMapper.getStockId(idtentification);
        return stockMapper.getAverageList(id,beginDate,endDate);
    }

    public Map getIDList() {
        return stockMapper.getIDList();
    }

    private CompareVO calCompare(ArrayList<Stock> stocks,double exBeginClose){
        /***
         int size=stocks.size();
         Stock first=stocks.get(0);
         String name=first.getName();
         String code=first.getCode();
         //计算涨跌幅,stocks的顺序是从后向前的么？？
         double close1=stocks.get(size-1).getAdjClose();
         double close2=first.getAdjClose();
         double change=(close2-close1)/close1;

         double high=first.getHigh();
         double low=first.getLow();
         ArrayList<Double>closeList=new ArrayList<>(size);
         ArrayList<Double>logarithmicYield=new ArrayList<>(size);
         double logarithmicYieldVariance=0;   //同上

         double exClose=stockPOs.get(0).getAdjClose();
         double close=exClose;
         double totalLogarithmic=0;                                    //有一点担心：累加后超出double表示范围?!

         if(exBeginClose>0) {
         double firstLogarithmic = Math.log(exBeginClose / exClose);       //通过多拿一个工作日的数据得到第一天的对数收益率,如果得不到前一天的数据就将其对数收益率设为0
         logarithmicYieldVariance += firstLogarithmic;
         logarithmicYield.add(firstLogarithmic);
         }
         else{
         logarithmicYield.add(0.0);
         }

         for(int i=0;i<size;i++){
         StockPO stockPO=stockPOs.get(i);
         if(stockPO.getLow()<low)
         low=stockPO.getLow();                //计算最高价
         if(stockPO.getHigh()>high)
         high=stockPO.getHigh();              //计算最低价
         exClose=close;
         close=stockPO.getAdjClose();
         closeList.add(stockPO.getClose());
         if(i!=0){
         double loagarithmic=Math.log(close/exClose);
         totalLogarithmic+=loagarithmic;
         logarithmicYield.add(loagarithmic);
         }
         }
         double average;
         if(exBeginClose<0)
         size--;
         average=totalLogarithmic/size;
         for(int i=0;i<size;i++){
         logarithmicYieldVariance+=Math.pow((logarithmicYield.get(i)-average),2);
         }
         compareVO=new CompareVO(name,code,low,high,change,closeList,logarithmicYield,logarithmicYieldVariance);
         return compareVO;
         ***/
        return null;
    }
}
