package cn.kiyasu.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kiyasu
 * @className ProductVO
 * @description
 * @create 2026/4/28 15:37
 **/
@Data
public class ProductVO {
    private String productId;
    private String productName;
    private String productDesc;
    private BigDecimal price;
}
