package com.luke.peach.peach.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author ：luke
 * @date ：Created in 2024/4/19 4:19 PM
 * @description：币种枚举
 * @modified By：
 */
@Getter
public enum CurrencyPairEnum {
    CNY("CNY", "人民币"),
    USD("USD", "美元"),
    EUR("EUR", "欧元"),
    JPY("JPY", "日元"),
    HKD("HKD", "港元"),
    GBP("GBP", "英镑"),
    AUD("AUD", "澳元"),
    NZD("NZD", "新西兰元"),
    SGD("SGD", "新加坡元"),
    CHF("CHF", "瑞士法郎"),
    CAD("CAD", "加元"),
    MOP("MOP", "澳门元"),
    MYR("MYR", "林吉特"),
    RUB("RUB", "卢布"),
    ZAR("ZAR", "兰特"),
    KRW("KRW", "韩元"),
    AED("AED", "迪拉姆"),
    SAR("SAR", "里亚尔"),
    HUF("HUF", "福林"),
    PLN("PLN", "兹罗提"),
    DKK("DKK", "丹麦克朗"),
    SEK("SEK", "瑞典克朗"),
    NOK("NOK", "挪威克朗"),
    TRY("TRY", "里拉"),
    MXN("MXN", "比索"),
    THB("THB", "泰铢");

    private final String code;
    @EnumValue
    private final String name;

    CurrencyPairEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code获取对应的枚举
     * @param code
     * @return
     */
    public static CurrencyPairEnum getByCode(String code) {
        for (CurrencyPairEnum currencyPairEnum : CurrencyPairEnum.values()) {
            if (currencyPairEnum.getCode().equals(code)) {
                return currencyPairEnum;
            }
        }
        return null;
    }

    /**
     * 根据name获取对应的枚举
     * @param name
     * @return
     */
    public static CurrencyPairEnum getByName(String name) {
        for (CurrencyPairEnum currencyPairEnum : CurrencyPairEnum.values()) {
            if (currencyPairEnum.getName().equals(name)) {
                return currencyPairEnum;
            }
        }
        return null;
    }

    /**
     * 重写toString方法以json的形式输出name和code
     */
    @Override
    public String toString() {
        return "{\"name\":\"" + this.name + "\",\"code\":\"" + this.code + "\"}";
    }

}