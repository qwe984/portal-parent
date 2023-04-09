package com.portal.entity.user;

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
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxbMemeber implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * MID
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
     * 会员主键
     */
    private String memeberId;

    /**
     * 账号
     */
    private String account;

    /**
     * QQ号码
     */
    private String qqNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 推荐人ID 默认管理员
     */
    private String recomUser;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 支付宝账号
     */
    private String payAccount;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邀请码
     */
    private String visitCode;

    /**
     * 是否有邀请权限 0 没有 1 有
     */
    private Integer useRecom;

    /**
     * 层级关系识别码
     */
    private String levelCode;

    /**
     * 最后登录时间
     */
    private Date updateTime;


}
