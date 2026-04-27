package cn.kiyasu.domain.res;

import lombok.Data;

/**
 * @className: WeixinTokenRes
 * @author: kiyasu
 * @description: 获取 Access token DTO 对象
 * @create: 2026/4/27 02:32
 **/
@Data
public class WeixinTokenRes {

    private String access_token;
    private int expires_in;
    private String errCode;
    private String errMsg;
}
