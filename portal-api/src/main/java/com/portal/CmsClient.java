package com.portal;

import com.portal.entity.cms.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("portal-cms")
@RequestMapping("/cms/content")
public interface CmsClient {
    @RequestMapping("findContentById")
    public List<Content> findContentById(@RequestParam("cid") String cid);
}
