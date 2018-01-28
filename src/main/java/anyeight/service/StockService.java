package anyeight.service;

import anyeight.model.Average;
import anyeight.model.Market;
import anyeight.model.Stock;
import anyeight.vo.CompareVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by 啊 on 2017/5/11.
 */
public interface StockService {
    /*通过股票代码或者股票名称获取响应时间段内的股票信息*/
    public ArrayList<Stock> getStockInfo(String idtentification, Calendar beginDate, Calendar endDate);

    /*两只股票某一个时间段内的数据比较，传出数据，单只单只进行*/
    public CompareVO getCompare(String idtentification, Calendar beginDate, Calendar endDate);

    /*市场情况温度计，单日情况，大盘情况*/
    public Market getMarketInfo(Calendar date);

    /*返回5，10,20,30,60天的平均收盘价*/
    public ArrayList<Average> getAverageVO(String idtentification, Calendar beginDate, Calendar endDate);

    /* 返回股票名与id的对应list*/
    public Map getIDList();
}
