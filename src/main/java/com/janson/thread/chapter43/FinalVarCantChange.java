package com.janson.thread.chapter43;

/**
 * @Description: final变量一旦被赋值就不能被修改
 * @Author: Janson
 * @Date: 2022/1/17 16:36
 **/
public class FinalVarCantChange {

    public final int finalVar = 0;

    public static void main(String[] args) {
        FinalVarCantChange finalVarCantChange = new FinalVarCantChange();
        // Cannot assign a value to final variable 'finalVar'
//        finalVarCantChange.finalVar = 9;
    }

}
