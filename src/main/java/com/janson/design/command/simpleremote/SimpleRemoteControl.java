package com.janson.design.command.simpleremote;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/13 5:07 下午
 */
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl(){}

    public void setCommand(Command command){
        slot = command;
    }

    public void buttonWasPressed(){
        slot.execute();
    }

}
