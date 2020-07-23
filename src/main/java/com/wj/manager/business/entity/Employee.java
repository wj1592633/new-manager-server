package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 雇员表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员id
     */
    @TableId(value = "emp_id")
    private String empId;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 职位id
     */
    private Integer positionId;

    /**
     * 雇员名字
     */
    private String name;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birth;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime hireDate;

    /**
     * 离职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime fireDate;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 性别
     */
    private Integer sex;

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
        return this.empId;
    }

}
