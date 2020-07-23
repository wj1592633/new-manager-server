package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.OperationLog;
import com.wj.manager.business.mapper.OperationLogMapper;
import com.wj.manager.business.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

}
