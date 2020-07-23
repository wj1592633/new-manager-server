package com.wj.manager.business.entity;

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
 * 通知表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_notice")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_id")
    private String noticeId;

    /**
     * 系统用户id
     */
    private String userId;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime postTime;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.noticeId;
    }

}
