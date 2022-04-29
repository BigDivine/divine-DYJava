package com.divine.sbdemo.utils;

import java.util.Calendar;
import java.util.UUID;

public class Utils {
    /**
     * 获取当前日期字符串
     *
     * @param separator
     * @return
     */
    public static String getNowDateStr(String separator) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DATE);

        return year + separator + month + separator + day;
    }

    //生成唯一的id
    public static String getRandomUUID() {
        String str = UUID.randomUUID().toString();
        return str.replace("-", "");
    }

    //判断字符串是否为空
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

}
