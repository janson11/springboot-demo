package com.janson.thread.chapter43;

/**
 * @Description: final的方法不允许被重写
 * @Author: Janson
 * @Date: 2022/1/17 16:48
 **/
public class FinalMethod {

    public void drink(){

    }

    public final void eat(){

    }

}

class SubClass extends FinalMethod {
    @Override
    public void drink() {
        super.drink();
    }

//    public void eat(){ }
}

