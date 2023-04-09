package com.portal;

import com.portal.entity.goods.WxbGoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("portal-goods")
@RequestMapping("/goods/wxb-goods")
public interface GoodsClient {
    @RequestMapping("findListByCid")
    public List<WxbGoods> findListByCid(@RequestParam("cid") String cid);

    @RequestMapping("findGoodsSpuInfo")
    public List<WxbGoods> findGoodSpuInfo();

}
