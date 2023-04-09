package com.portal.entity.sec;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("seckill_goods")
public class SecKillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "seckill_id", type = IdType.AUTO)
    private Integer seckillId;

    /**
     * 秒杀商品标题
     */
    private String seckillGoodsTitle;

    /**
     * 商品的sku信息
     */
    private String seckillGoodsSku;

    /**
     * 原价
     */
    private Double price;

    /**
     * 秒杀价格
     */
    private Double seckillPrice;

    /**
     * 秒杀开始时间
     */
    private Date seckillStarttime;

    /**
     * 秒杀结束时间
     */
    private Date seckillEndtime;

    /**
     * 秒杀数
     */
    private Integer seckillCount;

    /**
     * 剩余库存
     */
    private Integer seckillRemaining;

    /**
     * 审核状态：0，未审核1，审核通过2，审核不通过
     */
    private Integer seckillStatus;

    /**
     * 添加日期
     */
    private Date createdate;

    /**
     * 修改时间
     */
    private Date updatedate;


}
