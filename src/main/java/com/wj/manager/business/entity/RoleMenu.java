package com.wj.manager.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单中间表
 * </p>
 *
 * @author wj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role_menu")
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * id
     */
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
