package com.janson.design.command.simpleremote;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/13 5:09 下午
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}
