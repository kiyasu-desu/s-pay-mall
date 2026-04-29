package cn.kiyasu.service.rpc;

import cn.kiyasu.domain.vo.ProductVO;
import org.springframework.stereotype.Service;

/**
 * @author kiyasu
 * @className ProductRPC
 * @description
 * @create 2026/4/28 15:35
 **/
@Service
public class ProductRPC {

    public ProductVO queryProductByProductId(String productId) {
        ProductVO productVO = new ProductVO();
        productVO.setProductId(productId);
        productVO.setProductName("测试商品");
        productVO.setProductDesc("这是一个测试商品");
        productVO.setPrice(new java.math.BigDecimal("1.68"));
        return productVO;
    }
}
