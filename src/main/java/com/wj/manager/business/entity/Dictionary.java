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
 * 系统字典表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_dictionary")
public class Dictionary extends Model<Dictionary> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dictId;

    private Integer code;

    private String name;

    private Integer isParent;

    private Integer pid;

    private Integer sort;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.dictId;
    }

}
