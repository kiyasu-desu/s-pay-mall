package cn.kiyasu.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kiyasu
 * @className ShopCartReq
 * @description
 * @create 2026/4/28 15:11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartReq {

    private String userId;
    private String productId;

}
