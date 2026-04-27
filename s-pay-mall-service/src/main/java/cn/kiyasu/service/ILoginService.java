package cn.kiyasu.service;

import java.io.IOException;

/**
 * @className: ILoginService
 * @author: kiyasu
 * @description: 微信服务
 * @create: 2026/4/27 03:39
 **/
public interface ILoginService {

    String createQrTicket() throws Exception;

    String checkLogin(String ticket);

    void saveLoginState(String ticket, String openid) throws IOException;

}
