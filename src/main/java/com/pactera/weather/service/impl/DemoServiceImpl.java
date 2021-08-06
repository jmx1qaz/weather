package com.pactera.weather.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.pactera.weather.model.WeatherModel;
import com.pactera.weather.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * DemoServiceImpl
 * @author 姜满祥
 * @since 2021-08-05
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Value("${data.url}")
    private String url;

    @Override
    public WeatherModel queryWeatherInformation(String cityId) {
        WeatherModel weatherModel = new WeatherModel();
        try{
            // 由网址创建URL对象
            URL url = new URL(this.url+cityId+".html");
            // 防止乱码
            InputStreamReader isReader =  new InputStreamReader(url.openStream(),"UTF-8");
            // 采用缓冲式读入
            BufferedReader br = new BufferedReader(isReader);
            String str;
            while((str = br.readLine()) != null){
                System.out.println(str);
                JSONObject json = JSONObject.parseObject(str);
                String weatherInfoStr = json.getString("weatherinfo");
                // 国家气象局接口的json对象 set 到 weatherModel 中。
                JSONObject weatherInfo = JSONObject.parseObject(weatherInfoStr);
                // 城市
                weatherModel.setCity(weatherInfo.getString("city"));
                // 最低气温
                weatherModel.setMinimumTemperature(weatherInfo.getString("temp1"));
                // 最高气温
                weatherModel.setMaximumTemperature(weatherInfo.getString("temp2"));
                // 天气
                weatherModel.setWeather(weatherInfo.getString("weather"));
                // 时间
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                weatherModel.setUpdateTime(formatter.format(date));
            }
            br.close();//网上资源使用结束后，数据流及时关闭
            isReader.close();
        }
        catch(Exception exp){
            exp.printStackTrace();
            log.error(exp.toString());
            log.error(exp.getMessage());
        }
        return  weatherModel;
    }
}
