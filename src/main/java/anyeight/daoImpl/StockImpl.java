package anyeight.daoImpl;

import anyeight.dao.StockMapper;
import anyeight.model.*;
import anyeight.po.StockPO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by T5-SK on 2017/5/14.
 */
public class StockImpl extends SqlSessionDaoSupport implements StockMapper {

    // 需要向dao实现类中注入SqlSessionFactory
    // 这里通过构造方法注入
    private SqlSession sqlSession;

    public StockImpl(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }


    @Override
    public String getStockId(String str) {
        String ans = sqlSession.selectOne("mapper.StockMapper.getStockId","000001");
        // 提交事务
        sqlSession.commit();

        // 释放资源
        sqlSession.close();
        return ans;
    }

    @Override
    public List<StockPO> getOneStock(StockBetweenTwoCan stockBetweenTwoCan) {
        List<StockPO> poList = sqlSession.selectList("mapper.StockMapper.getOneStock",stockBetweenTwoCan);

        // 提交事务
        sqlSession.commit();

        // 释放资源
        sqlSession.close();

        return poList;
    }

    @Override
    public Market getMarket(Calendar date) {
        return null;
    }

    @Override
    public ArrayList<Stock> getOneDayStock(Calendar beginData) {
        return null;
    }

    @Override
    public Map getIDList() {
        return null;
    }

    @Override
    public ArrayList<Average> getAverageList(String ID, Calendar beginDate, Calendar endDate) {
        return null;
    }

    @Override
    public Calendar getBeginDate() {
        return null;
    }

    @Override
    public Calendar getEndDate() {
        return null;
    }

    @Override
    public Calendar getExCalendar(String id, Calendar calendar, int day) {
        return null;
    }

    @Override
    public Calendar getAfterCalendar(String id, Calendar calendar, int day) {
        return null;
    }

    @Override
    public ArrayList<String> getStocksByPlate(String plate) {
        return null;
    }

    @Override
    public ArrayList<String> getStocksNameByPlate(String plateName) {
        return null;
    }

    @Override
    public Map<Calendar, Double> getStandardLine(Calendar start, Calendar end, String plate) {
        return null;
    }

    @Override
    public ArrayList<BuyAndSellPrice> getAvailableClose(Calendar start, Calendar end, ArrayList<String> ids) {
        return null;
    }

    @Override
    public ArrayList<String> getBlockNameList() {
        return null;
    }
}
