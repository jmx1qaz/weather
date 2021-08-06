package com.pactera.weather.model;

import lombok.Data;

@Data
public class Tip {

    int code = 200;

    Object detail;

    String message;
}
