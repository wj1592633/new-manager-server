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
 * 职位表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_position")
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

    /**
     * 职位id
     */
    @TableId(value = "position_id", type = IdType.AUTO)
    private Integer positionId;

    /**
     * 职位名称
     */
    private String name;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.positionId;
    }

}
