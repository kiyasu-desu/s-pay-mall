package cn.kiyasu.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author kiyasu
 * @className OrderPaySuccessListener
 * @description 支付成功回调消息
 * @create 2026/5/4 00:43
 **/
@Slf4j
@Component
public class OrderPaySuccessListener {

    @Subscribe
    public void handleEvent(String paySuccessMessage) {
        log.info("收到支付成功消息，可以做接下来的事情，如发货、充值、开会员、返利 {}",  paySuccessMessage);
    }
}
