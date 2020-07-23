package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Import;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 部门名字
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 上级部门id
     */
    private Integer pid;

    /**
     * 所有的上级部门id
     */
    private String pids;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    /**
     * 状态
     */
    private Integer status;

    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

}
