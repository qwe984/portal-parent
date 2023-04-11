package com.portal.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portal.es.ESClient;
import com.portal.entity.goods.WxbGoods;
import com.portal.goods.mapper.WxbGoodsMapper;
import com.portal.goods.service.IWxbGoodsService;
import java.util.List;

import com.portal.vo.Result;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WxbGoodsServiceImpl extends ServiceImpl<WxbGoodsMapper, WxbGoods> implements IWxbGoodsService {

//    @Qualifier("ESClient")
    @Autowired
    private ESClient esClient;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //es发送消息
    @Value("${topic.es}")
    private String ESTopic;
    @Value("${tag.es}")
    private String ESTag;
    //page发送消息
    @Value("${topic.page}")
    private String PAGETopic;
    @Value("${tag.page}")
    private String PAGETag;
    public List<WxbGoods> findGoodsSpuInfo() {
        return ((WxbGoodsMapper)this.baseMapper).findGoodsSpuInfo();
    }

    @Override
    public Result auditGoods(String spuId) {
        WxbGoods goodsById = this.baseMapper.findGoodsById(spuId);
        if (goodsById == null)return new Result(false,"该商品不存在");

        //修改商品审核状态
        goodsById.setAuditStatus("1");
        int i = this.baseMapper.updateById(goodsById);
        //判断数据是否更改成功，来保证两个数据源的一致性
            //同步索引库
//            esClient.goods2es(goodsById);
            //发送消息到mq，异步处理
        SendResult ESSendResult = rocketMQTemplate.syncSend(ESTopic + ":" + ESTag, goodsById);
        SendStatus ESSendStatus = ESSendResult.getSendStatus();
        SendResult PageSendResult = rocketMQTemplate.syncSend(PAGETopic + ":" + PAGETag, spuId);
        SendStatus PageSendStatus  = PageSendResult.getSendStatus();
        if (ESSendStatus == SendStatus.SEND_OK && PageSendStatus == SendStatus.SEND_OK){
                return new Result(true,"审核成功");
            }else{
                //业务未完成
                //todo:什么消息 什么时间 发送状态【补偿】
                return new Result(true,"同步失败");
            }
    }
}
