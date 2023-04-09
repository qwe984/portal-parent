package com.portal.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author zhuxm
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxbGoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id，同时也是商品编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格，单位为：元
     */
    private BigDecimal price;

    /**
     * 提成
     */
    private Double tc;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 所属类目，叶子类目
     */
    @TableField("categoryId")
    private Long categoryId;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private BigDecimal marketPrice;

    private String isDefault;

    /**
     * spu的id
     */
    private Long goodsId;

    private String sellerId;

    private String category;

    private String brand;

    private String spec;

    private String seller;


}
