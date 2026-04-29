package cn.kiyasu.service.impl;

import cn.kiyasu.domain.vo.WeixinTemplateMessageVO;
import cn.kiyasu.domain.req.WeixinQrCodeReq;
import cn.kiyasu.domain.res.WeixinQrCodeRes;
import cn.kiyasu.domain.res.WeixinTokenRes;
import cn.kiyasu.service.ILoginService;
import cn.kiyasu.service.weixin.IWeixinApiService;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: WeixinLoginServiceImpl
 * @author: kiyasu
 * @description:
 * @create: 2026/4/27 03:42
 **/
@Service
public class WeixinLoginServiceImpl implements ILoginService {

    @Value("${weixin.config.app-id}")
    private String appId;
    @Value("${weixin.config.app-secret}")
    private String appSecret;
    @Value("${weixin.config.template-id}")
    private String template_id;

    @Resource
    private Cache<String, String> weixinAccessToken;
    @Resource
    private IWeixinApiService weixinApiService;
    @Resource
    private Cache<String, String> openidToken;

    @Override
    public String createQrTicket() throws Exception {
        //1. 获取 accessToken
        String accessToken = weixinAccessToken.getIfPresent(appId);
        if (null == accessToken) {
            Call<WeixinTokenRes> call = weixinApiService.getToken("client_credential", appId, appSecret);
            WeixinTokenRes weixinTokenRes = call.execute().body();
            assert weixinTokenRes != null;
            accessToken = weixinTokenRes.getAccess_token();
            weixinAccessToken.put(appId, accessToken);
        }

        //2. 生成 ticket
        WeixinQrCodeReq weixinQrCodeReq = WeixinQrCodeReq.builder()
                .expire_seconds(2592000)
                .action_name(WeixinQrCodeReq.ActionNameTypeVO.QR_SCENE.getCode())
                .action_info(WeixinQrCodeReq.ActionInfo.builder()
                        .scene(WeixinQrCodeReq.ActionInfo.Scene.builder()
                                .scene_id(100601)
                                .build())
                        .build())
                .build();

        Call<WeixinQrCodeRes> call = weixinApiService.createQrCode(accessToken, weixinQrCodeReq);
        WeixinQrCodeRes weixinQrCodeRes = call.execute().body();
        assert weixinQrCodeRes != null;
        return weixinQrCodeRes.getTicket();
    }

    @Override
    public String checkLogin(String ticket) {
        return openidToken.getIfPresent(ticket);
    }

    @Override
    public void saveLoginState(String ticket, String openid) throws IOException {
        openidToken.put(ticket, openid);

        //1. 获取 accessToken
        String accessToken = weixinAccessToken.getIfPresent(appId);
        if (null == accessToken) {
            Call<WeixinTokenRes> call = weixinApiService.getToken("client_credential", appId, appSecret);
            WeixinTokenRes weixinTokenRes = call.execute().body();
            assert weixinTokenRes != null;
            accessToken = weixinTokenRes.getAccess_token();
            weixinAccessToken.put(appId, accessToken);
        }

        //2. 发送模板消息
        Map<String, Map<String, String>> data = new HashMap<>();
        WeixinTemplateMessageVO.put(data, WeixinTemplateMessageVO.TemplateKey.USER, openid);
        WeixinTemplateMessageVO templateMessageDTO = new WeixinTemplateMessageVO(openid, template_id);
        templateMessageDTO.setUrl("https://baidu.com");
        templateMessageDTO.setData(data);

        Call<Void> call = weixinApiService.sendMessage(accessToken, templateMessageDTO);
        call.execute();

    }
}
