package com.portal.vo;

import lombok.*;

/**
 * <p>title: com.wfx.vo</p>
 * author zhuximing
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Result<T> {

    @NonNull
    private boolean success;

    @NonNull
    private String msg;


    private T data;

}