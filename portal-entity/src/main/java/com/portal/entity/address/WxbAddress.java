package com.portal.entity.address;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class WxbAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "loc_id", type = IdType.AUTO)
    private Integer locId;

    /**
     * 收件人姓名
     */
    private String locName;

    /**
     * 收件人电话
     */
    private String locTel;

    /**
     * 详细地址
     */
    private String locInfo;

    /**
     * 收件人邮箱
     */
    private String locEmail;

    /**
     * 地址别名（家里、公司、学校）
     */
    private String locType;

    /**
     * 是否默认1：是2：否
     */
    private Integer locIsDefault;

    private String locMemberId;


}
