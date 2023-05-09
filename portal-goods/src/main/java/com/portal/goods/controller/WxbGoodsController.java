package com.portal.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.portal.JSONUtil;
import com.portal.entity.goods.SpecInfo;
import com.portal.entity.goods.WxbGoods;
import com.portal.entity.goods.WxbGoodsSku;
import com.portal.goods.service.IWxbGoodsService;
import java.util.List;

import com.portal.goods.service.IWxbGoodsSkuService;
import com.portal.goods.service.impl.WxbGoodsServiceImpl;
import com.portal.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/goods/wxb-goods"})
public class WxbGoodsController {
    @Autowired
    public IWxbGoodsService wxbGoodsService;

    @RequestMapping({"findListByCid"})
    public List<WxbGoods> findListByCid(@RequestParam("cid") String cid) {
        IPage<WxbGoods> wxbGoodsPage = new Page();
        QueryWrapper<WxbGoods> wxbQW = new QueryWrapper();
        wxbQW.eq("category3_id", cid);
        IPage<WxbGoods> page = this.wxbGoodsService.page(wxbGoodsPage, wxbQW);
        return page.getRecords();
    }

    @RequestMapping({"findGoodsSpuInfo"})
    public List<WxbGoods> findGoodSpuInfo() {
        return this.wxbGoodsService.findGoodsSpuInfo();
    }

    //同步索引库
    @RequestMapping("audit")
    public Result audit(String spuId){
         return wxbGoodsService.auditGoods(spuId);
    }

    @RequestMapping("findGoodsBySpuId")
    public WxbGoods findGoodsBySpuId(@RequestParam("spuId") String spuId){
        WxbGoods byId = wxbGoodsService.getById(spuId);
        String spuSpecInfo = byId.getSpuSpecInfo();
        List<SpecInfo> list = JSONUtil.toBean(spuSpecInfo, List.class);
        byId.setSpecInfos(list);
        return byId;
    }


    @Autowired
    private IWxbGoodsSkuService iWxbGoodsSkuService;

    @RequestMapping("findSkuListBySkuId")
    public List<WxbGoodsSku> findSkuListBySpuId(String spuId){
        QueryWrapper<WxbGoodsSku> wxbGoodsSkuQueryWrapper = new QueryWrapper<>();
        wxbGoodsSkuQueryWrapper.eq("goods_id",spuId);
        wxbGoodsSkuQueryWrapper.orderByDesc("is_default");
        return iWxbGoodsSkuService.list(wxbGoodsSkuQueryWrapper);
    }

}
