package com.portal.controller;

import com.portal.service.ICartService;
import com.portal.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("add")
    public Result add(@RequestBody Map buyInfo){
        return iCartService.saveCart(buyInfo);
    }
}
