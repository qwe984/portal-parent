package com.portal.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.portal.JwtUtils;
import com.portal.RsaUtils;
import com.portal.entity.user.WxbMemeber;
import com.portal.user.mapper.WxbMemeberMapper;
import com.portal.user.service.IWxbMemeberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portal.vo.Result;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.security.PrivateKey;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsh
 * @since 2023-04-14
 */
@Service
public class WxbMemeberServiceImpl extends ServiceImpl<WxbMemeberMapper, WxbMemeber> implements IWxbMemeberService {

    @Override
    public Result login(WxbMemeber memeber) {

        //判断用户名是否存在
        QueryWrapper<WxbMemeber> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account",memeber.getAccount());
        WxbMemeber wxbMemeber = this.baseMapper.selectOne(queryWrapper);

        if (wxbMemeber == null){
            return new Result(false,"用户名或密码错误");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(memeber.getPassword(),wxbMemeber.getPassword())){
            return new Result(false,"用户名或密码错误");
        }

        PrivateKey privateKey = null;
        try{
            privateKey = RsaUtils.getPrivateKey(ResourceUtils.getFile("classpath:rsa").getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        //颁发令牌
        wxbMemeber.setPassword(null);
        String token = JwtUtils.generateTokenExpireInMinutes(wxbMemeber, privateKey, 30);


        return new Result(true,"success",token);
    }
}
