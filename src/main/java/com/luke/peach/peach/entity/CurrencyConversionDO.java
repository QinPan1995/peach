package com.luke.peach.peach.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luke.peach.peach.enums.CurrencyPairEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author luke
 * @since 2024-04-09
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("currency_conversion")
public class CurrencyConversionDO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 来源货币
     */
    @TableField(value = "source_currency")
    private CurrencyPairEnum sourceCurrency;

    /**
     * 汇率
     */
    @TableField(value = "exchange_rate")
    private BigDecimal exchangeRate;

    /**
     * 目标货币
     */
    @TableField(value = "target_currency")
    private CurrencyPairEnum targetCurrency;

    /**
     * 日期
     */
    @TableField(value = "conversion_date")
    private LocalDate conversionDate;

    public CurrencyConversionDO(BigDecimal amount, CurrencyPairEnum sourceCurrency, BigDecimal exchangeRate, CurrencyPairEnum targetCurrency, LocalDate conversionDate) {
        this.amount = amount;
        this.sourceCurrency = sourceCurrency;
        this.exchangeRate = exchangeRate;
        this.targetCurrency = targetCurrency;
        this.conversionDate = conversionDate;
    }
}
