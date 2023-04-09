package com.portal.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.portal.cms.service.IContentService;
import com.portal.entity.cms.Content;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/cms/content"})
public class ContentController {
    @Autowired
    private IContentService contentService;

    public ContentController() {
    }

    @RequestMapping({"findContentById"})
    public List<Content> findContentById(String cid) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper();
        QueryWrapper<Content> categoryId = (QueryWrapper)queryWrapper.eq("category_id", cid);
        return this.contentService.list(categoryId);
    }
}
