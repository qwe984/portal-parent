package com.portal.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portal.entity.goods.WxbGoods;
import com.portal.goods.mapper.WxbGoodsMapper;
import com.portal.goods.service.IWxbGoodsService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WxbGoodsServiceImpl extends ServiceImpl<WxbGoodsMapper, WxbGoods> implements IWxbGoodsService {
    public WxbGoodsServiceImpl() {
    }

    public List<WxbGoods> findGoodsSpuInfo() {
        return ((WxbGoodsMapper)this.baseMapper).findGoodsSpuInfo();
    }
}
