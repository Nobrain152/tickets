package anyeight.serviceImpl;

import anyeight.dao.StockMapper;
import anyeight.service.QuantitativeService;
import anyeight.vo.QuantitativeVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 啊 on 2017/5/11.
 */
public class QuantitativeServiceImpl implements QuantitativeService {
    StockMapper stockMapper;

    public QuantitativeVO getAll(Calendar startTime, Calendar endTime, int formative, int hold, String blockName,int type, double proportion) {
        ArrayList<String>stockList=new ArrayList<String>();
        Map<String,String> map=stockMapper.getIDList();
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        Map.Entry<String,String> tail = null;
        while (iterator.hasNext()) {
            tail = iterator.next();
            stockList.add(tail.getValue());
        }
        QuantitativeVO quantitativeVO=getAmongStocks(startTime,endTime,formative,hold,blockName,stockList,type,proportion);
        return quantitativeVO;
    }


    public QuantitativeVO getAmongBlock(Calendar startTime, Calendar endTime, int formative, int hold, String blockName, int type, double proportion) {
        ArrayList<String>stockList=new ArrayList<String>();
        stockList=stockMapper.getStocksByPlate(blockName);
        QuantitativeVO quantitativeVO=getAmongStocks(startTime,endTime,formative,hold,blockName,stockList,type,proportion);
        return quantitativeVO;
    }


    public QuantitativeVO getAmongStocks(Calendar startTime, Calendar endTime, int formative, int hold,String blockName, ArrayList<String> blocks, int type, double proportion) {
        return null;
    }


    public ArrayList<String> getBlockNameList() {
        ArrayList<String> result=new ArrayList<String>();
        result=stockMapper.getBlockNameList();
        return result;
    }


    public ArrayList<String> getBlockInClass(String className) {
        ArrayList<String> result=new ArrayList<String>();
        result=stockMapper.getStocksNameByPlate(className);
        return result;
    }
}
