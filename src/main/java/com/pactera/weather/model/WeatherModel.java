package com.pactera.weather.model;

import lombok.Data;

/**
 *
 * WeatherModel
 * @author 姜满祥
 * @since 2021-08-05
 */
@Data
public class WeatherModel {

    // 城市
    String city;

    // 最小气温
    String minimumTemperature;

    // 最大气温
    String maximumTemperature;

    // 天气情况
    String weather;

    // 更新时间
    String updateTime;

}
