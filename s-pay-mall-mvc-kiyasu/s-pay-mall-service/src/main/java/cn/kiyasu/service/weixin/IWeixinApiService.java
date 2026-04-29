package cn.kiyasu.service.weixin;

import cn.kiyasu.domain.vo.WeixinTemplateMessageVO;
import cn.kiyasu.domain.req.WeixinQrCodeReq;
import cn.kiyasu.domain.res.WeixinQrCodeRes;
import cn.kiyasu.domain.res.WeixinTokenRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @className: IWeixinApiService
 * @author: kiyasu
 * @description: 微信API接口
 * @create: 2026/4/27 02:30
 **/
public interface IWeixinApiService {

    /**
     * 获取 Access token
     *
     * @param grantType 获取access_token填写client_credential
     * @param appId     第三方用户唯一凭证
     * @param appSecret 第三方用户唯一凭证密钥，即appSecret
     * @return 响应结果
     */
    @GET("cgi-bin/token")
    Call<WeixinTokenRes> getToken(@Query("grant_type") String grantType,
                                  @Query("appid") String appId,
                                  @Query("secret") String appSecret);

    /**
     * 获取凭证 ticket
     *
     * @param accessToken     getToken 获取的 token 信息
     * @param weixinQrCodeReq 入参对象
     * @return 应答结果
     */
    @POST("cgi-bin/qrcode/create")
    Call<WeixinQrCodeRes> createQrCode(@Query("access_token") String accessToken, @Body WeixinQrCodeReq weixinQrCodeReq);

    /**
     * 发送微信公众号模板消息
     *
     * @param accessToken             getToken 获取的 token 信息
     * @param WeixinTemplateMessageVO 入参对象
     * @return 应答结果
     */
    @POST("cgi-bin/message/template/send")
    Call<Void> sendMessage(@Query("access_token") String accessToken, @Body WeixinTemplateMessageVO WeixinTemplateMessageVO);
}
