package com.portal.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.portal.entity.user.WxbMemeber;
import com.portal.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsh
 * @since 2023-04-14
 */
public interface IWxbMemeberService extends IService<WxbMemeber> {
    public Result login(WxbMemeber memeber);
}
