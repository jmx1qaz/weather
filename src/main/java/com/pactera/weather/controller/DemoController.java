package com.pactera.weather.controller;

import com.pactera.weather.model.Tip;
import com.pactera.weather.model.WeatherModel;
import com.pactera.weather.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * DemoController
 * @author 姜满祥
 * @since 2021-08-05
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:/index.html";
    }

    @ResponseBody
    @RequestMapping(value = "/queryCurrentWeather/{cityId}", method = RequestMethod.GET)
    public Tip getCurrentWeather(@PathVariable String cityId){
        WeatherModel weatherModel = demoService.queryWeatherInformation(cityId);
        Tip tip = new Tip();
        tip.setDetail(weatherModel);
        return tip;
    }

}