package com.portal.cms.service.impl;

import com.portal.cms.mapper.ContentMapper;
import com.portal.cms.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portal.entity.cms.Content;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuxm
 * @since 2021-04-02
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

}
