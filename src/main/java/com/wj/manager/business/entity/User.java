package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author wj
 * @since 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户id
     */
    @TableId("user_id")
    private String userId;

    /**
     * 雇员id
     */
    private String empId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色id
     */
    private String roleId;

    private String socialType;

    private String socialId;

    /**
     * 状态
     */
    private Integer status;

    private Integer version;

    /**
     * 预留字段
     */
    @TableField("Column1")
    private String Column1;

    /**
     * 预留字段
     */
    @TableField("Column2")
    private String Column2;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
