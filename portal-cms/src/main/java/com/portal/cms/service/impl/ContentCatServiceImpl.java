package com.portal.cms.service.impl;

import com.portal.cms.mapper.ContentCatMapper;
import com.portal.cms.service.IContentCatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portal.entity.cms.ContentCat;
import org.springframework.stereotype.Service;

@Service
public class ContentCatServiceImpl extends ServiceImpl<ContentCatMapper, ContentCat> implements IContentCatService {

}
