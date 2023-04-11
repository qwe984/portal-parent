package com.portal.service.impl;

import com.portal.cmc.CmsClient;
import com.portal.goods.GoodsClient;
import com.portal.JSONUtil;
import com.portal.entity.cms.Content;
import com.portal.entity.goods.WxbGoods;
import com.portal.service.IndexService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    public CmsClient cmsClient;
    @Autowired
    public GoodsClient goodsClient;
    @Autowired
    public StringRedisTemplate redisTemplate;

    public List<Content> listContentById(String cid) {
        BoundHashOperations<String, Object, Object> cms = this.redisTemplate.boundHashOps("cms");
        Object contentList = cms.get("catid" + cid);
        List list;
        if (contentList == null) {
            list = this.cmsClient.findContentById(cid);
            cms.put("catid" + cid, JSONUtil.toString(list));
            return list;
        } else {
            list = (List)JSONUtil.toBean((String)contentList, List.class);
            return list;
        }
    }

    public List<WxbGoods> findListByCid(String cid) {
        BoundHashOperations<String, Object, Object> goods = this.redisTemplate.boundHashOps("goods");
        Object goodsCid = goods.get("goodsCid" + cid);
        List list;
        if (goodsCid == null) {
            list = this.goodsClient.findListByCid(cid);
            goods.put(goodsCid + cid, JSONUtil.toString(list));
            return list;
        } else {
            list = (List)JSONUtil.toBean((String)goodsCid, List.class);
            return list;
        }
    }
}
