package cn.kiyasu.dao;

import cn.kiyasu.domain.po.PayOrder;
import org.apache.ibatis.annotations.Mapper;

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
}
