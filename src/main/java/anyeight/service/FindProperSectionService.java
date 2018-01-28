package anyeight.service;

import anyeight.vo.SearchProperVO;

import java.util.Calendar;

/**
 * Created by 啊 on 2017/5/11.
 */
public interface FindProperSectionService {
    /*给定开始日期，结束日期和形成期确定最佳持有期（均值回归的形成期只能为5,10，20）*/
    public SearchProperVO findPropperHold(Calendar startTime, Calendar endTime, int formative, double proportion);
    /*给定开始日期，结束日期和持有其确定最佳形成期（均值回归没有此方法）*/
    public SearchProperVO findProperFormative(Calendar startTime, Calendar endTime, int hold, double proportion);
}
