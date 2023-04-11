package com.portal.service;

import com.portal.entity.ESGoods;
import com.portal.entity.goods.WxbGoods;
import com.portal.vo.PageResultVO;
import com.portal.vo.Result;

import java.util.Map;

public interface SearchService {
    public Result initData2es();
    public PageResultVO<ESGoods> search(Map searchMap);

    public Result goods2es(WxbGoods wxbGoods);
}
