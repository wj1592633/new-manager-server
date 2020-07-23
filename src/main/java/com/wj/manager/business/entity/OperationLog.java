package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_operation_log")
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "oplog_id")
    private String oplogId;

    /**
     * 系统用户id
     */
    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operateTime;

    private String operation;

    private Integer success;

    private String className;

    private String methodName;


    @Override
    protected Serializable pkVal() {
        return this.oplogId;
    }

}
