package cn.kiyasu.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kiyasu
 * @className AliPayConfig
 * @description
 * @create 2026/5/2 22:42
 **/
@Configuration
@EnableConfigurationProperties(AliPayConfigProperties.class)
public class AliPayConfig {

    @Bean("alipayClient")
    public AlipayClient alipayClient(AliPayConfigProperties properties) {
        return new DefaultAlipayClient(properties.getGatewayUrl(),
                properties.getApp_id(),
                properties.getMerchant_private_key(),
                properties.getFormat(),
                properties.getCharset(),
                properties.getAlipay_public_key(),
                properties.getSign_type());
    }



}
