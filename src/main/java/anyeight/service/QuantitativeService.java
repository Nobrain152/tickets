package anyeight.service;

import anyeight.vo.QuantitativeVO;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 啊 on 2017/5/11.
 */
public interface QuantitativeService {
    /*得到大盘内所有股票的量化交易信息*/
    public QuantitativeVO getAll(Calendar startTime, Calendar endTime, int formative, int hold,String blockName, int type1, double proportion);
    /*得到某个板块内所有股票的量化交易信息*/
    public QuantitativeVO getAmongBlock(Calendar startTime,Calendar endTime,int formative,int hold,String blockName,int type2 , double proportion);
    /*得到选定的某些股票的量化交易信息*/
    public QuantitativeVO getAmongStocks(Calendar startTime, Calendar endTime, int formative, int hold, String blockName,ArrayList<String> blocks, int type3, double proportion);
    /*得到用户自选股票的分类类名*/
    public ArrayList<String> getBlockNameList();
    /*得到某一类中的所有股票*/
    public ArrayList<String> getBlockInClass(String className);
}
