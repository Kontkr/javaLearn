package com.learn.dp.adapter.objadapter;


/**
 * 被适配者
 */
public class Voltag220V {

    public int output220V() {
        int src = 220;
        System.out.println("来源电压:" + src + "V");
        return 220;
    }
}
