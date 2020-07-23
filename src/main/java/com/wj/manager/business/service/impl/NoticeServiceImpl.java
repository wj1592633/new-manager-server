package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.Notice;
import com.wj.manager.business.mapper.NoticeMapper;
import com.wj.manager.business.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
