package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 版本
     */
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
