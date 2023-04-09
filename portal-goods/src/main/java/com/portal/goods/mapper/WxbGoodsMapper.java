package com.portal.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portal.entity.goods.WxbGoods;

import java.util.List;


public interface WxbGoodsMapper extends BaseMapper<WxbGoods> {

    List<WxbGoods> findGoodsSpuInfo();


    WxbGoods findGoodsById(String spuId);

}
