package com.janson.design.adapter;

/**
 * @Description: 适配器
 * 首先，你需要实现想转换成的类型接口，也就是你的客户所期望看到的接口
 * @Author: shanjian
 * @Date: 2022/5/16 10:54 上午
 */
public class TurkeyAdapter implements Duck {

    Turkey turkey;

    /**
     * 需要取到适配的对象引用，这里我们利用构造器取得这个引用。
     *
     * @param turkey
     */
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();

    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
