package com.learn.dp.adapter.objadapter;

import com.learn.dp.adapter.classadapter.Voltage220V;

/**
 * 适配器
 */
public class VoltageAdapter implements IVoltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int adapter = 0;
        if (voltage220V != null) {
            int src = voltage220V.output220V();
            adapter = src / 44;
            System.out.println("适配器适配中......");
        }
        return adapter;
    }
}
