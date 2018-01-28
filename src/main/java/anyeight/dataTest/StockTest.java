package anyeight.dataTest;

import anyeight.dao.StockMapper;
import anyeight.factory.StockSqlSessionFactory;
import anyeight.model.StockBetweenTwoCan;
import anyeight.po.StockPO;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by T5-SK on 2017/5/14.
 */
public class StockTest {
    private static SqlSession sqlSession = null;
    private static StockMapper stockMapper = null;

    public static void main(String[] args) {
        sqlSession = StockSqlSessionFactory.getSqlSession();
        stockMapper = sqlSession.getMapper(StockMapper.class);
        //testGetStockId();
        testGetOneStock();

    }

    public static void testGetStockId(){
        System.out.println(stockMapper.getStockId("000001"));
        System.out.println(stockMapper.getStockId("000001"));
    }

    public static void testGetOneStock(){

        Date date1 = null,date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date1 = Date.valueOf("2017-05-02");
        date2 = Date.valueOf("2017-05-05");
        StockBetweenTwoCan stockBetweenTwoCan = new StockBetweenTwoCan("000001",date1,date2);
        List<StockPO> poList = stockMapper.getOneStock(stockBetweenTwoCan);
        for(StockPO po : poList){
            System.out.println(po.getDate().toString());
        }
    }
}
