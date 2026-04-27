package cn.kiyasu.domain.res;

import lombok.Data;

/**
 * @className: WeixinQrCodeRes
 * @author: kiyasu
 * @description: 获取微信登录二维码响应对象
 * @create: 2026/4/27 02:48
 **/
@Data
public class WeixinQrCodeRes {

    private String ticket;
    private Long expire_seconds;
    private String url;
}
