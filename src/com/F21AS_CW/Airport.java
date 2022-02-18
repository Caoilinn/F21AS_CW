package com.F21AS_CW;

public class Airport {
    private String name;
    private String code;
    private ControlTower controlTower;

    public Airport(String name, String code, ControlTower controlTower) {
        this.name = name;
        this.code = code;
        this.controlTower = controlTower;
    }
    public Airport(String name, String code){
        this.name = name;
        this.code = code;
    }

    public Airport(){

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ControlTower getControlTower() {
        return controlTower;
    }

    public void setControlTower(ControlTower controlTower) {
        this.controlTower = controlTower;
    }
}
