package com.portal.user.controller;


import com.portal.entity.user.WxbMemeber;
import com.portal.user.service.IWxbMemeberService;
import com.portal.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsh
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/user")
public class WxbMemeberController {

    @Autowired
    private IWxbMemeberService wxbMemeberService;
    @RequestMapping("login")
        public Result login(@RequestBody WxbMemeber memeber){
            return wxbMemeberService.login(memeber);
        }
}
