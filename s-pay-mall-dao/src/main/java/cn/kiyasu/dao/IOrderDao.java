package cn.kiyasu.dao;

import cn.kiyasu.domain.po.PayOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: IOrderDao
 * @author: kiyasu
 * @description:
 * @create: 2026/4/28 14:23
 **/
@Mapper
public interface IOrderDao {

    void insert(PayOrder payOrder);

    PayOrder queryUnpayOrder(PayOrder payOrderReq);

    void updateOrderPayInfo(PayOrder payOrder);

    void changeOrderPaySuccess(PayOrder order);

    List<String> queryNoPayNotifyOrder();

    List<String> queryTimeoutCloseOrderList();

    boolean changeOrderClose(String orderId);
}
