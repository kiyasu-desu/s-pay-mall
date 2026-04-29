package cn.kiyasu.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: WeixinTemplateMessageVO
 * @author: kiyasu
 * @description: 微信模板消息
 * @create: 2026/4/27 03:05
 **/
@Setter
@Getter
public class WeixinTemplateMessageVO {

    private String touser = "";
    private String template_id = "";
    private String url = "https://weixin.qq.com";
    private Map<String, Map<String, String>> data = new HashMap<>();

    public WeixinTemplateMessageVO(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }

    public void put(TemplateKey key, String value) {
        data.put(key.getCode(), new HashMap<String, String>() {
            public static final long serialVersionUID = 1L;

            {
                put("value", value);
            }
        });
    }

    public static void put(Map<String, Map<String, String>> data, TemplateKey key, String value) {
        data.put(key.getCode(), new HashMap<String, String>() {
            public static final long serialVersionUID = 1L;

            {
                put("value", value);
            }
        });
    }

    @Getter
    public enum TemplateKey {
        USER("user", "用户ID");

        private String code;
        private String desc;

        TemplateKey(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
