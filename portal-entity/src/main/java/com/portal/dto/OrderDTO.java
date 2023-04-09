package com.portal.dto;

import com.portal.entity.address.WxbAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>title: com.portal.dto</p>
 * author zhuximing
 * description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//senderType:'圆通',
//    payType:"微信",
//    sendAddress:{}
public class OrderDTO {

    private String senderType;
    private String payType;
    private WxbAddress sendAddress;
    private Integer age;
    private String name;

}