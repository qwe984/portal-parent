package com.portal.vo;

import lombok.*;

import java.util.List;

/**
 * <p>title: com.wfx.vo</p>
 * author zhuximing
 * description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PageResultVO<T> {

    private List<T> data;//当前页数据

    private Long total; //总记录数


    @NonNull
    private boolean success;  //查询状态

    @NonNull
    private String msg;  //查询信息



}