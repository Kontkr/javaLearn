package com.learn.dp.adapter.classadapter;


/**
 * 适配器
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        System.out.println("适配器工作中.......");
        int src = output220V();
        int dest = src / 44;
        return dest;
    }
}
