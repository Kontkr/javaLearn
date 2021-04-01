package com.learn.dp.adapter.classadapter;


/**
 * 被适配者 adaptee
 */
public class Voltage220V {

    public int output220V() {
        int src = 220;
        System.out.println("来源电压" + src + "V");
        return src;
    }
}
