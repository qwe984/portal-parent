package com.portal.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.portal.entity.goods.WxbGoods;
import com.portal.goods.service.IWxbGoodsService;
import java.util.List;
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
}
