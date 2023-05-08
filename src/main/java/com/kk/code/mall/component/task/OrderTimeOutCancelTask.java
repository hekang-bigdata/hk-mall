package com.kk.code.mall.component.task;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.component.task
 * @ClassName: OrderTimeOutCancelTask
 * @Author: HK
 * @Description:
 * @Date: 2023/5/8 17:01
 * @Version: 1.0
 */

@Component
@Log4j2
public class OrderTimeOutCancelTask {

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO: 2019/5/3 此处应调用取消订单的方法，具体查看mall项目源码
        log.info("取消订单，并根据sku编号释放锁定库存");
    }

}
