package com.portal.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuxm
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxbGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商家ID
     */
    private String sellerId;

    /**
     * SPU名
     */
    private String goodsName;

    /**
     * 默认SKU
     */
    private Long defaultItemId;

    /**
     * 状态
     */
    private String auditStatus;

    /**
     * 是否上架
     */
    private String isMarketable;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * 副标题
     */
    private String caption;

    /**
     * 一级类目
     */
    private Long category1Id;

    /**
     * 二级类目
     */
    private Long category2Id;

    /**
     * 三级类目
     */
    private Long category3Id;

    /**
     * 小图
     */
    private String smallPic;

    /**
     * 商城价
     */
    private BigDecimal price;

    /**
     * 分类模板ID
     */
    private Long typeTemplateId;

    /**
     * 是否启用规格
     */
    private String isEnableSpec;

    /**
     * 是否删除
     */
    private String isDelete;

    /**
     * 文案id
     */
    private String copyId;

    /**
     * spu对应的规格信息
     */
    private String spuSpecInfo;


    //组合品牌
    @TableField(exist = false)
    private WxbGoodsBrand brand;

    //组合一级分类、二级分类、三级分类
    @TableField(exist = false)
    private WxbGoodsCat cat1;
    @TableField(exist = false)
    private WxbGoodsCat cat2;
    @TableField(exist = false)
    private WxbGoodsCat cat3;
    @TableField(exist = false)
    private List<SpecInfo> specInfos;


}
