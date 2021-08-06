package com.pactera.weather.service;

import com.pactera.weather.model.WeatherModel;

/**
 *
 * DemoService
 * @author 姜满祥
 * @since 2021-08-05
 */
public interface DemoService {

	/**
	 * 查询天气信息 Query weather information
	 *
	 * @param cityId 城市id
	 * @return WeatherModel
	 */
	WeatherModel queryWeatherInformation(String cityId);

}
