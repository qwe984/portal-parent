package com.portal.entity.sec;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("seckill_order")
public class SecKillOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号(主键)
     */
    @TableId
    private String seckillOrderId;

    /**
     * 秒杀商品id
     */
    private String seckillGoodsId;

    /**
     * 支付金额
     */
    private Double seckillMoney;

    private String seckillUserId;

    private String seckillSellerId;

    /**
     * 秒杀单子状态0，未支付1，已支付
     */
    private Integer seckillOrderStatus;

    /**
     * 支付时间
     */
    private Date seckillPaytime;

    /**
     * 收货地址
     */
    private String receiverAddress;

    /**
     * 联系电话
     */
    private String receiverMobile;

    /**
     * 收货人姓名
     */
    private String receiverName;

    private Date createdate;

    private Date updatedate;


}
