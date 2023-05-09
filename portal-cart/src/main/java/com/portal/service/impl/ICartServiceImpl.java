package com.portal.service.impl;

import com.portal.entity.user.WxbMemeber;
import com.portal.interceptor.UserInfoInterceptor;
import com.portal.service.ICartService;
import com.portal.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

public class ICartServiceImpl implements ICartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Result saveCart(Map buyInfo) {

        if (buyInfo == null){
            return new Result(false,"fail");
        }
        WxbMemeber userInfo = UserInfoInterceptor.getUserInfo();

        //获取用户的购物车
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps("CART" + userInfo.getMemeberId());



        return null;
    }
}
