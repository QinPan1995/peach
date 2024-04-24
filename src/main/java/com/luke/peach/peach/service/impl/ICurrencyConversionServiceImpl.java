package com.luke.peach.peach.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luke.peach.peach.entity.CurrencyConversionDO;
import com.luke.peach.peach.mapper.CurrencyConversionMapper;
import com.luke.peach.peach.service.ICurrencyConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luke
 * @since 2024-04-09
 */
@Service
public class ICurrencyConversionServiceImpl extends ServiceImpl<CurrencyConversionMapper, CurrencyConversionDO> implements ICurrencyConversionService {

    @Override
    public List<CurrencyConversionDO> listCurrencyConversion(LocalDate date) {
        LambdaQueryWrapper<CurrencyConversionDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CurrencyConversionDO::getConversionDate,date);
        return list(queryWrapper);
    }
}
