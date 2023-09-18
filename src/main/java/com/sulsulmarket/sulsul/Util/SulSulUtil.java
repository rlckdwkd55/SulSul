package com.sulsulmarket.sulsul.Util;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class SulSulUtil {

    private static AtomicLong sequence = new AtomicLong(1);

    public static Date getCurrentTime(){

        Date now = new Date(System.currentTimeMillis());
        return now;
        
    }

    public static Map<String, Integer> getPage(double pageNum, double totalCount) {
//        log.info("요청이 들어오는 값을 찍어보자 : {}, {}", pageNum, totalCount);
        if (pageNum != 0 && totalCount != 0) {
//            log.info("여긴 들어오니? : {}, {}", pageNum, totalCount);
            Map<String, Integer> resultMap = new HashMap<>();
            int start = 1;
            double end = totalCount / 10;
            int endPage = (int) end;
            if (end % 1 != 0) {
                endPage = (int) (end + 1);
            }
            for (int i = start; i <= endPage; i++) {
                int pageStart = (i - 1) * 10 + 1;
                if (pageNum == i) {
                    resultMap.put("startNum", pageStart);
                    int pageEnd = pageStart + 9;
                    resultMap.put("endNum", pageEnd);
                    return resultMap;
                }
                //System.out.println("i에서 나오는 페이지 스타트 :" + pageStart);
            }
        }
        return null;
    }

    public synchronized static Long getNextSequence(){

        return sequence.getAndIncrement();
    }

    /**
     * String null || space check method
     */
    public static boolean strNullCheck(String str) {
        if (str.equals("") || str == null || str.length() == 0) {
            return false;
        }
        return true;
    } 

}
