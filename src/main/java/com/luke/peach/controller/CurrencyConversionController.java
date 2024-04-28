package com.luke.peach.controller;

import com.alibaba.fastjson2.JSONObject;
import com.luke.peach.entity.CurrencyConversionDO;
import com.luke.peach.service.ICurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ：luke
 * @date ：Created in 2024/4/24 6:01 PM
 * @description：
 * @modified By：
 */

@RestController
@RequestMapping("/currency_conversion")
public class CurrencyConversionController {

    @Autowired
    private ICurrencyConversionService iCurrencyConversionService;

    @GetMapping("/query")
    public String queryCurrencyConversion(@RequestParam LocalDate date){
        List<CurrencyConversionDO> currencyConversionDOS = iCurrencyConversionService.listCurrencyConversion(date);
        return JSONObject.toJSONString(currencyConversionDOS);
    }
}
