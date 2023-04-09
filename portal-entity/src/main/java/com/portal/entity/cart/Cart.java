package com.portal.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    
    private String skuId;
    
    private String title;
    
    private String img;
    
    private String spec;
    
    private Double price;
    
    private  Double marketPrice;
    
    private Integer buyNum;
    
    private String sellerId;
    
    private String sellerName;

    private boolean choose = false;


}