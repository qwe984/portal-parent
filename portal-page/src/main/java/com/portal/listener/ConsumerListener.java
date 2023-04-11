package com.portal.listener;

import com.portal.entity.goods.WxbGoods;
import com.portal.goods.GoodsClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;

@Component
@RocketMQMessageListener(consumerGroup = "portal-page-consumer",
    topic = "goods-2-page",selectorExpression = "goods-2-page-tag",
    consumeMode = ConsumeMode.CONCURRENTLY,
    messageModel = MessageModel.BROADCASTING)   //BROADCASTING广播
public class ConsumerListener implements RocketMQListener<String> {

    @Value("${html.sink}")
    private String HTMLSINK;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    private GoodsClient goodsClient;
    @Override
    public void onMessage(String spuId) {
        try {
            genHTML(spuId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void genHTML(String spuId) throws Exception{
        Configuration configuration = freeMarkerConfig.getConfiguration();
        Template template = configuration.getTemplate("introduction.ftl");

        WxbGoods goods= goodsClient.findGoodsBySpuId(spuId);

        HashMap data = new HashMap();
        data.put("specs",goods.getSpecInfos());     //拿到数据
        Writer out = new FileWriter(HTMLSINK+"/"+spuId+".html");
        template.process(data,out);
        out.close();
    }
}
