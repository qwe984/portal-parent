package com.portal.service;

import com.portal.entity.cms.Content;
import com.portal.entity.goods.WxbGoods;

import java.util.List;

public interface IndexService {
    List<Content> listContentById(String cid);

    List<WxbGoods> findListByCid(String cid);

}
