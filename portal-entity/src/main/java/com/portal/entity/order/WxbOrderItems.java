package com.portal.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuxm
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxbOrderItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    private String skuName;

    private String skuId;

    private String orderId;

    private String skuPrice;

    private Date updateTime;

    private Integer buyNum;

    private Date createTime;

    private String goodsId;


}
