package com.enjoy.book.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    /**
     * 获取上传图片名
     *
     * @return
     */
    public static String getImageName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getImageName());
    }

}
