package com.portal.controller;

import com.portal.entity.cms.Content;
import com.portal.entity.goods.WxbGoods;
import com.portal.service.IndexService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"index"})
public class IndexController {
    @Autowired
    public IndexService indexservice;


    @RequestMapping({"listContentById"})
    public List<Content> listContentById(@RequestParam("cid") String cid) {
        return this.indexservice.listContentById(cid);
    }

    @RequestMapping({"findListByCid"})
    public List<WxbGoods> findListByCid(@RequestParam("cid") String cid) {
        return this.indexservice.findListByCid(cid);
    }
}
