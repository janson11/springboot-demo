package com.janson.design.command.simpleremote;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/13 5:05 下午
 */
public class LightOnCommand implements Command{
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
