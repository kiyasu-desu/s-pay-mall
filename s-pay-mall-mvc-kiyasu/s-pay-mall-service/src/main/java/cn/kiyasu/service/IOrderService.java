package cn.kiyasu.service;

import cn.kiyasu.domain.po.PayOrder;
import cn.kiyasu.domain.req.ShopCartReq;
import cn.kiyasu.domain.res.PayOrderRes;

import java.util.List;

/**
 * @className: IOrderService
 * @author: kiyasu
 * @description: 订单服务接口
 * @create: 2026/4/28 15:09
 **/
public interface IOrderService {

    PayOrderRes createOrder(ShopCartReq shopCartReq) throws Exception;

    void changeOrderPaySuccess(String  orderId);

    List<String> queryNoPayNotifyOrder();

    List<String> queryTimeoutCloseOrderList();

    boolean changeOrderClose(String orderId);
}
