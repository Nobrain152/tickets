package anyeight.dao;

import anyeight.model.*;
import anyeight.po.StockPO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface StockMapper {
    /*给定股票名或id，返回股票id*/
    public String getStockId(String str);
    /*一段时间内，一只股票的所有表现*/
    public List<StockPO> getOneStock(StockBetweenTwoCan stockBetweenTwoCan);
    /*一天的股票市场信息*/
    public Market getMarket(Calendar date);
    /*某一天中，所有股票的具体表现*/
    public ArrayList<Stock> getOneDayStock(Calendar beginData);
    /* 返回股票名与id的对应map*/
    public Map getIDList() ;
    /* 返回均线图信息*/
    public ArrayList<Average> getAverageList(String ID, Calendar beginDate, Calendar endDate) ;


    /*返回起始数据的时间*/
    public Calendar getBeginDate() ;

    /*返回结束数据的时间*/
    public Calendar getEndDate() ;

    /*返回一只股票的前n日交易日期,若id为空字符串，则返回大盘前n日交易日期,若为2001-1-1,则存在问题*/
    public Calendar getExCalendar(String id,Calendar calendar,int day);

    /*返回一只股票的后n日交易日期;若id为空字符串，则返回大盘后n日交易日期*/
    public Calendar getAfterCalendar(String id,Calendar calendar,int day);

    /*获取某个模块的股票代码，获取的list里面是ID*/
    public ArrayList<String> getStocksByPlate (String plate);

    /*获取某个模块的股票代码，获取的list里面是名字*/
    public ArrayList<String> getStocksNameByPlate(String plateName) ;

    /*获取某个模块在某段时间的累计收益率，若plate为"",则返回整个大盘在这段时间的累计收益率*/
    public Map<Calendar,Double> getStandardLine(Calendar start,Calendar end,String plate);

    /*返回list里所有有数据股票的开始收盘价与结束收盘价*/
    public ArrayList<BuyAndSellPrice> getAvailableClose(Calendar start, Calendar end, ArrayList<String>ids);

    /*返回一只股票输入日期的股票信息，以及他的上一个交易日的股票交易信息
    * 返回的ArrayList如果长度为零，说明没有当天或者上一天*/
    //public ArrayList<StockPO> getTodayAndExStock(String id, Calendar today) ;

    /*通过一个存有股票id的List，来获得在List里面所有股票的对应 每天 的 复权收盘价*/
    // public ArrayList<AdjClosePO> getAdjClose(List<String> ids, Calendar start, Calendar end) ;

    /*得到用户自选股票的分类的类名*/
    public ArrayList<String> getBlockNameList();
}