package com.luke.peach.peach.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.peach.peach.entity.CurrencyConversionDO;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luke
 * @since 2024-04-09
 */
public interface ICurrencyConversionService extends IService<CurrencyConversionDO> {

    /**
     * 获取各国指定日期的汇率
     * @param date
     * @return
     */
    List<CurrencyConversionDO> listCurrencyConversion(LocalDate date);
}
