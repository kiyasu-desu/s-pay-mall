package cn.kiyasu.controller.dto;

import lombok.Data;

/**
 * @author kiyasu
 * @className CreatePayRequestDTO
 * @description
 * @create 2026/5/2 23:56
 **/
@Data
public class CreatePayRequestDTO {

    //用户ID
    private String userId;

    //产品编号
    private String productId;
}
