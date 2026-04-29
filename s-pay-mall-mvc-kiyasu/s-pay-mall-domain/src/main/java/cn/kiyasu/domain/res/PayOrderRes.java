package cn.kiyasu.domain.res;

import cn.kiyasu.common.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kiyasu
 * @className PayOrderRes
 * @description
 * @create 2026/4/28 15:11
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderRes {

    private String userId;
    private String orderId;
    private String payUrl;
    private Constants.OrderStatusEnum orderStatusEnum;

}
