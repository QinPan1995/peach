package com.luke.peach.job;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JobProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luke.peach.entity.CurrencyConversionDO;
import com.luke.peach.enums.CurrencyPairEnum;
import com.luke.peach.service.ICurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ：luke
 * @date ：Created in 2024/4/7 11:38 AM
 * @description：
 * @modified By：
 */

@Slf4j
@Component
public class CurrencyJob implements JobProcessor {

    @Autowired
    private ICurrencyConversionService iCurrencyConversionService;

    /**
     * 按照标准时间来算，每隔 5分钟 执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void job1() throws IOException {
        log.info("【job1】开始执行：{}", DateUtil.formatDateTime(new Date()));
        //LocalDate now = LocalDate.now();
        cell(0);

    }

    @Override
    public ProcessResult process(JobContext jobContext) throws IOException {
        log.info("【job1】开始执行：{}", DateUtil.formatDateTime(new Date()));
        cell(0);
        return new ProcessResult(true);
    }

    private void cell(Integer addDays) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        LocalDate now = LocalDate.now().plusDays(-addDays);
        Request request = new Request.Builder()
                .url("http://www.safe.gov.cn/AppStructured/hlw/jsonRmb.do?date=" + now)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        log.info("获取三方汇率结果：{}", string);
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<Object>> data = objectMapper.readValue(string, List.class);
        List<CurrencyConversionDO> currencyConversionDOS = iCurrencyConversionService.listCurrencyConversion(now);
        try {
            List<CurrencyConversionDO> addList = new ArrayList<>();
            List<Long> removeList = new ArrayList<>();
            for (List<Object> row : data) {
                BigDecimal amount = Convert.toBigDecimal(row.get(0));
                String sourceCurrency = Convert.toStr(row.get(1));
                BigDecimal exchangeRate = Convert.toBigDecimal(row.get(2));
                String targetCurrency = Convert.toStr(row.get(3));
                Optional<CurrencyConversionDO> first = currencyConversionDOS.stream().filter(o -> sourceCurrency.equals(o.getSourceCurrency().getName()) && targetCurrency.equals(o.getTargetCurrency().getName())).findFirst();
                if (first.isEmpty()) {
                    CurrencyConversionDO currencyConversion = new CurrencyConversionDO(amount, CurrencyPairEnum.getByName(sourceCurrency), exchangeRate, CurrencyPairEnum.getByName(targetCurrency), now);
                    //新增
                    addList.add(currencyConversion);
                    continue;
                }
                CurrencyConversionDO currencyConversionDO = first.get();
                BigDecimal amount1 = currencyConversionDO.getAmount();
                BigDecimal exchangeRate1 = currencyConversionDO.getExchangeRate();
                if (amount1.compareTo(amount) == 0 && exchangeRate1.compareTo(exchangeRate) == 0){
                    //无变化，跳过
                    continue;
                }
                //有变化，删除旧的汇率
                removeList.add(currencyConversionDO.getId());
                CurrencyConversionDO currencyConversion = new CurrencyConversionDO(amount, CurrencyPairEnum.getByName(sourceCurrency), exchangeRate, CurrencyPairEnum.getByName(targetCurrency), now);
                //新增
                addList.add(currencyConversion);
            }
            iCurrencyConversionService.removeBatchByIds(removeList);
            log.info("同步汇率到数据库：{}", JSONObject.toJSONString(addList));
            iCurrencyConversionService.saveOrUpdateBatch(addList);
            // Now 'conversions' list contains CurrencyConversion objects
            // You can use this list as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
