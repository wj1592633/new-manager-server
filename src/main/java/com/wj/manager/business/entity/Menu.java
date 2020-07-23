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
 * 菜单表，权限表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    private String code;

    /**
     * 父级id
     */
    private Integer pid;

    private String pcode;

    private String pcodes;

    private String name;

    private String icon;

    private String url;

    private Integer levels;

    private Integer isMenu;

    private Integer isOpen;

    private Integer keepAlive;

    private String component;

    private Integer sort;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
