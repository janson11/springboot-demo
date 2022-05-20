package com.janson.design.state.gumballstate;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/20 2:03 下午
 */
public interface State {
    public void insertQuarter();
    public void ejectQuarter();
    public void turnCrank();
    public void dispense();

    public void refill();

}
