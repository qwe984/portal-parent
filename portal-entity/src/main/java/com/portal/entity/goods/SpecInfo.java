package com.portal.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>title: com.portal.entity.goods</p>
 * author zhuximing
 * description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecInfo {

    private String name;//规格名称

    private List<String> opts; //规格选项

}