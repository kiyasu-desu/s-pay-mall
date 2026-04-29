package cn.kiyasu.test.service;

import cn.kiyasu.domain.req.ShopCartReq;
import cn.kiyasu.domain.res.PayOrderRes;
import cn.kiyasu.service.IOrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author kiyasu
 * @className OrderServiceTest
 * @description
 * @create 2026/4/28 15:52
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Resource
    private IOrderService orderService;

    @Test
    public void test_createOrder() {
        ShopCartReq shopCartReq = new ShopCartReq();
        shopCartReq.setUserId("kiyasu");
        shopCartReq.setProductId("10101");
        try {
            PayOrderRes payOrderRes = orderService.createOrder(shopCartReq);
            log.info("请求参数:{}", JSON.toJSONString(shopCartReq));
            log.info("测试结果:{}", JSON.toJSONString(payOrderRes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
