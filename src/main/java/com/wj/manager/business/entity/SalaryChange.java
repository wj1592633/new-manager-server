package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 修改工资表
 * </p>
 *
 * @author wj
 * @since 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_salary_change")
public class SalaryChange extends Model<SalaryChange> {

    private static final long serialVersionUID = 1L;

    private String salaryId;

    /**
     * 系统用户id
     */
    private String userId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime changeTime;

    private Integer success;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
