package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * 每次发工资记录表
 * </p>
 *
 * @author wj
 * @since 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_salary_record")
public class SalaryRecord extends Model<SalaryRecord> {

    private static final long serialVersionUID = 1L;

    @TableId("record_id")
    private String recordId;

    /**
     * 雇员id
     */
    private String empId;

    private String salaryId;

    private BigDecimal trueSalary;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime giveTime;


    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

}
