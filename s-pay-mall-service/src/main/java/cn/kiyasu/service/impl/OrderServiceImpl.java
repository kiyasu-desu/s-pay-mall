package cn.kiyasu.service.impl;

import cn.kiyasu.common.constants.Constants;
import cn.kiyasu.dao.IOrderDao;
import cn.kiyasu.domain.po.PayOrder;
import cn.kiyasu.domain.req.ShopCartReq;
import cn.kiyasu.domain.res.PayOrderRes;
import cn.kiyasu.domain.vo.ProductVO;
import cn.kiyasu.service.IOrderService;
import cn.kiyasu.service.rpc.ProductRPC;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author kiyasu
 * @className OrderServiceImpl
 * @description 订单服务
 * @create 2026/4/28 15:18
 **/
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDao orderDao;

    @Resource
    private ProductRPC productRPC;

    @Override
    public PayOrderRes createOrder(ShopCartReq shopCartReq) throws Exception {
        // 1. 查询当前用户是否存在未支付订单或掉单订单
        PayOrder payOrderReq = new PayOrder();
        payOrderReq.setUserId(shopCartReq.getUserId());
        payOrderReq.setProductId(shopCartReq.getProductId());

        PayOrder unpaidOrder = orderDao.queryUnpayOrder(payOrderReq);

        if (null != unpaidOrder && Constants.OrderStatusEnum.PAY_WAIT.getCode().equals(unpaidOrder.getStatus())) {
            log.info("创建订单-存在，已存在未支付订单。UserId:{} productId:{} orderId:{}", shopCartReq.getUserId(), shopCartReq.getProductId(), unpaidOrder.getOrderId());
            return PayOrderRes.builder()
                    .orderId(unpaidOrder.getOrderId())
                    .payUrl(unpaidOrder.getPayUrl())
                    .build();
        } else if (null != unpaidOrder && Constants.OrderStatusEnum.CREATE.getCode().equals(unpaidOrder.getStatus())) {
            // todo kiyasu
        }

        // 2. 查询商品 && 创建订单
        ProductVO productVO = productRPC.queryProductByProductId(shopCartReq.getProductId());
        String orderId = RandomStringUtils.randomNumeric(16);
        orderDao.insert(PayOrder.builder()
                        .userId(shopCartReq.getUserId())
                        .productId(shopCartReq.getProductId())
                        .productName(productVO.getProductName())
                        .orderId(orderId)
                        .totalAmount(productVO.getPrice())
                        .orderTime(new Date())
                        .status(Constants.OrderStatusEnum.CREATE.getCode())
                .build());

        // 3. 创建支付单 todo


        return PayOrderRes.builder()
                .orderId(orderId)
                .payUrl("暂无")
                .build();
    }
}
