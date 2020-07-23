package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 工资详情表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_salary")
public class Salary extends Model<Salary> {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员id
     */
    private String empId;

    private String salaryId;

    /**
     * 基础
     */
    private BigDecimal basicSalary;

    /**
     * 奖金
     */
    private BigDecimal bonus;

    /**
     */
    private BigDecimal extraSalary;

    private BigDecimal allSalary;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    private String description;


    @Override
    protected Serializable pkVal() {
        return this.salaryId;
    }

}
