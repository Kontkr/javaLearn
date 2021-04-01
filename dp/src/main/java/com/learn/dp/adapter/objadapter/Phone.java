package com.learn.dp.adapter.objadapter;

import com.learn.dp.adapter.classadapter.Voltage220V;

public class Phone {

    public void charge() {
        IVoltage5V voltage5V = new VoltageAdapter(new Voltage220V());
        int dest = voltage5V.output5V();
        if (dest == 5) {
            System.out.println("手机充电中！");
        } else {
            System.out.println("电压不为5V，充电失败！");
        }
    }
}
