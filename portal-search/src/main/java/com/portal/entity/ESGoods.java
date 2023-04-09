package com.portal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
@Data
@Document(indexName = "es-goods",  shards = 1, replicas = 0)
public class ESGoods {
    @Id
    private Long spuId;                        // spuId

    @Field(type = FieldType.Text, analyzer = "ik_max_word",store=true)
    private String goodsName;

    @Field(type=FieldType.Long,index=true,store=true)
    private Long brandId;                       // 品牌id
    
    @Field(type = FieldType.Keyword,index=true,store=true)
    private String brandName;                   //品牌名称

     @Field(type=FieldType.Long,index=true,store=true)
    private Long cid1id;                        // 1级分类id
    @Field(type = FieldType.Keyword,index=true,store=true)
    private String cat1name;                    // 1级分类名称
   
     @Field(type=FieldType.Long,index=true,store=true)
    private Long cid2id;                        // 2级分类id
     @Field(type = FieldType.Keyword,index=true,store=true)
    private String cat2name;                    // 2级分类名称
   
    
    @Field(type=FieldType.Long,index=true,store=true)
    private Long cid3id;                        // 3级分类id
     @Field(type = FieldType.Keyword,index=true,store=true)
    private String cat3name;                    // 3级分类名称

	 @Field(type=FieldType.Date,index=true,store=true,format= DateFormat.basic_date_time)
    private Date createTime;                    // 创建时间
    @Field(type=FieldType.Double,index=true,store=true)
    private Double price;                       // 价格，spu默认的sku的price
    @Field(type = FieldType.Keyword,index = false,store=true)
    private String smallPic;                    // 图片地址
}