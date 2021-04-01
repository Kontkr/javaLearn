package com.learn.dp.adapter.classadapter;


public class Phone {

    public void charge() {
        IVoltage5V IVoltage5V = new VoltageAdapter();
        int v = IVoltage5V.output5V();
        if (v == 5) {
            System.out.println("电压5V，充电中！");
        } else {
            System.out.println("电压非5V,充电失败！");
        }
    }
}
