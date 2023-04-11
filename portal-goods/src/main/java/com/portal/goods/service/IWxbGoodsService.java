package com.portal.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.portal.entity.goods.WxbGoods;
import com.portal.vo.Result;

import java.util.List;

public interface IWxbGoodsService extends IService<WxbGoods> {
    List<WxbGoods> findGoodsSpuInfo();

    Result auditGoods(String spuId);
}
