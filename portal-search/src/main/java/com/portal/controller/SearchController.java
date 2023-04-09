package com.portal.controller;

import com.portal.entity.ESGoods;
import com.portal.service.SearchService;
import com.portal.vo.PageResultVO;
import com.portal.vo.Result;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/search"})
public class SearchController {
    @Autowired
    public SearchService searchService;

    @RequestMapping({"init"})
    public Result init() {
        return this.searchService.initData2es();
    }

    @RequestMapping({"search"})
    public PageResultVO<ESGoods> search(@RequestBody Map searchMap) {
        return this.searchService.search(searchMap);
    }
}
