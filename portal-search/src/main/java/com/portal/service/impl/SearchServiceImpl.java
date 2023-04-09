package com.portal.service.impl;

import com.netflix.config.AggregatedConfiguration;
import com.portal.GoodsClient;
import com.portal.entity.ESGoods;
import com.portal.entity.SearchPageResult;
import com.portal.entity.goods.WxbGoods;
import com.portal.service.SearchService;
import com.portal.vo.PageResultVO;
import com.portal.vo.Result;
import io.netty.util.internal.StringUtil;
import jdk.nashorn.internal.runtime.NativeJavaPackage;
import org.apache.lucene.util.QueryBuilder;
import org.checkerframework.checker.units.qual.C;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    public ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Override
    public Result initData2es() {
        List<WxbGoods> goodSpuInfo = goodsClient.findGoodSpuInfo();

        goodSpuInfo.forEach(spu->{

            //spu->esGoods
            ESGoods esGoods = new ESGoods();
            esGoods.setSpuId(spu.getId());

            esGoods.setBrandId(spu.getBrand().getId());
            esGoods.setBrandName(spu.getBrand().getName());


            esGoods.setCid1id(spu.getCat1().getId());
            esGoods.setCat1name(spu.getCat1().getName());
            esGoods.setCid2id(spu.getCat2().getId());
            esGoods.setCat2name(spu.getCat2().getName());
            esGoods.setCid3id(spu.getCat3().getId());
            esGoods.setCat3name(spu.getCat3().getName());

            esGoods.setCreateTime(new Date());
            esGoods.setGoodsName(spu.getGoodsName());
            esGoods.setPrice(spu.getPrice().doubleValue());
            esGoods.setSmallPic(spu.getSmallPic());

            //保存
            elasticsearchRestTemplate.save(esGoods);

            elasticsearchRestTemplate.save(esGoods);
        });

        return new Result(true,"初始化成功");
    }

    @Override
    public PageResultVO<ESGoods> search(Map searchMap) {
        if (searchMap == null)return new PageResultVO<>(false,"参数不合法");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(searchMap.get("keyword"))){
            MatchQueryBuilder goodsQueryBuilder = QueryBuilders
                    .matchQuery("goodsName", searchMap.get("keyword"));
            MatchQueryBuilder brandQueryBuilder = QueryBuilders
                    .matchQuery("brandName", searchMap.get("keyword"));
            MatchQueryBuilder cat3QueryBuilder = QueryBuilders
                    .matchQuery("cat3name", searchMap.get("keyword"));

            boolQueryBuilder.should(goodsQueryBuilder);
            boolQueryBuilder.should(brandQueryBuilder);
            boolQueryBuilder.should(cat3QueryBuilder);
        }

        //设置分页
        int page = 0;
        if (!StringUtils.isEmpty(searchMap.get("page"))){
            page = (int) searchMap.get("page");
            page--;
        }

        int size = 2;
        if (!StringUtils.isEmpty(searchMap.get("size"))){
            size = (int) searchMap.get("size");
        }
        PageRequest pageRequest = PageRequest.of(page,size);
        //设置高亮
        HighlightBuilder goodsName = getHighlightBuilder("goodsName");
        //设置聚合
        TermsAggregationBuilder brandNameAgg = AggregationBuilders.terms("brandName_agg")
                .field("brandName.keyword");
        TermsAggregationBuilder cat3NameAgg = AggregationBuilders.terms("cat3name_agg")
                .field("cat3name.keyword");

        //过滤
        if (!StringUtils.isEmpty(searchMap.get("brandNameFilter"))){
            TermQueryBuilder brandNameFilter = QueryBuilders
                    .termQuery("brandName.keyword", searchMap.get("brandNameFilter"));
            boolQueryBuilder.filter(brandNameFilter);
        }

        if (!StringUtils.isEmpty(searchMap.get("cat3nameFilter"))){
            TermQueryBuilder cat3nameFilter = QueryBuilders
                    .termQuery("cat3name.keyword", searchMap.get("cat3nameFilter"));
            boolQueryBuilder.filter(cat3nameFilter);
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                //查询方式
                .withQuery(boolQueryBuilder)
                //设置高亮
                .withHighlightBuilder(goodsName)
                //设置聚合
                .addAggregation(brandNameAgg)
                .addAggregation(cat3NameAgg)
                //设置分页
                .withPageable(pageRequest);

        SortBuilder priceSortBuilder = null;
        //排序
        if (!StringUtils.isEmpty(searchMap.get("priceSort"))){
            if ("desc".equals(searchMap.get("priceSort"))){
                priceSortBuilder = SortBuilders.fieldSort("price").order(SortOrder.DESC);
            }else {
                priceSortBuilder = SortBuilders.fieldSort("price").order(SortOrder.ASC);
            }
            nativeSearchQueryBuilder.withSort(priceSortBuilder);
        }

        NativeSearchQuery builder = nativeSearchQueryBuilder
                .build();

        SearchHits<ESGoods> res = elasticsearchRestTemplate.search(builder, ESGoods.class);

        //获取分页信息
        long totalHits = res.getTotalHits();
        //获取当页信息
        List<ESGoods> esGoodsList = new ArrayList<>();
        List<SearchHit<ESGoods>> searchHits = res.getSearchHits();
        searchHits.forEach(hit->{
            ESGoods content = hit.getContent();
            //取高亮
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            Set<String> keyset = highlightFields.keySet();
            keyset.forEach(key->{
                if ("goodsName".equals(key)){
                    List<String> list = highlightFields.get(key);
                    content.setGoodsName(list.get(0));
                }
            });

            esGoodsList.add(content);
        });

        /*
        * 获取聚合信息
        * */
        ArrayList cat3NameList = new ArrayList();
        //获取聚合信息
        Aggregations aggregations = res.getAggregations();
        Terms cat3Name_agg = aggregations.get("cat3name_agg");
        List<? extends Terms.Bucket> cat2NameBuckets = cat3Name_agg.getBuckets();
        cat2NameBuckets.forEach(b->{
            cat3NameList.add(b.getKey());
        });

        ArrayList brandNameList = new ArrayList();
        //获取品牌的聚合信息
        Terms brandName = aggregations.get("brandName_agg");
        List<? extends Terms.Bucket> brandNameBuckets = brandName.getBuckets();
        brandNameBuckets.forEach(b->{
            brandNameList.add(b.getKey());
        });

        //构造分页结果
//        PageResultVO<ESGoods> pageResultVO = new PageResultVO<>(true,"success");
        SearchPageResult searchPageResult = new SearchPageResult();
        searchPageResult.setTotal(totalHits);
        searchPageResult.setData(esGoodsList);
        searchPageResult.setBrandNameList(brandNameList);
        searchPageResult.setCat3NameList(cat3NameList);

        return searchPageResult;
    }

    //设置高亮字段
    private HighlightBuilder getHighlightBuilder(String... fields){
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        for (String field:fields) {
            highlightBuilder.field(field);  //高亮查询字段
        }
        highlightBuilder.requireFieldMatch(false);  //如果多个字段为高亮，这项为false
        highlightBuilder.preTags("<span style=\"color:red\">"); //高亮设置
        highlightBuilder.postTags("</span>");
        highlightBuilder.fragmentSize(80000);
        highlightBuilder.numOfFragments(0);

        return highlightBuilder;
    }
}
