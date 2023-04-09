package com.portal.entity;

import com.portal.vo.PageResultVO;
import lombok.Data;

import java.util.List;

/**
 * <p>title: com.portal.entity</p>
 * author zhuximing
 * description:
 */
@Data
public class SearchPageResult extends PageResultVO {

    //品牌的聚合列表信息

    private List<String> brandNameList;


    //三级分类聚合列表信息

    private List<String> cat3NameList ;


}